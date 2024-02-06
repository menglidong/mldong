package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.VisLogPageParam;
import com.mldong.modules.sys.dto.VisLogParam;
import com.mldong.modules.sys.service.VisLogService;
import com.mldong.modules.sys.vo.VisLogVO;
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
    * 系统访问日志表 前端控制器
    * </p>
*
* @author mldong
* @since 2024-02-06
*/
@RestController
@Api(tags = "系统访问日志表管理")
@RequiredArgsConstructor
public class VisLogController {
    private final VisLogService visLogService;
    /**
     * 添加系统访问日志表
     * @param param
     * @return
     */
    @PostMapping("/sys/visLog/save")
    @ApiOperation(value = "添加系统访问日志表")
    @SaCheckPermission("sys:visLog:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) VisLogParam param) {
        visLogService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除系统访问日志表
     * @param param
     * @return
     */
    @PostMapping("/sys/visLog/remove")
    @ApiOperation(value = "删除系统访问日志表")
    @SaCheckPermission("sys:visLog:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        visLogService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改系统访问日志表
     * @param param
     * @return
     */
    @PostMapping("/sys/visLog/update")
    @ApiOperation(value = "修改系统访问日志表")
    @SaCheckPermission("sys:visLog:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) VisLogParam param) {
        visLogService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个系统访问日志表
     * @param param
     * @return
     */
    @PostMapping("/sys/visLog/detail")
    @ApiOperation(value = "查询单个系统访问日志表")
    @SaCheckPermission("sys:visLog:detail")
    public CommonResult<VisLogVO> detail(@RequestBody IdParam param) {
        VisLogVO visLog = visLogService.findById(param.getId());
        return CommonResult.data(visLog);
    }
    /**
     *分页查询系统访问日志表列表
     * @param param
     * @return
     */
    @PostMapping("/sys/visLog/page")
    @ApiOperation(value = "分页查询系统访问日志表列表")
    @SaCheckPermission("sys:visLog:page")
    public CommonResult<CommonPage<VisLogVO>> page(@RequestBody VisLogPageParam param) {
        return CommonResult.data(visLogService.page(param));
    }
}
