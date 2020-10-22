package com.mldong.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.dto.SysUploadConfigParam;
import com.mldong.modules.sys.dto.SysUploadConfigPageParam;
import com.mldong.modules.sys.entity.SysUploadConfig;
import com.mldong.modules.sys.service.SysUploadConfigService;

@RestController
@RequestMapping("/sys/uploadConfig")
@Api(tags="sys-上传配置管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="上传配置管理",scope="sys:uploadConfig:index")
    })
})
public class SysUploadConfigController {
	@Autowired
	private SysUploadConfigService sysUploadConfigService;

	@PostMapping("save")
	@ApiOperation(value="添加上传配置", notes="添加上传配置",authorizations={
		@Authorization(value="添加上传配置",scopes={
	    	@AuthorizationScope(description="添加上传配置",scope="sys:uploadConfig:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysUploadConfigParam param) {
		int count = sysUploadConfigService.save(param);
		if(count>0) {
			return CommonResult.success("添加上传配置成功", null);
		} else {
			return CommonResult.fail("添加上传配置失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改上传配置", notes="修改上传配置",authorizations={
		@Authorization(value="修改上传配置",scopes={
	    	@AuthorizationScope(description="修改上传配置",scope="sys:uploadConfig:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysUploadConfigParam param) {
		int count = sysUploadConfigService.update(param);
		if(count>0) {
			return CommonResult.success("修改上传配置成功", null);
		} else {
			return CommonResult.fail("修改上传配置失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除上传配置", notes="删除上传配置",authorizations={
		@Authorization(value="删除上传配置",scopes={
	    	@AuthorizationScope(description="删除上传配置",scope="sys:uploadConfig:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysUploadConfigService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除上传配置成功", null);
		} else {
			return CommonResult.fail("删除上传配置失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取上传配置", notes="通过id获取上传配置",authorizations={
		@Authorization(value="通过id获取上传配置",scopes={
	    	@AuthorizationScope(description="通过id获取上传配置",scope="sys:uploadConfig:get")
	    })
	})
	public CommonResult<SysUploadConfig> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取上传配置成功",sysUploadConfigService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询上传配置", notes="分页查询上传配置",authorizations={
		@Authorization(value="分页查询上传配置",scopes={
	    	@AuthorizationScope(description="分页查询上传配置",scope="sys:uploadConfig:list")
	    })
	})
	public CommonResult<CommonPage<SysUploadConfig>> list(@RequestBody @Validated SysUploadConfigPageParam param) {
		return CommonResult.success("查询上传配置成功",sysUploadConfigService.list(param));
	}
}
