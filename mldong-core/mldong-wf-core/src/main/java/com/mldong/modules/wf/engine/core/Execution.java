package com.mldong.modules.wf.engine.core;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 执行对象参数
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class Execution {
    // 流程实例ID
    private Long processInstanceId;
    // 当前流程任务ID
    private Long processTaskId;
    // 执行对象扩展参数
    private Dict args;
    // 当前流程模型
    private ProcessModel processModel;
    // 当前任务
    private ProcessTask processTask;
    // 当前流程实例
    private ProcessInstance processInstance;
    // 所有任务集合
    private List<ProcessTask> processTaskList = new ArrayList<>();
    // 是否可合并
    private boolean isMerged;
    // 流程引擎对象
    private FlowEngine engine;
    // 操作人
    private String operator;
    // 当前节点模型
    private NodeModel nodeModel;
    /**
     * 添加任务到任务集合
     * @param processTask
     */
    public void addTask(ProcessTask processTask) {
        this.processTaskList.add(processTask);
    }

    /**
     * 添加任务集合
     * @param processTasks
     */
    public void addTasks(List<ProcessTask> processTasks) {
        this.processTaskList.addAll(processTasks);
    }
    /**
     * 获取正在进行中的任务列表
     * @return
     */
    public List<ProcessTask> getDoingTaskList() {
        return this.processTaskList.stream().filter(item->{
            return ProcessTaskStateEnum.DOING.getCode().equals(item.getTaskState());
        }).collect(Collectors.toList());
    }
 }