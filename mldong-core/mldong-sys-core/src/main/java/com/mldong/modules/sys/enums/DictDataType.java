package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * 字典数据类型
 * @author mldong
 * @date 2023/7/3
 */
@DictEnum(key = "sys_dict_data_type", name = "字典数据类型")
public enum DictDataType  implements CodedEnum {
    STRING(1, "字符串"),
    INTEGER(2, "整型"),
    ;
    private Integer code;
    private String message;

    @JsonCreator
    public static DictDataType forValue(Object value) {
        return CodedEnum.codeOf(DictDataType.class, value).get();
    }

    DictDataType(Integer code, String message) {
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
