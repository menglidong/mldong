package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessTaskActorPageParam;
import com.mldong.modules.wf.dto.ProcessTaskActorParam;
import com.mldong.modules.wf.service.ProcessTaskActorService;
import com.mldong.modules.wf.vo.ProcessTaskActorVO;
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
    * 流程任务和参与人关系 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程任务和参与人关系管理")
@RequiredArgsConstructor
public class ProcessTaskActorController {
    private final ProcessTaskActorService processTaskActorService;
    /**
     * 添加流程任务和参与人关系
     * @param param
     * @return
     */
    @PostMapping("/wf/processTaskActor/save")
    @ApiOperation(value = "添加流程任务和参与人关系")
    @SaCheckPermission("wf:processTaskActor:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessTaskActorParam param) {
        processTaskActorService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程任务和参与人关系
     * @param param
     * @return
     */
    @PostMapping("/wf/processTaskActor/remove")
    @ApiOperation(value = "删除流程任务和参与人关系")
    @SaCheckPermission("wf:processTaskActor:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processTaskActorService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程任务和参与人关系
     * @param param
     * @return
     */
    @PostMapping("/wf/processTaskActor/update")
    @ApiOperation(value = "修改流程任务和参与人关系")
    @SaCheckPermission("wf:processTaskActor:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessTaskActorParam param) {
        processTaskActorService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程任务和参与人关系
     * @param param
     * @return
     */
    @PostMapping("/wf/processTaskActor/detail")
    @ApiOperation(value = "查询单个流程任务和参与人关系")
    @SaCheckPermission("wf:processTaskActor:detail")
    public CommonResult<ProcessTaskActorVO> detail(@RequestBody IdParam param) {
        ProcessTaskActorVO processTaskActor = processTaskActorService.findById(param.getId());
        return CommonResult.data(processTaskActor);
    }
    /**
     *分页查询流程任务和参与人关系列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processTaskActor/page")
    @ApiOperation(value = "分页查询流程任务和参与人关系列表")
    @SaCheckPermission("wf:processTaskActor:page")
    public CommonResult<CommonPage<ProcessTaskActorVO>> page(@RequestBody ProcessTaskActorPageParam param) {
        return CommonResult.data(processTaskActorService.page(param));
    }
}
