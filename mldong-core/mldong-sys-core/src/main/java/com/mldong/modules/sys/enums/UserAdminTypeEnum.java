package com.mldong.modules.sys.enums;

import java.util.Arrays;

/**
 * 用户类型的枚举
 */
public enum UserAdminTypeEnum {
    ADMIN(1,"超级管理员"),
    COMMON(2,"普通用户"),
    ;
    private Integer code;
    private String message;
    UserAdminTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 由code转enum
     * @param code
     * @return
     */
    public static UserAdminTypeEnum codeToEnum(Integer code) {
        return Arrays.stream(UserAdminTypeEnum.values()).filter(item->{
            return item.code.equals(code);
        }).findFirst().orElse(UserAdminTypeEnum.COMMON);
    }
    public Integer getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}
