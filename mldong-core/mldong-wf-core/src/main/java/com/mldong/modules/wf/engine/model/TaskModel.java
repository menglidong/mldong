package com.mldong.modules.wf.engine.model;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.impl.CountersignHandler;
import com.mldong.modules.wf.enums.ProcessTaskPerformTypeEnum;
import com.mldong.modules.wf.enums.ProcessTaskTypeEnum;
import com.mldong.modules.wf.enums.CountersignTypeEnum;
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
    // 会签类型 PARALLEL表示并行会签，SEQUENTIAL表示串行会签
    private CountersignTypeEnum countersignType;
    // 会签完成条件
    /**
     * ● 全部完成：为空
     * ● 按数量通过：#nrOfCompletedInstances==n，这里表示n人完成任务，会签结束。
     * ● 按比例通过：#nrOfCompletedInstances/nrOfInstances==n，这里表示已完成会签数与总实例数达到一定比例时，会签结束
     * ● 一票通过：#nrOfCompletedInstances==1，这里表示1人完成任务，会签结束。
     * ● 一票否决：ONE_VOTE_VETO
     */
    private String countersignCompletionCondition;
    @Override
    public void exec(Execution execution) {
        // 执行任务节点自定义执行逻辑
        System.out.println(super.toString());
        if(ProcessTaskPerformTypeEnum.COUNTERSIGN.equals(performType)) {
            // 会签任务处理
            fire(new CountersignHandler(this),execution);
            if(execution.isMerged()) {
                runOutTransition(execution);
            }
        } else {
            runOutTransition(execution);
        }
    }
}
