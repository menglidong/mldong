package com.mldong.auth;

import cn.dev33.satoken.fun.SaParamFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import com.mldong.web.LoginUserHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author mldong
 * @date 2023/9/20
 */
public class AuthInterceptor extends SaInterceptor {
    public AuthInterceptor(SaParamFunction<Object> auth) {
        super(auth);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUser loginUser = LoginUserHolder.me();
        if(loginUser!=null && loginUser.isSuperAdmin()) {
            // 超级管理员直接放行
            return true;
        }
        return super.preHandle(request, response, handler);
    }
}
