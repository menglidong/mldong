package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2022/9/7
 */
@DictEnum(key = "sex", name = "性别")
public enum SexEnum implements CodedEnum {
    MAN(1, "男"),
    WOMAN(2, "女"),
    UN_KNOW(3, "未知"),
    ;
    private Integer code;
    private String message;

    @JsonCreator
    public static SexEnum forValue(Object value) {
        return CodedEnum.codeOf(SexEnum.class, value).get();
    }

    SexEnum(Integer code, String message) {
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
