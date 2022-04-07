package com.mldong.modules.wf.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
import com.mldong.common.base.CodedEnum;

@DictEnum(key="wf_order_state",name="流程实例状态")
public enum WfOrderStateEnum implements CodedEnum {

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
    TERMINATE(2, "强制中止"),
    /**
     * 不同意(强制中止)
     */
    DISAGREE(3, "不同意(强制中止)"),
    /**
     * 取回流程(强制中止)
     */
    TAKE_BACK(4, "取回流程(强制中止)"),
    /**
     * 作废流程(强制中止)
     */
    CANCEL(5, "作废流程(强制中止)"),
    /**
     * 驳回流程(强制中止)
     */
    REJECT(6, "驳回流程(强制中止)"),
    ;
    private int value;
    private String name;
    @JsonCreator
    public static WfOrderStateEnum forValue(int value) {
        return CodedEnum.codeOf(WfOrderStateEnum.class, value).get();

    }
    WfOrderStateEnum(int value, String name) {
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