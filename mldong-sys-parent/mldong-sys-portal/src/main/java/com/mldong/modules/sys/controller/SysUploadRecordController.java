package com.mldong.modules.sys.controller;

import com.mldong.common.base.*;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.upload.UploadMimeType;
import com.mldong.modules.sys.entity.SysUploadConfig;
import com.mldong.modules.sys.service.SysUploadConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.logger.LoggerModel;
import com.mldong.common.upload.CommonUpload;
import com.mldong.common.upload.model.UploadResult;
import com.mldong.common.upload.model.UploadTokenVo;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.sys.dto.SysUploadBizTypeParam;
import com.mldong.modules.sys.dto.SysUploadRecordPageParam;
import com.mldong.modules.sys.entity.SysUploadRecord;
import com.mldong.modules.sys.service.SysUploadRecordService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sys/uploadRecord")
@Api(tags="sys-上传记录管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="上传记录管理",scope="sys:uploadRecord:index")
    })
})
public class SysUploadRecordController {
	@Autowired
	private SysUploadRecordService sysUploadRecordService;
	@Autowired
	private SysUploadConfigService sysUploadConfigService;
	@PostMapping("remove")
	@ApiOperation(value="删除上传记录", notes="删除上传记录",authorizations={
		@Authorization(value="删除上传记录",scopes={
	    	@AuthorizationScope(description="删除上传记录",scope="sys:uploadRecord:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysUploadRecordService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除上传记录成功", null);
		} else {
			return CommonResult.fail("删除上传记录失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取上传记录", notes="通过id获取上传记录",authorizations={
		@Authorization(value="通过id获取上传记录",scopes={
	    	@AuthorizationScope(description="通过id获取上传记录",scope="sys:uploadRecord:get")
	    })
	})
	public CommonResult<SysUploadRecord> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取上传记录成功",sysUploadRecordService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询上传记录", notes="分页查询上传记录",authorizations={
		@Authorization(value="分页查询上传记录",scopes={
	    	@AuthorizationScope(description="分页查询上传记录",scope="sys:uploadRecord:list")
	    })
	})
	public CommonResult<CommonPage<SysUploadRecord>> list(@RequestBody @Validated SysUploadRecordPageParam param) {
		return CommonResult.success("查询上传记录成功",sysUploadRecordService.list(param));
	}
	@PostMapping("createUploadToken")
	@ApiOperation(value="创建上传凭证", notes="创建上传凭证",authorizations={
		@Authorization(value="创建上传凭证",scopes={
	    	@AuthorizationScope(description="创建上传凭证",scope="sys:upload:createUploadToken")
	    })
	})
	public CommonResult<UploadTokenVo> createUploadToken(@RequestBody SysUploadBizTypeParam param) {
		return CommonResult.success("创建上传凭证成功", sysUploadRecordService.createUploadToken(param));
	}
	@PostMapping("handleCallback")
	@ApiOperation(value="上传回调处理", notes="上传回调处理")
	@AuthIgnore
	public CommonResult<UploadResult> handleCallback(HttpServletRequest request) throws IOException {
		String callbackAuthHeader = request.getHeader(CommonUpload.CALLBACK_AUTH_HEADER);
		String callbackBody = IOUtils.toString(request.getInputStream(),"UTF-8");
		// 使用 HttpServletRequest request参数后，全局日志没办法 拿到body,需要再这里重新setBody
		LoggerModel logger = RequestHolder.getLoggerModel();
		if(null!=logger) {
			logger.setBody(callbackBody);
		}
		return CommonResult.success("上传回调处理成功", sysUploadRecordService.handleCallback(callbackAuthHeader, callbackBody));
	}
	@PostMapping("file")
	@ApiOperation(value="上传文件", notes="上传文件",authorizations={
			@Authorization(value="上传文件",scopes={
					@AuthorizationScope(description="上传文件",scope="stms:uploadRecord:file")
			})
	})
	public CommonResult<UploadResult> file(@RequestParam(value = "file") MultipartFile file, @RequestParam(defaultValue = "common") String bizType) {
		if(file.isEmpty()) {
			throw new BizException(GlobalErrEnum.GL99990007);
		}

		SysUploadConfig config = sysUploadConfigService.getByBizType(bizType);
		if(config == null) {
			throw new BizException(GlobalErrEnum.GL99990008);
		}
		if(!UploadMimeType.getMimeType(config.getFileExt()).contains(file.getContentType())) {
			throw new BizException(GlobalErrEnum.GL99990006);
		}
		if(file.getSize()>config.getFileSizeMax()) {
			throw new BizException(GlobalErrEnum.GL99990009);
		}
		String fileName = file.getOriginalFilename();  // 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		String filePath = config.getUploadDir() + config.getUploadSubDir();
		fileName = UUID.randomUUID() + suffixName; // 新文件名
		File dest = new File(filePath, fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BizException(GlobalErrEnum.GL99990007);
		}
		Date now = new Date();
		SysUploadRecord record = new SysUploadRecord();
		record.setBizId("");
		record.setBizType(config.getBizType());
		record.setCreateTime(now);
		record.setFileExt(suffixName);
		record.setFileSize(file.getSize());
		record.setFileName(file.getOriginalFilename());
		record.setIsDeleted(YesNoEnum.NO);
		record.setMimeType(file.getContentType());
		record.setUpdateTime(now);
		record.setUrl(config.getUploadSubDir()+"/"+fileName);
		if(YesNoEnum.YES.equals(config.getIsRecord())) {
			sysUploadRecordService.saveReturnId(record);
		}
		UploadResult result = new UploadResult();
		result.setUploadRecordId(record.getId());
		result.setBaseUrl(config.getBaseUrl());
		result.setBizId(record.getBizId());
		result.setBizType(record.getBizType());
		result.setFileExt(record.getFileExt());
		result.setFileSize(record.getFileSize());
		result.setMimeType(record.getMimeType());
		result.setUrl(record.getUrl());
		return CommonResult.success(result);
	}
}
