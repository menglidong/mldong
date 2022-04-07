package com.mldong.modules.wf.enums;

/**
 * @author mldong
 * @date 2022/4/7
 */
public interface WfConstants {
    public final static String ORDER_STATE_KEY = "orderState"; // 自定义实例状态key
    public final static String ORDER_USER_REAL_NAME_KEY = "operator.realName"; // 用户姓名key
    public final static String ORDER_USER_NAME_KEY = "operator.userName"; // 用户名key
    public final static String APPROVAL_TYPE = "approvalType"; // 类型
    public final static String REMARK = "remark"; // 原因
    public final static String TARGET_NODE_NAME = "targetNodeName"; // 目地节点名称，用于退回到指定节点的
    public final static String FIRST_TASK_NAME = "apply";

}
