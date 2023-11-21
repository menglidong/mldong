package com.mldong.modules.wf.enums;
/**
 *
 * 流程常量
 * @author mldong
 * @date 2023/6/9
 */
public interface FlowConst {
    // 业务流程号
    String BUSINESS_NO = "BUSINESS_NO";
    // 超级管理员ID
    String ADMIN_ID = "flow.admin";
    // 自动执行ID
    String AUTO_ID = "flow.auto";
    String PROCESS_NAME_KEY = "name";
    String PROCESS_DISPLAY_NAME_KEY = "displayName";
    String PROCESS_TYPE = "type";
    // 流程定义id，key
    String PROCESS_DEFINE_ID_KEY = "processDefineId";
    // 流程设计id，key
    String PROCESS_DESIGN_ID_KEY = "processDesignId";
    // 流程任务id
    String PROCESS_TASK_ID_KEY = "processTaskId";
    // 流程实例id
    String PROCESS_INSTANCE_ID_KEY = "processInstanceId";
    // 表单数据前辍
    String FORM_DATA_PREFIX = "f_";
    // 任务表单数据前辍
    String TASK_FORM_DATA_PREFIX = "tf_";
    // 审批意见
    String APPROVAL_COMMENT = "tf_approvalComment";
    // 审批提交附件
    String APPROVAL_ATTACHMENT = "tf_approvalAttachment";
    // 下一节点执行人
    String NEXT_NODE_OPERATOR = "tf_nextNodeOperator";
    // 用户ID
    String USER_USER_ID = "u_userId";
    // 用户姓名
    String USER_REAL_NAME = "u_realName";
    // 用户所属部门ID
    String USER_DEPT_ID = "u_deptId";
    // 用户所属部门名称
    String USER_DEPT_NAME = "u_deptName";
    // 用户所属岗位id
    String USER_POST_ID = "u_postId";
    // 用户所属岗位名称
    String USER_POST_NAME = "u_postName";
    // 提交类型
    String SUBMIT_TYPE = "submitType";
    // 自动生成的标题
    String AUTO_GEN_TITLE = "autoGenTitle";
    // 节点名称
    String TASK_NAME = "taskName";
    // 是否第一个任务节点
    String IS_FIRST_TASK_NODE = "isFirstTaskNode";
}
