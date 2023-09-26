package com.mldong.modules.wf.engine.handlers.impl;


import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.IHandler;
import com.mldong.modules.wf.engine.model.*;

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
    private void findForkTaskNames(NodeModel node, StringBuilder buffer) {
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
}
