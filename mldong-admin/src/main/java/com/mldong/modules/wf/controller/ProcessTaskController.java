package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.service.ProcessTaskService;
import com.mldong.modules.wf.vo.ProcessTaskVO;
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
    * 流程任务 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程任务管理")
@RequiredArgsConstructor
public class ProcessTaskController {
    private final ProcessTaskService processTaskService;
    /**
     * 添加流程任务
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/save")
    @ApiOperation(value = "添加流程任务")
    @SaCheckPermission("wf:processTask:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessTaskParam param) {
        processTaskService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程任务
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/remove")
    @ApiOperation(value = "删除流程任务")
    @SaCheckPermission("wf:processTask:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processTaskService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程任务
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/update")
    @ApiOperation(value = "修改流程任务")
    @SaCheckPermission("wf:processTask:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessTaskParam param) {
        processTaskService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程任务
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/detail")
    @ApiOperation(value = "查询单个流程任务")
    @SaCheckPermission("wf:processTask:detail")
    public CommonResult<ProcessTaskVO> detail(@RequestBody IdParam param) {
        ProcessTaskVO processTask = processTaskService.findById(param.getId());
        return CommonResult.data(processTask);
    }
    /**
     *分页查询流程任务列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/page")
    @ApiOperation(value = "分页查询流程任务列表")
    @SaCheckPermission("wf:processTask:page")
    public CommonResult<CommonPage<ProcessTaskVO>> page(@RequestBody ProcessTaskPageParam param) {
        return CommonResult.data(processTaskService.page(param));
    }
}
