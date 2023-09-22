package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.DictItemPageParam;
import com.mldong.modules.sys.dto.DictItemParam;
import com.mldong.modules.sys.service.DictItemService;
import com.mldong.modules.sys.vo.DictItemVO;
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
    * 字典项 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "字典项管理")
@RequiredArgsConstructor
public class DictItemController {
    private final DictItemService dictItemService;
    /**
     * 添加字典项
     * @param param
     * @return
     */
    @PostMapping("/sys/dictItem/save")
    @ApiOperation(value = "添加字典项")
    @SaCheckPermission("sys:dictItem:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) DictItemParam param) {
        dictItemService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除字典项
     * @param param
     * @return
     */
    @PostMapping("/sys/dictItem/remove")
    @ApiOperation(value = "删除字典项")
    @SaCheckPermission("sys:dictItem:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        dictItemService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改字典项
     * @param param
     * @return
     */
    @PostMapping("/sys/dictItem/update")
    @ApiOperation(value = "修改字典项")
    @SaCheckPermission("sys:dictItem:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) DictItemParam param) {
        dictItemService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个字典项
     * @param param
     * @return
     */
    @PostMapping("/sys/dictItem/detail")
    @ApiOperation(value = "查询单个字典项")
    @SaCheckPermission("sys:dictItem:detail")
    public CommonResult<DictItemVO> detail(@RequestBody IdParam param) {
        DictItemVO dictItem = dictItemService.findById(param.getId());
        return CommonResult.data(dictItem);
    }
    /**
     *分页查询字典项列表
     * @param param
     * @return
     */
    @PostMapping("/sys/dictItem/page")
    @ApiOperation(value = "分页查询字典项列表")
    @SaCheckPermission("sys:dictItem:page")
    public CommonResult<CommonPage<DictItemVO>> page(@RequestBody DictItemPageParam param) {
        return CommonResult.data(dictItemService.page(param));
    }
}
