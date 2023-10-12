package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.lang.Dict;
import com.mldong.base.*;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
* <p>
    * 流程定义 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程定义管理")
@RequiredArgsConstructor
public class ProcessDefineController {
    private final ProcessDefineService processDefineService;
    private final ProcessInstanceService processInstanceService;

    /**
     * 删除流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/remove")
    @ApiOperation(value = "删除流程定义")
    @SaCheckPermission("wf:processDefine:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processDefineService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 查询单个流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/detail")
    @ApiOperation(value = "查询单个流程定义")
    @SaCheckPermission(value = {"wf:processDefine:detail","wf:processDesign:listByType"},mode = SaMode.OR)
    public CommonResult<ProcessDefineVO> detail(@RequestBody IdParam param) {
        ProcessDefineVO processDefine = processDefineService.findById(param.getId());
        return CommonResult.data(processDefine);
    }
    /**
     *分页查询流程定义列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/page")
    @ApiOperation(value = "分页查询流程定义列表")
    @SaCheckPermission("wf:processDefine:page")
    public CommonResult<CommonPage<ProcessDefineVO>> page(@RequestBody ProcessDefinePageParam param) {
        return CommonResult.data(processDefineService.page(param));
    }
    /**
     * 启用/禁用流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/upAndDown")
    @ApiOperation(value = "启用/禁用流程定义")
    @SaCheckPermission("wf:processDefine:upAndDown")
    public CommonResult<?> upAndDown(@RequestBody UpAndDownParam param) {
        processDefineService.upAndDown(param);
        return CommonResult.ok();
    }
    /**
     * 启动流程实例
     * @param args
     * @return
     */
    @PostMapping("/wf/processDefine/startAndExecute")
    @ApiOperation(value = "启动流程实例")
    @SaCheckPermission(value = {"wf:processDefine:startAndExecute","wf:processDesign:listByType"}, mode = SaMode.OR)
    public CommonResult<?> startAndExecute(@RequestBody Dict args) {
        Long processDefineId = args.getLong(FlowConst.PROCESS_DEFINE_ID_KEY);
        args.remove(FlowConst.PROCESS_DEFINE_ID_KEY);
        processInstanceService.startAndExecute(processDefineId,args);
        return CommonResult.ok();
    }
}
