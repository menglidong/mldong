package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.LabelValueVO;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.enums.ProcessSubmitTypeEnum;
import com.mldong.modules.wf.service.ProcessTaskService;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.mldong.web.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    private final FlowEngine flowEngine;
    /**
     * 查询单个流程任务
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/detail")
    @ApiOperation(value = "查询单个流程任务")
    public CommonResult<ProcessTaskVO> detail(@RequestBody IdParam param) {
        ProcessTaskVO processTask = processTaskService.findById(param.getId());
        return CommonResult.data(processTask);
    }
    /**
     *分页查询我的待办列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/todoList")
    @ApiOperation(value = "分页查询我的待办列表")
    @SaCheckPermission("wf:processTask:todoList")
    public CommonResult<CommonPage<ProcessTaskVO>> todoList(@RequestBody ProcessTaskPageParam param) {
        return CommonResult.data(processTaskService.todoList(param));
    }
    /**
     *分页查询我的已办列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processTask/doneList")
    @ApiOperation(value = "分页查询我的已办列表")
    @SaCheckPermission("wf:processTask:doneList")
    public CommonResult<CommonPage<ProcessTaskVO>> doneList(@RequestBody ProcessTaskPageParam param) {
        return CommonResult.data(processTaskService.doneList(param));
    }
    /**
     * 处理待办
     * @param args
     * @return
     */
    @PostMapping("/wf/processTask/execute")
    @ApiOperation(value = "处理待办")
    @SaCheckPermission("wf:processTask:execute")
    public CommonResult<?> execute(@RequestBody Dict args) {
        Long processTaskId = args.getLong(FlowConst.PROCESS_TASK_ID_KEY);
        String operator = LoginUserHolder.getUserId().toString();
        args.remove(FlowConst.PROCESS_TASK_ID_KEY);
        Integer submitType = args.get(FlowConst.SUBMIT_TYPE, ProcessSubmitTypeEnum.AGREE.getCode());
        args.put(FlowConst.SUBMIT_TYPE,submitType);
        if(ObjectUtil.equals(submitType,ProcessSubmitTypeEnum.ROLLBACK.getCode())) {
            // 退回上一个节点
            flowEngine.executeAndJumpTask(processTaskId,operator,args, null);
        } else if(ObjectUtil.equals(submitType,ProcessSubmitTypeEnum.REJECT.getCode())){
            // 拒绝，跳转到结束节点
            flowEngine.executeAndJumpToEnd(processTaskId,operator,args);
        } else if(ObjectUtil.equals(submitType,ProcessSubmitTypeEnum.JUMP.getCode())){
            // 跳转到指定节点
            String taskName = args.getStr(FlowConst.TASK_NAME);
            flowEngine.executeAndJumpTask(processTaskId,operator,args,taskName);
        }  else if(ObjectUtil.equals(submitType,ProcessSubmitTypeEnum.ROLLBACK_TO_OPERATOR.getCode())){
            // 退回发起人
            flowEngine.executeAndJumpToFirstTaskNode(processTaskId,operator,args);
        } else if(ObjectUtil.equals(submitType,ProcessSubmitTypeEnum.COUNTERSIGN_DISAGREE.getCode())) {
            // 会签不同意，追加不同意标识
            args.put(FlowConst.COUNTERSIGN_DISAGREE_FLAG,1);
            flowEngine.executeProcessTask(processTaskId,operator,args);
        }  else {
            // 默认执行
            flowEngine.executeProcessTask(processTaskId,operator,args);
        }
        // 存在抄送
        Object ccUserIds = args.get(FlowConst.CC_ACTORS);
        if(ObjectUtil.isNotEmpty(ccUserIds)) {
            Long processInstanceId = args.getLong(FlowConst.PROCESS_INSTANCE_ID_KEY);
            // 抄送人列表
            if(ccUserIds instanceof String) {
                flowEngine.processInstanceService().createCCInstance(processInstanceId,operator,
                        ((String) ccUserIds).split(","));
            } else if(ccUserIds instanceof Collection) {
                flowEngine.processInstanceService().createCCInstance(processInstanceId,operator,
                        ArrayUtil.toArray((Collection) ccUserIds,String.class));
            }
        }
        return CommonResult.ok();
    }
    /**
     *获取可跳转的任务节点名称
     * @param args
     * @return
     */
    @PostMapping("/wf/processTask/jumpAbleTaskNameList")
    @ApiOperation(value = "获取可跳转的任务节点名称")
    @SaCheckPermission("wf:processTask:execute")
    public CommonResult<List<LabelValueVO>> jumpAbleTaskNameList(@RequestBody Dict args) {
        Long processInstanceId = args.getLong(FlowConst.PROCESS_INSTANCE_ID_KEY);
        return CommonResult.data(processTaskService.jumpAbleTaskNameList(processInstanceId));
    }
    /**
     * 获取执行候选人
     * @param query
     * @return
     */
    @PostMapping("/wf/processTask/candidatePage")
    @ApiOperation(value = "获取执行候选人")
    @SaCheckPermission(value = {"wf:processTask:execute","wf:processTask:candidatePage"},mode = SaMode.OR)
    public CommonResult<CommonPage<Map<String,Object>>> candidatePage(@RequestBody Dict query) {
        return CommonResult.data(processTaskService.candidatePage(query));
    }
    /**
     * 委托
     * @param args
     * @return
     */
    @PostMapping("/wf/processTask/surrogate")
    @ApiOperation(value = "委托")
    @SaCheckPermission("wf:processTask:surrogate")
    public CommonResult<?> surrogate(@RequestBody Dict args) {
        Long processTaskId = args.getLong(FlowConst.PROCESS_TASK_ID_KEY);
        List<String> actors = Convert.toList(String.class,args.get(FlowConst.ACTOR_IDS_KEY));
        processTaskService.addTaskActor(processTaskId, actors);
        return CommonResult.ok();
    }
    /**
     * 加签
     * @param args
     * @return
     */
    @PostMapping("/wf/processTask/addCandidate")
    @ApiOperation(value = "加签")
    public CommonResult<?> addCandidate(@RequestBody Dict args) {
        Long processTaskId = args.getLong(FlowConst.PROCESS_TASK_ID_KEY);
        List<String> actors = Convert.toList(String.class,args.get(FlowConst.ACTOR_IDS_KEY));
        processTaskService.addCandidateActor(processTaskId, actors);
        return CommonResult.ok();
    }
}
