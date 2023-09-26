package com.mldong.modules.wf.engine.model;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.enums.ProcessTaskPerformTypeEnum;
import com.mldong.modules.wf.enums.ProcessTaskTypeEnum;
import lombok.Data;

@Data
public class TaskModel extends NodeModel {
    private String form; // 表单标识
    private String assignee; // 参与人
    private String assignmentHandler; // 参与人处理类
    private ProcessTaskTypeEnum taskType; // 任务类型(主办/协办)
    private ProcessTaskPerformTypeEnum performType; // 参与类型(普通参与/会签参与)
    private String reminderTime; // 提醒时间
    private String reminderRepeat; // 重复提醒间隔
    private String expireTime; // 期待任务完成时间变量key
    private String autoExecute; // 到期是否自动执行Y/N
    private String callback; // 自动执行回调类
    private Dict ext = Dict.create(); // 自定义扩展属性
    // 候选用户标识
    private String candidateUsers; // ext.getStr("candidateUsers");
    // 候选用户组标识
    private String candidateGroups; // ext.getStr("candidateGroups");
    // 候选用户处理类字符串
    private String candidateHandler; // ext.getStr("candidateHandler");
    @Override
    public void exec(Execution execution) {
        // 执行任务节点自定义执行逻辑
        System.out.println(super.toString());
        runOutTransition(execution);
    }
}
