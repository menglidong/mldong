package com.mldong.modules.sys.controller;

import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonResult;
import com.mldong.base.LabelValueVO;
import com.mldong.util.LowCodeServiceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
