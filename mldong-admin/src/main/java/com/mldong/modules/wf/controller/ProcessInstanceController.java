package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
* <p>
    * 流程实例 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程实例管理")
@RequiredArgsConstructor
public class ProcessInstanceController {
    private final ProcessInstanceService processInstanceService;
    /**
     * 启动流程实例
     * @param args
     * @return
     */
    @PostMapping("/wf/processInstance/startAndExecute")
    @ApiOperation(value = "启动流程实例")
    @SaCheckPermission("wf:processInstance:startAndExecute")
    public CommonResult<?> startAndExecute(@RequestBody Dict args) {
        Long processDefineId = args.getLong(FlowConst.PROCESS_DEFINE_ID_KEY);
        args.remove(FlowConst.PROCESS_DEFINE_ID_KEY);
        processInstanceService.startAndExecute(processDefineId,args);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/detail")
    @ApiOperation(value = "查询单个流程实例")
    @SaCheckPermission("wf:processInstance:detail")
    public CommonResult<ProcessInstanceVO> detail(@RequestBody IdParam param) {
        ProcessInstanceVO processInstance = processInstanceService.findById(param.getId());
        return CommonResult.data(processInstance);
    }
    /**
     *分页查询流程实例列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/page")
    @ApiOperation(value = "分页查询流程实例列表")
    @SaCheckPermission("wf:processInstance:page")
    public CommonResult<CommonPage<ProcessInstanceVO>> page(@RequestBody ProcessInstancePageParam param) {
        return CommonResult.data(processInstanceService.page(param));
    }
}
