package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import com.mldong.base.*;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.HighLightVO;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.mldong.web.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    /**
     * 流程实例高亮数据
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/highLight")
    @ApiOperation(value = "流程实例高亮数据")
    public CommonResult<HighLightVO> highLight(@RequestBody IdParam param) {
        return CommonResult.data(processInstanceService.highLight(param.getId()));
    }
    /**
     * 审批记录
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/approvalRecord")
    @ApiOperation(value = "审批记录")
    public CommonResult<List<ProcessTaskVO>> approvalRecord(@RequestBody IdParam param) {
        return CommonResult.data(processInstanceService.approvalRecord(param.getId()));
    }
    /**
     * 获取流程参与人回显文本数据
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/getAssigneeTextData")
    @ApiOperation(value = "获取流程参与人回显文本数据")
    public CommonResult<List<LabelValueVO>> getAssigneeTextData(@RequestBody IdParam param) {
        return CommonResult.data(processInstanceService.getAssigneeTextData(param.getId()));
    }
    /**
     * 撤回流程
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/withdraw")
    @ApiOperation(value = "撤回流程")
    @SaCheckPermission("wf:processInstance:withdraw")
    public CommonResult<?> withdraw(@RequestBody IdsParam param) {
        param.getIds().forEach(id->{
            processInstanceService.withdraw(id, LoginUserHolder.getUserId().toString());
        });
        return CommonResult.ok();
    }
    /**
     * 创建抄送实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/createCCInstance")
    @ApiOperation(value = "创建抄送实例")
    @SaCheckPermission("wf:processInstance:createCCInstance")
    public CommonResult<?> createCCInstance(@RequestBody Dict param) {
        Long processInstanceId = Convert.toLong(param.get(FlowConst.PROCESS_INSTANCE_ID_KEY));
        Long userId = LoginUserHolder.getUserId();
        String[] actorIds = Convert.toStrArray(param.get(FlowConst.ACTOR_IDS_KEY));
        processInstanceService.createCCInstance(processInstanceId,userId.toString(),actorIds);
        return CommonResult.ok();
    }
    /**
     * 更新抄送状态
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/updateCCStatus")
    @ApiOperation(value = "更新抄送状态")
    @SaCheckPermission("wf:processInstance:updateCCStatus")
    public CommonResult<?> updateCCStatus(@RequestBody Dict param) {
        Long processInstanceId = Convert.toLong(param.get(FlowConst.PROCESS_INSTANCE_ID_KEY));
        Long userId = LoginUserHolder.getUserId();
        processInstanceService.updateCCStatus(processInstanceId,userId.toString());
        return CommonResult.ok();
    }
    /**
     *分页查询我的抄送列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/ccList")
    @ApiOperation(value = "分页查询我的抄送列表")
    @SaCheckPermission("wf:processInstance:ccList")
    public CommonResult<CommonPage<ProcessInstanceVO>> ccList(@RequestBody ProcessInstancePageParam param) {
        return CommonResult.data(processInstanceService.ccInstancePage(param));
    }
}
