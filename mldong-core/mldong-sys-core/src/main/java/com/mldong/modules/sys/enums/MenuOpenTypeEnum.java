package com.mldong.modules.sys.enums;


import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2022/9/7
 */
@DictEnum(key="sys_menu_open_type",name="菜单打开方式")
public enum MenuOpenTypeEnum implements CodedEnum {
    NONE(0,"无"),
    COMPONENT(1, "组件"),
    IFRAME(2,"内部链接"),
    LINK(3,"外部链接"),
    ;
    private Integer code;
    private String message;
    MenuOpenTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
