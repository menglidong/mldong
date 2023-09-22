package com.mldong.modules.sys.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2022/9/7
 */
@DictEnum(key="sys_menu_type",name="菜单类型")
public enum MenuTypeEnum implements CodedEnum {
    DIR(1,"目录"),
    MENU(2,"菜单"),
    BUTTON(3,"按钮"),
    INTERFACE(4, "接口")
    ;
    private Integer code;
    private String message;
    @JsonCreator
    public static MenuTypeEnum forValue(Object value) {
        return CodedEnum.codeOf(MenuTypeEnum.class, value).get();
    }
    MenuTypeEnum(Integer code, String message) {
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

