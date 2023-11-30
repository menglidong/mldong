package com.mldong.modules.wf.engine.handlers.impl;


import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.enums.ProcessTaskPerformTypeEnum;

import java.util.List;

/**
 * 创建任务处理器
 * @author mldong
 * @date 2023/5/16
 */
public class CreateTaskHandler implements IHandler {
    private TaskModel taskModel;
    public CreateTaskHandler(TaskModel taskModel) {
        this.taskModel = taskModel;
    }
    @Override
    public void handle(Execution execution) {
        List<ProcessTask> processTaskList;
        if(ProcessTaskPerformTypeEnum.COUNTERSIGN.equals(taskModel.getPerformType())) {
            // 会签类型，创建会签任务
            processTaskList = execution.getEngine().processTaskService().createCountersignTask(taskModel, execution);
        } else {
            // 创建普通任务
            processTaskList = execution.getEngine().processTaskService().createTask(taskModel, execution);
        }
        // 将任务添加到执行对象中
        execution.addTasks(processTaskList);
    }
}
