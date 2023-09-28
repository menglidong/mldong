package com.mldong.modules.biz.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * 请假原因类型
 * @author mldong
 * @date 2023/9/28
 */
@DictEnum(key="biz_leave_reason_type",name="请假原因类型")
public enum LeaveReasonTypeEnum implements CodedEnum {
    MARRIAGE(1,"婚假"),
    SICK(2,"病假"),
    ABSENCE(3, "事假"),
    ANNUAL(4, "年假"),
    OTHER(9, "其他"),
    ;
    private Integer code;
    private String message;
    @JsonCreator
    public static LeaveReasonTypeEnum forValue(Object value) {
        return CodedEnum.codeOf(LeaveReasonTypeEnum.class, value).get();
    }
    LeaveReasonTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @JsonValue
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
