package com.mldong.auth;

import cn.dev33.satoken.fun.SaParamFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.auth.err.AuthErrEnum;
import com.mldong.exception.ServiceException;
import com.mldong.properties.GlobalProperties;
import com.mldong.web.LoginUserHolder;
import org.springframework.core.env.Environment;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 自定义拦截器
 * @author mldong
 * @date 2023/9/20
 */
public class AuthInterceptor extends SaInterceptor {
    public AuthInterceptor(SaParamFunction<Object> auth) {
        super(auth);
    }
    private static final Environment environment = SpringUtil.getBean(Environment.class);
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private static final String profileActive = environment.getProperty("spring.profiles.active",String.class);
    private static final GlobalProperties globalProperties = SpringUtil.getBean(GlobalProperties.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUser loginUser = LoginUserHolder.me();
        if(loginUser!=null && loginUser.isSuperAdmin()) {
            // 超级管理员直接放行
            return true;
        }
        if(ObjectUtil.equals(profileActive,"demo")) {
            List<String> blacklist = globalProperties.getBlacklist();
            // 演示环境，无权限操作
            String uri = request.getRequestURI();
            if(CollectionUtil.isNotEmpty(blacklist) && blacklist.stream().anyMatch(s -> antPathMatcher.match(s,uri))) {
                ServiceException.throwBiz(AuthErrEnum.NO_RESOURCE_AUTH);
            }
        }
        return super.preHandle(request, response, handler);
    }
}
