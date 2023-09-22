package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2022/9/7
 */
@DictEnum(key="sys_role_role_type",name="角色类型")
public enum RoleTypeEnum implements CodedEnum {
    COMMON_ROLE(1,"普通角色"),
    DATA_ROLE(2,"数据角色"),
    WORKFLOW_ROLE(3, "工作流")
    ;
    private Integer code;
    private String message;
    @JsonCreator
    public static RoleTypeEnum forValue(Object value) {
        return CodedEnum.codeOf(RoleTypeEnum.class, value).get();
    }
    RoleTypeEnum(Integer code, String message) {
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
