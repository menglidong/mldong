package com.mldong.modules.wf.engine.handlers.impl;

import cn.hutool.core.util.ObjectUtil;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.event.ProcessEvent;
import com.mldong.modules.wf.engine.event.ProcessPublisher;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.EndModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.model.SubProcessModel;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.enums.ProcessEventTypeEnum;

/**
 *
 * 结束流程实例的处理器
 * @author mldong
 * @date 2023/5/29
 */
public class EndProcessHandler implements IHandler {
    private EndModel endModel;
    public EndProcessHandler(EndModel endModel) {
        this.endModel = endModel;
    }
    @Override
    public void handle(Execution execution) {
        execution.getEngine().processInstanceService().finishProcessInstance(execution.getProcessInstanceId());
        // 发布流程实例结束事件
        ProcessPublisher.notify(ProcessEvent.builder().eventType(ProcessEventTypeEnum.PROCESS_INSTANCE_END).sourceId(execution.getProcessInstanceId()).build());
        ProcessInstance processInstance = execution.getProcessInstance();
        if(ObjectUtil.isNotNull(processInstance.getParentId())) {
            // 如果子流程存在父流程实例，则执行父流程的子流程节点模型方法
            ProcessInstance parentInstance = execution.getEngine().processInstanceService().getById(processInstance.getParentId());
            if(parentInstance == null) return;
            ProcessModel pm = execution.getEngine().processDefineService().getProcessModel(parentInstance.getProcessDefineId());
            if(pm == null) return;
            SubProcessModel spm = (SubProcessModel)pm.getNode(processInstance.getParentNodeName());
            Execution newExecution = new Execution();
            newExecution.setEngine(execution.getEngine());
            newExecution.setProcessModel(pm);
            newExecution.setProcessInstance(parentInstance);
            newExecution.setProcessInstanceId(parentInstance.getId());
            newExecution.setArgs(execution.getArgs());
            spm.execute(newExecution);
            execution.addTasks(newExecution.getProcessTaskList());
        }
    }
}