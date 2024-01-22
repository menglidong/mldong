package com.mldong.modules.dev.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.dev.dto.SchemaFieldPageParam;
import com.mldong.modules.dev.dto.SchemaFieldParam;
import com.mldong.modules.dev.dto.UpdateSortParam;
import com.mldong.modules.dev.service.SchemaFieldService;
import com.mldong.modules.dev.vo.SchemaFieldVO;
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
    * 模型字段 前端控制器
    * </p>
*
* @author mldong
* @since 2024-01-17
*/
@RestController
@Api(tags = "模型字段管理")
@RequiredArgsConstructor
public class SchemaFieldController {
    private final SchemaFieldService schemaFieldService;
    /**
     * 添加模型字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaField/save")
    @ApiOperation(value = "添加模型字段")
    @SaCheckPermission("dev:schemaField:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SchemaFieldParam param) {
        schemaFieldService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除模型字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaField/remove")
    @ApiOperation(value = "删除模型字段")
    @SaCheckPermission("dev:schemaField:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        schemaFieldService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改模型字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaField/update")
    @ApiOperation(value = "修改模型字段")
    @SaCheckPermission("dev:schemaField:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SchemaFieldParam param) {
        schemaFieldService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个模型字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaField/detail")
    @ApiOperation(value = "查询单个模型字段")
    @SaCheckPermission("dev:schemaField:detail")
    public CommonResult<SchemaFieldVO> detail(@RequestBody IdParam param) {
        SchemaFieldVO schemaField = schemaFieldService.findById(param.getId());
        return CommonResult.data(schemaField);
    }
    /**
     *分页查询模型字段列表
     * @param param
     * @return
     */
    @PostMapping("/dev/schemaField/page")
    @ApiOperation(value = "分页查询模型字段列表")
    @SaCheckPermission("dev:schemaField:page")
    public CommonResult<CommonPage<SchemaFieldVO>> page(@RequestBody SchemaFieldPageParam param) {
        return CommonResult.data(schemaFieldService.page(param));
    }
    @PostMapping("/dev/schemaField/updateSort")
    @ApiOperation(value = "更新排序")
    @SaCheckPermission("dev:schemaField:updateSort")
    public CommonResult<?> updateSort(@RequestBody UpdateSortParam param) {
        boolean isSuccess = schemaFieldService.updateSort(param);
        if (isSuccess) {
            return CommonResult.ok("更新排序成功");
        } else {
            return CommonResult.fail("更新排序失败");
        }
    }
}
