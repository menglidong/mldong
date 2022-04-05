package com.mldong.modules.wf.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
import com.mldong.common.base.CodedEnum;

@DictEnum(key="wf_process_state",name="流程状态")
public enum WfProcessStateEnum implements CodedEnum {
    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    private int value;
    private String name;
    @JsonCreator
    public static WfProcessStateEnum forValue(int value) {
        return CodedEnum.codeOf(WfProcessStateEnum.class, value).get();

    }
    WfProcessStateEnum(int value, String name) {
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