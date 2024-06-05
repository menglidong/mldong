package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.mldong.base.CommonResult;
import com.mldong.base.LabelValueVO;
import com.mldong.util.LowCodeServiceUtil;
import com.mldong.web.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mldong
 * @date 2023/9/22
 */
@RestController
@Api(tags = "低代码相关接口")
@RequiredArgsConstructor
public class LowCodeController {
    @PostMapping("/{module}/{tableName}/select")
    @ApiOperation(value="通用下拉接口")
    public CommonResult<List<LabelValueVO>> defaultSelect(
            @PathVariable("module") String module,
            @PathVariable("tableName")String tableName,
            @RequestBody @Validated Dict param) {
        return CommonResult.data(LowCodeServiceUtil.select(module,tableName,param));
    }
    @PostMapping("/{module}/{tableName}/querySchema")
    @ApiOperation(value="通用高级搜索元数据接口")
    public CommonResult<?> defaultQuerySchema(
            @PathVariable("module") String module,
            @PathVariable("tableName")String tableName) {
        return CommonResult.data(LowCodeServiceUtil.buildQuerySchema(module,tableName) );
    }
    @PostMapping("/{module}/{tableName}/generateExportUrl")
    @ApiOperation(value = "获取导出下载链接")
    public CommonResult<Dict> defaultGenerateExportUrl(@PathVariable("module") String module,
                                                       @PathVariable("tableName")String tableName) {
        // 这里做一下鉴权
        StpUtil.checkLogin();
        if(!LoginUserHolder.me().isSuperAdmin()) {
            StpUtil.checkPermissionOr(
                    StrUtil.format("{}:{}:export", module, tableName),
                    StrUtil.format("{}:{}:generateExportUrl", module, tableName)
            );
        }
        return CommonResult.data(LowCodeServiceUtil.generateExportUrl(module,tableName));
    }
    /**
     * 导出演示
     * @return
     */
    @GetMapping("/{module}/{tableName}/export")
    @ApiOperation(value = "导出数据")
    @SaIgnore
    public void export(@PathVariable("module") String module,
                       @PathVariable("tableName")String tableName, String token) {
        LowCodeServiceUtil.export(module, tableName, token);
    }
}
