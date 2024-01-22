package com.mldong.modules.dev.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.dev.dto.SchemaGroupPageParam;
import com.mldong.modules.dev.dto.SchemaGroupParam;
import com.mldong.modules.dev.service.SchemaGroupService;
import com.mldong.modules.dev.vo.SchemaGroupVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
* <p>
    * 模型分组 前端控制器
    * </p>
*
* @author mldong
* @since 2024-01-17
*/
@RestController
@Api(tags = "模型分组管理")
@RequiredArgsConstructor
public class SchemaGroupController {
    private final SchemaGroupService schemaGroupService;
    /**
     * 添加模型分组
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaGroup/save")
    @ApiOperation(value = "添加模型分组")
    @SaCheckPermission("dev:schemaGroup:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SchemaGroupParam param) {
        schemaGroupService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除模型分组
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaGroup/remove")
    @ApiOperation(value = "删除模型分组")
    @SaCheckPermission("dev:schemaGroup:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        schemaGroupService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改模型分组
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaGroup/update")
    @ApiOperation(value = "修改模型分组")
    @SaCheckPermission("dev:schemaGroup:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SchemaGroupParam param) {
        schemaGroupService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个模型分组
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaGroup/detail")
    @ApiOperation(value = "查询单个模型分组")
    @SaCheckPermission("dev:schemaGroup:detail")
    public CommonResult<SchemaGroupVO> detail(@RequestBody IdParam param) {
        SchemaGroupVO schemaGroup = schemaGroupService.findById(param.getId());
        return CommonResult.data(schemaGroup);
    }
    /**
     *分页查询模型分组列表
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaGroup/page")
    @ApiOperation(value = "分页查询模型分组列表")
    @SaCheckPermission("dev:schemaGroup:page")
    public CommonResult<CommonPage<SchemaGroupVO>> page(@RequestBody SchemaGroupPageParam param) {
        return CommonResult.data(schemaGroupService.page(param));
    }
}
