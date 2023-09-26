package com.mldong.modules.wf.engine.parser.impl;


import com.mldong.modules.wf.engine.model.JoinModel;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;
import com.mldong.modules.wf.engine.parser.AbstractNodeParser;

/**
 *
 * 合并节点解析器
 * @author mldong
 * @date 2023/4/26
 */
public class JoinParser extends AbstractNodeParser {

    @Override
    public void parseNode(LfNode lfNode) {

    }

    @Override
    public NodeModel newModel() {
        return new JoinModel();
    }
}
