package com.mldong.modules.dev.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @Description 数据表类型枚举
 * @date 2024-01-17
 */
@DictEnum(key = "dev_schema_table_type", name = "数据表类型")
public enum SchemaTableTypeEnum implements CodedEnum {
    SINGLE_TABLE(1, "单表"),
    PRIMARY_TABLE(2, "主表"),
    SUB_TABLE(3, "附表"),
    WF_TABLE(4, "工作流表单");
    private Integer code;
    private String message;

    @JsonCreator
    public static SchemaTableTypeEnum forValue(Integer value) {
        return CodedEnum.codeOf(SchemaTableTypeEnum.class, value).get();
    }

    SchemaTableTypeEnum(Integer code, String message) {
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
