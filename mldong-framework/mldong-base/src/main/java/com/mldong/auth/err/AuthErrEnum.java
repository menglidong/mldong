package com.mldong.auth.err;

import com.mldong.base.ErrEnum;

/**
 * 权限认证相关错误码
 * @author mldong
 * @date 2023/7/4
 */
public enum AuthErrEnum implements ErrEnum {
    TOKEN_NOT_EXIST(99990403,"token不存在"),
    TOKEN_EXPIRED(99990408, "token已过期，请使用refreshToken刷新token"),
    NO_RESOURCE_AUTH(99990406,"您没有资源访问权限，请联系管理员！"),
    WX_AUTH_LOGIN_EX(10041001,"微信授权登录异常"),
    NO_REL_WX(10041002,"您未绑定微信，请先绑定！"),
    USER_IS_LOCKED(10041003,"您的账号已被锁定，请联系管理员！"),
    NO_REG_PHONE(10041005,"您手机号未注册！"),
    TOKEN_NOT_MATCH_REFRESH_TOKEN(99990409,"token与refreshToken不匹配"),
    REFRESH_TOKEN_NOT_EXIST(99990410,"refreshToken不存在"),

    ;
    private Integer code;
    private String message;
    AuthErrEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
