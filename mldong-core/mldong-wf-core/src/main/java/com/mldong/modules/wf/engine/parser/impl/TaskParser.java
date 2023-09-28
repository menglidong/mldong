package com.mldong.modules.wf.engine.parser.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.enums.ProcessTaskPerformTypeEnum;
import com.mldong.modules.wf.enums.ProcessTaskTypeEnum;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.engine.model.logicflow.LfNode;
import com.mldong.modules.wf.engine.parser.AbstractNodeParser;

/**
 *
 * 任务节点解析类
 * @author mldong
 * @date 2023/4/26
 */
public class TaskParser extends AbstractNodeParser {

    /**
     * 解析task节点特有属性
     * @param lfNode
     */
    @Override
    public void parseNode(LfNode lfNode) {
        TaskModel taskModel = (TaskModel)nodeModel;
        Dict properties = lfNode.getProperties();
        taskModel.setForm(properties.getStr(FORM_KEY));
        taskModel.setAssignee(properties.getStr(ASSIGNEE_KEY));
        taskModel.setAssignmentHandler(properties.getStr(ASSIGNMENT_HANDLE_KEY));
        taskModel.setTaskType(ProcessTaskTypeEnum.codeOf(properties.getInt(TASK_TYPE_KEY)));
        taskModel.setPerformType(ProcessTaskPerformTypeEnum.codeOf(properties.getInt(PERFORM_TYPE_KEY)));
        taskModel.setReminderTime(properties.getStr(REMINDER_TIME_KEY));
        taskModel.setReminderRepeat(properties.getStr(REMINDER_REPEAT_KEY));
        taskModel.setExpireTime(properties.getStr(EXPIRE_TIME_KEY));
        taskModel.setAutoExecute(properties.getStr(AUTH_EXECUTE_KEY));
        taskModel.setCallback(properties.getStr(CALLBACK_KEY));
        // 自定义扩展属性
        Object field = properties.get(EXT_FIELD_KEY);
        if(field!=null) {
            Dict ext = Convert.convert(Dict.class, field);
            taskModel.setExt(ext);
            taskModel.setCandidateUsers(ext.getStr(EXT_FIELD_CANDIDATE_USERS_KET));
            taskModel.setCandidateGroups(ext.getStr(EXT_FIELD_CANDIDATE_GROUPS_KEY));
            taskModel.setCandidateHandler(ext.getStr(EXT_FIELD_CANDIDATE_HANDLER_KEY));
        }
    }

    @Override
    public NodeModel newModel() {
        return new TaskModel();
    }
}