package com.mldong.modules.wf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.snaker.engine.model.FieldModel;

import java.util.List;

@ApiModel
public class WfTaskModelVO {
    @ApiModelProperty(value = "元素名称")
    private String name;
    @ApiModelProperty(value = "显示名称")
    private String displayName;
    @ApiModelProperty(value = "表单标识")
    private String form;
    @ApiModelProperty(value = "参与者变量名称")
    private String assignee;
    @ApiModelProperty(value = "参与方式")
    private String performType;
    @ApiModelProperty(value = "任务类型")
    private String taskType;
    @ApiModelProperty(value = "期望完成时间")
    private String expireTime;
    @ApiModelProperty(value = "提醒时间")
    private String reminderTime;
    @ApiModelProperty(value = "提醒间隔(分钟)")
    private String reminderRepeat;
    @ApiModelProperty(value = "是否自动执行")
    private String autoExecute;
    @ApiModelProperty(value = "任务执行后回调类")
    private String callback;
    @ApiModelProperty(value = "分配参与者处理类型")
    private String assignmentHandler;
    @ApiModelProperty(value = "字段模型集合")
    private List<FieldModel> fields = null;
    @ApiModelProperty(value = "候选用户")
    private String candidateUsers;
    @ApiModelProperty(value = "候选用户组")
    private String candidateGroups;
    @ApiModelProperty(value = "候选用户处理类")
    private String candidateHandler;
    @ApiModelProperty(value = "参与者处理类可根据用户组标识获取参与者")
    private String groupKey;
    @ApiModelProperty(value = "参与者处理类可根据用户标识获取参与者")
    private String userKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getPerformType() {
        return performType;
    }

    public void setPerformType(String performType) {
        this.performType = performType;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getReminderRepeat() {
        return reminderRepeat;
    }

    public void setReminderRepeat(String reminderRepeat) {
        this.reminderRepeat = reminderRepeat;
    }

    public String getAutoExecute() {
        return autoExecute;
    }

    public void setAutoExecute(String autoExecute) {
        this.autoExecute = autoExecute;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getAssignmentHandler() {
        return assignmentHandler;
    }

    public void setAssignmentHandler(String assignmentHandler) {
        this.assignmentHandler = assignmentHandler;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    public String getCandidateUsers() {
        candidateUsers = getAttr("candidateUsers");
        return candidateUsers;
    }

    public void setCandidateUsers(String candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public String getCandidateGroups() {
        candidateGroups = getAttr("candidateGroups");
        return candidateGroups;
    }

    public void setCandidateGroups(String candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public String getCandidateHandler() {
        candidateHandler = getAttr("candidateHandler");
        return candidateHandler;
    }

    public void setCandidateHandler(String candidateHandler) {
        this.candidateHandler = candidateHandler;
    }

    public String getGroupKey() {
        groupKey = getAttr("groupKey");
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getUserKey() {
        userKey = getAttr("userKey");
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    private String getAttr(String key) {
        if(this.getFields() == null) {
            return null;
        }
        FieldModel fieldModel = this.getFields().stream().filter(fm -> {
            return "ext".equals(fm.getName());
        }).findAny().orElse(null);
        if(fieldModel!=null) {
            return fieldModel.getAttrMap().get(key);
        }
        return null;
    }
}
