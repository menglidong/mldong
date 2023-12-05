package com.mldong.modules.wf.engine.handlers.impl;

import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.event.ProcessEvent;
import com.mldong.modules.wf.engine.event.ProcessPublisher;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.EndModel;
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
    }
}