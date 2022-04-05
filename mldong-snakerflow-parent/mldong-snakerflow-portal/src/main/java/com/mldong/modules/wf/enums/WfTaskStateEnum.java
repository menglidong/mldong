package com.mldong.modules.wf.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
import com.mldong.common.base.CodedEnum;

@DictEnum(key="wf_task_state",name="流程任务状态")
public enum WfTaskStateEnum implements CodedEnum {
    /**
     * 已完成
     */
    FINISHED(0, "已完成"),
    /**
     * 进行中
     */
    DOING(1, "进行中"),
    /**
     * 强制中止
     */
    TERMINATE(2, "强制中止")
    ;
    private int value;
    private String name;
    @JsonCreator
    public static WfTaskStateEnum forValue(int value) {
        return CodedEnum.codeOf(WfTaskStateEnum.class, value).get();

    }
    WfTaskStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
    @JsonValue
    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

}