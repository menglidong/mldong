package com.mldong.security.component;

import com.mldong.common.access.AccessInitProcessor;
import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.SsoUser;
import com.mldong.common.interceptor.AuthInterceptorService;
import com.mldong.common.tool.CxtTool;
import com.mldong.common.tool.StringTool;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthManager {
    @Autowired
    private AuthInterceptorService authInterceptorService;
    public boolean hasAccess(HttpServletRequest request, Authentication authentication) throws Exception {
        Object principal = authentication.getPrincipal();
        if(principal == null){
            return false;
        }

        HandlerMapping handlerMapping = CxtTool.getApplicationContext().getBean(HandlerMapping.class);
        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
        if(handlerExecutionChain!=null) {
            Object handler = handlerExecutionChain.getHandler();
            if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                AuthIgnore authIgnore = handlerMethod.getMethodAnnotation(AuthIgnore.class);
                if (null != authIgnore) {
                    // 要忽略权限
                    return true;
                }
                if("anonymousUser".equals(authentication.getPrincipal())) {
                    throw new AccessDeniedException("未登录");
                }
                ApiOperation apiOperation =  handlerMethod.getMethodAnnotation(ApiOperation.class);
                String access = AccessInitProcessor.getAccess(apiOperation);
                if (StringTool.isEmpty(access)) {
                    // 没有定义，直接放行
                    return true;
                }
                if(authentication instanceof AnonymousAuthenticationToken){
                    //check if this uri can be access by anonymous
                    //return
                    throw new AccessDeniedException("未登录");
                }
                SsoUser user = (SsoUser) authentication.getPrincipal();
                if (!authInterceptorService.hasAuth(user.getUserId(), access)) {
                    return false;
                }
            }
        }
       return true;
    }
}
