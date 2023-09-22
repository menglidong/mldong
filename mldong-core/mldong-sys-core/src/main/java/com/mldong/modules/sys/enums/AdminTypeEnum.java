package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2022/9/7
 */
@DictEnum(key="sys_user_admin_type",name="管理员类型")
public enum AdminTypeEnum implements CodedEnum {
    SUPER_ADMIN(1,"超级管理员"),
    COMMON_ADMIN(2, "普通管理员")
    ;
    private Integer code;
    private String message;
    @JsonCreator
    public static AdminTypeEnum forValue(Object value) {
        return CodedEnum.codeOf(AdminTypeEnum.class, value).get();
    }
    AdminTypeEnum(Integer code, String message) {
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
