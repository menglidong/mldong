package com.mldong.modules.wf.engine.model.logicflow;

import com.mldong.modules.wf.engine.model.BaseModel;
import lombok.Data;

import java.util.List;
/**
 *
 * logicFlow模型
 * @author mldong
 * @date 2023/4/26
 */
@Data
public class LfModel extends BaseModel {
    private String type; // 流程定义分类
    private String expireTime;// 过期时间（常量或变量）
    private String instanceUrl; // 启动实例的url,前后端分离后，定义为路由名或或路由地址
    private String instanceNoClass; // 启动流程时，流程实例的流水号生成类
    private String preInterceptors; // 节点前置拦截器
    private String postInterceptors; // 节点后置拦截器
    private List<LfNode> nodes; // 节点集合
    private List<LfEdge> edges; // 边集合
}