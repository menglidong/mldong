package com.mldong.modules.wf.engine.handlers.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionUtil;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.enums.CountersignTypeEnum;
import com.mldong.modules.wf.enums.FlowConst;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会签任务处理，用于判断会签任务是否可通过
 * @author mldong
 * @date 2023/11/24
 */
public class CountersignHandler implements IHandler {
    private TaskModel taskModel;
    public CountersignHandler(TaskModel taskModel) {
        this.taskModel = taskModel;
    }
    @Override
    public void handle(Execution execution) {
        boolean isMerged = false;
        String countersignType = taskModel.getExt().get("countersignType","PARALLEL");
        String countersignCompletionCondition = taskModel.getExt().get("countersignCompletionCondition","");
        String prefix = FlowConst.COUNTERSIGN_VARIABLE_PREFIX +taskModel.getName()+"_";
        // 会签办理人列表
        List<String> operatorList = Convert.toList(String.class,execution.getArgs().get(prefix+FlowConst.COUNTERSIGN_OPERATOR_LIST));
        // 循环计数器，办理人在列表中的索引
        int loopCounter = operatorList.indexOf(execution.getOperator());
        // 追加计数器
        execution.getArgs().put(prefix+FlowConst.LOOP_COUNTER,loopCounter);
        // 追加已完成数量
        execution.getArgs().put(prefix + FlowConst.NR_OF_COMPLETED_INSTANCES,
                execution.getArgs().get(prefix + FlowConst.NR_OF_COMPLETED_INSTANCES,0)+1);
        /**
         * ● 全部通过：为空
         * ● 按数量通过：#nrOfCompletedInstances==n，这里表示n人完成任务，会签结束。
         * ● 按比例通过：#nrOfCompletedInstances/nrOfInstances==n，这里表示已完成会签数与总实例数达到一定比例时，会签结束
         * ● 一票通过：#nrOfCompletedInstances==1，这里表示1人完成任务，会签结束。
         * ● 一票否决：ONE_VOTE_VETO
         */
        if("ONE_VOTE_VETO".equalsIgnoreCase(countersignCompletionCondition)) {
            // 一票否决
            if(execution.getArgs().containsKey(FlowConst.COUNTERSIGN_DISAGREE_FLAG)) {
                // 存在拒绝标识，则直接通过
                isMerged = true;
            }
        } else if(!StrUtil.isBlank(countersignCompletionCondition)) {
            // 根据条件判断是否通过
            Dict dict = Dict.create();
            execution.getArgs().forEach((k,v)->{
                dict.set(k.replace(prefix,""),v);
            });
            isMerged = Convert.toBool(ExpressionUtil.eval(countersignCompletionCondition, dict));
        }
        if(!isMerged && CountersignTypeEnum.SEQUENTIAL.toString().equalsIgnoreCase(countersignType)) {
            // 串行未通过，则判断是否为最后一个
            if (loopCounter == operatorList.size() - 1) {
                isMerged = true;
            } else {
                // 非最后一个，则继续创建会签任务
                execution.getEngine().processTaskService().createCountersignTask(taskModel, execution);
            }
        }
        if(!isMerged && CountersignTypeEnum.PARALLEL.toString().equalsIgnoreCase(countersignType)) {
            // 是否所有会签任务已完成
            isMerged = execution.getEngine().processTaskService().getDoingTaskList(execution.getProcessInstanceId(), new String[]{taskModel.getName()}).size()==0;
            if(!isMerged) {
                // 未通过，更新已完成实例数量
                Dict addVariable = Dict.create();
                addVariable.put(prefix + FlowConst.NR_OF_COMPLETED_INSTANCES, execution.getArgs().get(prefix+FlowConst.NR_OF_COMPLETED_INSTANCES));
                execution.getEngine().processInstanceService().addVariable(execution.getProcessInstanceId(), addVariable);
            }
        }
        if(isMerged) {
            // 获取所有会签参数键值
            List<String> keys = execution.getArgs().keySet().stream().filter(k->k.startsWith(prefix)).collect(Collectors.toList());
            keys.add(FlowConst.COUNTERSIGN_DISAGREE_FLAG);
            // 如果可以合并，则把流程实例中的会签参数清空
            execution.getEngine().processInstanceService().removeVariable(
                    execution.getProcessInstanceId(),
                    keys.toArray(new String[]{}));
            // 如果为并行会签，需将其他会签任务设置为废弃
            if(CountersignTypeEnum.PARALLEL.toString().equalsIgnoreCase(countersignType)) {
                execution.getEngine().processTaskService().getDoingTaskList(execution.getProcessInstanceId(), new String[]{taskModel.getName()}).stream().forEach(t->{
                    execution.getEngine().processTaskService().abandonProcessTask(t.getId(), FlowConst.AUTO_ID, execution.getArgs());
                });
            }
        }
        execution.setMerged(isMerged);
    }
}
