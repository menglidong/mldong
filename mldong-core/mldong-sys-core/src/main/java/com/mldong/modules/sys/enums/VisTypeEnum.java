package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2024/02/06
 */
@DictEnum(key = "sys_vis_log_vis_type", name = "访问类型")
public enum VisTypeEnum implements CodedEnum {
    LOGIN(1, "登录"),
    LOGOUT(2, "登出"),
    ;
    private Integer code;
    private String message;

    @JsonCreator
    public static VisTypeEnum forValue(Object value) {
        return CodedEnum.codeOf(VisTypeEnum.class, value).get();
    }

    VisTypeEnum(Integer code, String message) {
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
