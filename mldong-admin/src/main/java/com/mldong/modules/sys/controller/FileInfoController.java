package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mldong.base.*;
import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.modules.sys.dto.FileInfoPageParam;
import com.mldong.modules.sys.dto.FileInfoParam;
import com.mldong.modules.sys.service.FileInfoService;
import com.mldong.modules.sys.vo.FileInfoVO;
import com.mldong.util.UrlWrapUtil;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* <p>
    * 文件信息 前端控制器
    * </p>
*
* @author mldong
* @since 2024-02-23
*/
@RestController
@Api(tags = "文件信息管理")
@RequiredArgsConstructor
public class FileInfoController {
    private final FileInfoService fileInfoService;
    private final FileStorageService fileStorageService;
    /**
     * 添加文件信息
     * @param param
     * @return
     */
    @PostMapping("/sys/fileInfo/save")
    @ApiOperation(value = "添加文件信息")
    @SaCheckPermission("sys:fileInfo:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) FileInfoParam param) {
        fileInfoService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除文件信息
     * @param param
     * @return
     */
    @PostMapping("/sys/fileInfo/remove")
    @ApiOperation(value = "删除文件信息")
    @SaCheckPermission("sys:fileInfo:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        fileInfoService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改文件信息
     * @param param
     * @return
     */
    @PostMapping("/sys/fileInfo/update")
    @ApiOperation(value = "修改文件信息")
    @SaCheckPermission("sys:fileInfo:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) FileInfoParam param) {
        fileInfoService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个文件信息
     * @param param
     * @return
     */
    @PostMapping("/sys/fileInfo/detail")
    @ApiOperation(value = "查询单个文件信息")
    @SaCheckPermission("sys:fileInfo:detail")
    public CommonResult<FileInfoVO> detail(@RequestBody IdParam param) {
        FileInfoVO fileInfo = fileInfoService.findById(param.getId());
        return CommonResult.data(fileInfo);
    }
    /**
     *分页查询文件信息列表
     * @param param
     * @return
     */
    @PostMapping("/sys/fileInfo/page")
    @ApiOperation(value = "分页查询文件信息列表")
    @SaCheckPermission("sys:fileInfo:page")
    public CommonResult<CommonPage<FileInfoVO>> page(@RequestBody FileInfoPageParam param) {
        return CommonResult.data(fileInfoService.page(param));
    }
    /**
     * 上传文件，成功返回文件 id/url
     */
    @PostMapping("/sys/fileInfo/upload")
    @ApiOperation(value="文件上传")
    public CommonResult<?> upload(MultipartFile file, @RequestParam(required = false,defaultValue = "default") String objectType) {
        String uploadBasePath = ConstantContextHolder.getUploadBasePath();
        String path = DateUtil.format(new Date(),"yyyyMM") + "/";
        if(StrUtil.isNotEmpty(uploadBasePath)) {
            uploadBasePath = StrUtil.removeSuffix(uploadBasePath,"/");
            uploadBasePath = StrUtil.removePrefix(uploadBasePath,"/");
            path = uploadBasePath + "/"+ path;
        }
        Long objectId = IdWorker.getId();
        String ext = FileNameUtil.getSuffix(file.getOriginalFilename());
        cn.xuyanwu.spring.file.storage.FileInfo fileInfo = fileStorageService.of(file)
                .setPath(path)
                .setSaveFilename(objectId+"."+ext)
                .setObjectId(objectId)
                .setObjectType(objectType)
                .upload();  //将文件上传到对应地方
        if(fileInfo == null) {
            return CommonResult.fail("上传失败");
        }
        Dict dict = Dict.create();
        dict.put("fullUrl", UrlWrapUtil.wrap(fileInfo.getUrl()));
        dict.put("url", fileInfo.getUrl());
        dict.put("fileInfoId", fileInfo.getObjectId());
        return CommonResult.data(dict);
    }
    @PostMapping("/sys/fileInfo/getFileInfoByIds")
    @ApiOperation(value="根据文件信息id获取ant文件对象")
    public CommonResult<List<AntUploadModel>> getFileInfoByIds(@RequestBody  Dict dict) {
        List<AntUploadModel> res = new ArrayList<>();
        String fileInfoIds = dict.getStr("fileInfoIds");
        if(StrUtil.isNotEmpty(fileInfoIds)) {
            res.addAll(fileInfoService.fileInfoToAntUploadModel(CollectionUtil.toList(fileInfoIds.split(","))));
        }
        return CommonResult.data(res);
    }
}
