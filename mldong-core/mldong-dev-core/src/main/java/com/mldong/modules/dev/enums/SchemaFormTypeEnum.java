package com.mldong.modules.dev.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @Description 表单类型枚举
 * @date 2024-01-17
 */
@DictEnum(key = "dev_schema_form_type", name = "模型表单类型")
public enum SchemaFormTypeEnum implements CodedEnum {
    POP_UP_WINDOW(1, "弹窗"),
    DRAWER(2, "抽屉"),
    NEW_WINDOW(3, "新窗口");
    private Integer code;
    private String message;

    @JsonCreator
    public static SchemaFormTypeEnum forValue(Integer value) {
        return CodedEnum.codeOf(SchemaFormTypeEnum.class, value).get();
    }

    SchemaFormTypeEnum(Integer code, String message) {
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
