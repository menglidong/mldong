package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.core.Execution;

/**
 *
 * 分支模型
 * @author mldong
 * @date 2023/4/25
 */
public class ForkModel extends NodeModel {
    @Override
    public void exec(Execution execution) {
        // 执行分支节点自定义执行逻辑
        runOutTransition(execution);
    }
}