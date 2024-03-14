package com.mldong.modules.wf.engine.handlers.impl;


import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.*;
import com.mldong.modules.wf.service.ProcessTaskService;

import java.util.List;

/**
 * 合并分支操作的处理器
 * @author mldong
 * @date 2023/5/21
 */
public class MergeBranchHandler implements IHandler {
    private JoinModel joinModel;
    public MergeBranchHandler(JoinModel joinModel) {
        this.joinModel = joinModel;
    }
    @Override
    public void handle(Execution execution) {
        // 判断是否存在正在执行的任务，存在则不允许合并
        execution.setMerged(
                execution.getEngine()
                .processTaskService()
                        .getDoingTaskList(execution.getProcessInstanceId(),findActiveNodes()).isEmpty());
    }
    /**
     * 对join节点的所有输入变迁进行递归，查找join至fork节点的所有中间task元素
     * @param node
     * @param buffer
     */
    public static void findForkTaskNames(NodeModel node, StringBuilder buffer) {
        if(node instanceof ForkModel) return;
        List<TransitionModel> inputs = node.getInputs();
        for(TransitionModel tm : inputs) {
            if(tm.getSource() instanceof TaskModel) {
                buffer.append(tm.getSource().getName()).append(",");
            }
            findForkTaskNames(tm.getSource(), buffer);
        }
    }

    /**
     * 对join节点的所有输入变迁进行递归，查找join至fork节点的所有中间task元素
     * @see MergeBranchHandler#findActiveNodes()
     */
    public String[] findActiveNodes() {
        StringBuilder buffer = new StringBuilder(20);
        findForkTaskNames(joinModel, buffer);
        String[] taskNames = buffer.toString().split(",");
        return taskNames;
    }

    /**
     * 判断流程是否可合并
     * @param processInstanceId
     * @param nodeModel
     * @return
     */
    public static boolean isMerged(Long processInstanceId, NodeModel nodeModel) {
        // 合并节点
        StringBuilder buffer = new StringBuilder(20);
        MergeBranchHandler.findForkTaskNames(nodeModel, buffer);
        String[] taskNames = buffer.toString().split(",");
        ProcessTaskService processTaskService = ServiceContext.find(ProcessTaskService.class);
        boolean isMerged = processTaskService.getDoingTaskList(processInstanceId,taskNames).isEmpty();
        return isMerged;
    }
}
