package com.mldong.modules.wf.engine.parser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.engine.model.TransitionModel;
import com.mldong.modules.wf.engine.model.logicflow.LfEdge;
import com.mldong.modules.wf.engine.model.logicflow.LfModel;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ModelParser {
    private ModelParser(){}

    /**
     * 将json定义文件解析成流程模型对象
     * @param bytes
     * @return
     */
    public static ProcessModel parse(byte [] bytes) {
        String json = IoUtil.readUtf8(new ByteArrayInputStream(bytes));
        LfModel lfModel = JSONUtil.parse(json).toBean(LfModel.class);
        ProcessModel processModel = new ProcessModel();
        List<LfNode> nodes = lfModel.getNodes();
        List<LfEdge> edges = lfModel.getEdges();
        if(CollectionUtil.isEmpty(nodes) || CollectionUtil.isEmpty(edges) )  {
            return processModel;
        }
        // 流程定义基本信息
        processModel.setName(lfModel.getName());
        processModel.setDisplayName(lfModel.getDisplayName());
        processModel.setType(lfModel.getType());
        processModel.setInstanceUrl(lfModel.getInstanceUrl());
        processModel.setInstanceNoClass(lfModel.getInstanceNoClass());
        processModel.setPostInterceptors(lfModel.getPostInterceptors());
        processModel.setPreInterceptors(lfModel.getPreInterceptors());
        // 流程节点信息
        nodes.forEach(node->{
            String type = node.getType().replace(NodeParser.NODE_NAME_PREFIX,"");
            NodeParser nodeParser = ServiceContext.findByName(type,NodeParser.class);
            if(nodeParser!=null) {
                nodeParser.parse(node, edges);
                NodeModel nodeModel = nodeParser.getModel();
                processModel.getNodes().add(nodeParser.getModel());
                if (nodeModel instanceof TaskModel) {
                    processModel.getTasks().add((TaskModel) nodeModel);
                }
            }
        });
        // 循环节点模型，构造输入边、输出边的source、target
        for(NodeModel node : processModel.getNodes()) {
            for(TransitionModel transition : node.getOutputs()) {
                String to = transition.getTo();
                for(NodeModel node2 : processModel.getNodes()) {
                    if(to.equalsIgnoreCase(node2.getName())) {
                        node2.getInputs().add(transition);
                        transition.setTarget(node2);
                    }
                }
            }
        }
        return processModel;
    }
}
