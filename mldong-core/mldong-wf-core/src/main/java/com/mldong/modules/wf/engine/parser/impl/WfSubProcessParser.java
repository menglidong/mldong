package com.mldong.modules.wf.engine.parser.impl;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.SubProcessModel;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;
import com.mldong.modules.wf.engine.parser.AbstractNodeParser;

/**
 * 子流程解析类
 * @author mldong
 * @date 2023/12/7
 */
public class WfSubProcessParser extends AbstractNodeParser {
    @Override
    public void parseNode(LfNode lfNode) {
        SubProcessModel subProcessModel = (SubProcessModel) nodeModel;
        Dict properties = lfNode.getProperties();
        subProcessModel.setForm(properties.getStr(FORM_KEY));
        subProcessModel.setVersion(properties.getInt(VERSION_KEY));
    }

    @Override
    public NodeModel newModel() {
        return new SubProcessModel();
    }
}
