package com.mldong.modules.wf.engine.parser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.TransitionModel;
import com.mldong.modules.wf.engine.model.logicflow.LfEdge;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;

import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * 通用属性解析（基本属性和边）
 * @author mldong
 * @date 2023/4/26
 */
public abstract class AbstractNodeParser implements NodeParser {
    // 节点模型对象
    protected NodeModel nodeModel;
    @Override
    public void parse(LfNode lfNode, List<LfEdge> edges) {
        nodeModel = newModel();
        // 解析基本信息
        nodeModel.setName(lfNode.getId());
        if(ObjectUtil.isNotNull(lfNode.getText())) {
            nodeModel.setDisplayName(lfNode.getText().getStr(TEXT_VALUE_KEY));
        }
        Dict properties = lfNode.getProperties();
        // 解析布局属性
        int x = lfNode.getX();
        int y = lfNode.getY();
        int w = Convert.toInt(properties.get(WIDTH_KEY),0);
        int h = Convert.toInt(properties.get(HEIGHT_KEY),0);
        nodeModel.setLayout(StrUtil.format("{},{},{},{}",x,y,w,h));
        // 解析拦截器
        nodeModel.setPreInterceptors(properties.getStr(PRE_INTERCEPTORS_KEY));
        nodeModel.setPostInterceptors(properties.getStr(POST_INTERCEPTORS_KEY));
        // 解析输出边
        List<LfEdge> nodeEdges = getEdgeBySourceNodeId(lfNode.getId(), edges);
        nodeEdges.forEach(edge->{
            TransitionModel transitionModel = new TransitionModel();
            transitionModel.setName(edge.getId());
            transitionModel.setTo(edge.getTargetNodeId());
            transitionModel.setSource(nodeModel);
            transitionModel.setExpr(edge.getProperties().getStr(EXPR_KEY));
            if(CollectionUtil.isNotEmpty(edge.getPointsList())) {
                // x1,y1;x2,y2;x3,y3……
                transitionModel.setG(edge.getPointsList().stream().map(point->{
                    return point.getX()+","+point.getY();
                }).collect(Collectors.joining(";")));
            } else {
                if(ObjectUtil.isNotNull(edge.getStartPoint()) && ObjectUtil.isNotNull(edge.getEndPoint())) {
                    int startPointX = edge.getStartPoint().getX();
                    int startPointY = edge.getStartPoint().getY();
                    int endPointX = edge.getEndPoint().getX();
                    int endPointY = edge.getEndPoint().getY();
                    transitionModel.setG(StrUtil.format("{},{};{},{}", startPointX, startPointY, endPointX, endPointY));
                }
            }
            nodeModel.getOutputs().add(transitionModel);
        });
        // 调用子类特定解析方法
        parseNode(lfNode);
    }

    /**
     * 子类实现此类完成特定解析
     * @param lfNode
     */
    public abstract void parseNode(LfNode lfNode);

    /**
     * 由子类各自创建节点模型对象
     * @return
     */
    public abstract NodeModel newModel();
    @Override
    public NodeModel getModel() {
        return nodeModel;
    }
    /**
     * 获取节点输入
     * @param targetNodeId 目标节点id
     * @param edges
     * @return
     */
    private List<LfEdge> getEdgeByTargetNodeId(String targetNodeId,List<LfEdge> edges) {
        return edges.stream().filter(edge->{
            return edge.getTargetNodeId().equals(targetNodeId);
        }).collect(Collectors.toList());
    }
    /**
     * 获取节点输出
     * @param sourceNodeId 源节点id
     * @param edges
     * @return
     */
    private List<LfEdge> getEdgeBySourceNodeId(String sourceNodeId,List<LfEdge> edges) {
        return edges.stream().filter(edge->{
            return edge.getSourceNodeId().equals(sourceNodeId);
        }).collect(Collectors.toList());
    }
}
