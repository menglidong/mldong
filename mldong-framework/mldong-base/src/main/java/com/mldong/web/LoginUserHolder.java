package com.mldong.web;

import com.mldong.auth.LoginUser;

/**
 * 当前登录用户持有工具
 * @author mldong
 * @date 2022-09-08
 */
public class LoginUserHolder {
    private final static ThreadLocal<LoginUser> CURRENT_USER = new ThreadLocal<>();
    public static void set(LoginUser loginUser) {
        CURRENT_USER.set(loginUser);
    }
    public static LoginUser me() {
        return CURRENT_USER.get();
    }

    /**
     * 获取当前用户id,0L为系统自动
     * @return
     */
    public static Long getUserId() {
        LoginUser loginUser = me();
        if(loginUser==null) return 0L;
        return loginUser.getId();
    }
    /**
     * 获取当前用户名,空则为系统自动
     * @return
     */
    public static String getUserName() {
        LoginUser loginUser = me();
        if(loginUser==null) return "system_auto";
        return loginUser.getUserName();
    }
    /**
     * 获取当前用户姓名,空则为系统自动
     * @return
     */
    public static String getRealName() {
        LoginUser loginUser = me();
        if(loginUser==null) return "系统自动";
        return loginUser.getRealName();
    }
    public static void remove() {
        CURRENT_USER.remove();
    }
}
