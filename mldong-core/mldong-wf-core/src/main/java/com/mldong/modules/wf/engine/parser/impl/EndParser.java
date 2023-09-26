package com.mldong.modules.wf.engine.parser.impl;

import com.mldong.modules.wf.engine.model.EndModel;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;
import com.mldong.modules.wf.engine.parser.AbstractNodeParser;
/**
 *
 * 结束节点解析类
 * @author mldong
 * @date 2023/4/26
 */
public class EndParser extends AbstractNodeParser {

    @Override
    public void parseNode(LfNode lfNode) {

    }

    @Override
    public NodeModel newModel() {
        return new EndModel();
    }
}
