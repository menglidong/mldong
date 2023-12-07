package com.mldong.modules.wf.engine.parser;

import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.logicflow.LfEdge;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;

import java.util.List;
/**
 *
 * 节点解析接口
 * @author mldong
 * @date 2023/4/26
 */
public interface NodeParser {
    String NODE_NAME_PREFIX="snaker:"; // 节点名称前辍
    String TEXT_VALUE_KEY = "value"; // 文本值
    String WIDTH_KEY = "width"; // 节点宽度
    String HEIGHT_KEY = "height"; // 节点高度
    String PRE_INTERCEPTORS_KEY = "preInterceptors"; // 前置拦截器
    String POST_INTERCEPTORS_KEY = "postInterceptors"; // 后置拦截器
    String EXPR_KEY = "expr"; // 表达式key
    String HANDLE_CLASS_KEY = "handleClass"; // 表达式处理类
    String FORM_KEY = "form"; // 表单标识
    String ASSIGNEE_KEY = "assignee"; // 参与人
    String ASSIGNMENT_HANDLE_KEY = "assignmentHandler"; // 参与人处理类
    String TASK_TYPE_KEY = "taskType"; // 任务类型(主办/协办)
    String PERFORM_TYPE_KEY = "performType"; // 参与类型(普通参与/会签参与)
    String REMINDER_TIME_KEY = "reminderTime"; // 提醒时间
    String REMINDER_REPEAT_KEY = "reminderRepeat"; // 重复提醒间隔
    String EXPIRE_TIME_KEY = "expireTime"; // 期待任务完成时间变量key
    String AUTH_EXECUTE_KEY = "autoExecute"; // 到期是否自动执行Y/N
    String CALLBACK_KEY = "callback"; // 自动执行回调类
    String EXT_FIELD_KEY = "field"; // 自定义扩展属性
    String EXT_FIELD_CANDIDATE_USERS_KET = "candidateUsers";
    String EXT_FIELD_CANDIDATE_GROUPS_KEY = "candidateGroups";
    String EXT_FIELD_CANDIDATE_HANDLER_KEY = "candidateHandler";
    String EXT_FIELD_COUNTERSIGN_TYPE_KEY = "countersignType"; // 会签类型
    String EXT_FIELD_COUNTERSIGN_COMPLETION_CONDITION_KEY = "countersignCompletionCondition"; // 会签完成条件
    String CLASS_KEY = "clazz"; // 类路径
    String METHOD_NAME_KEY = "methodName"; // 方法名
    String ARGS_KEY = "args"; // 方法入参
    String RETURN_VAL_KEY = "val"; // 返回变量名
    String VERSION_KEY = "version"; // 版本号
    /**
     * 节点属性解析方法，由解析类完成解析
     * @param lfNode LogicFlow节点对象
     * @param edges 所有边对象
     */
    void parse(LfNode lfNode, List<LfEdge> edges);

    /**
     * 解析完成后，提供返回NodeModel对象
     * @return 节点模型
     */
    NodeModel getModel();
}
