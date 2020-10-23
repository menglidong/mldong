package com.mldong.common.support;


import com.mldong.common.annotation.LoginUser;
import com.mldong.common.token.TokenStrategy;
import com.mldong.common.web.RequestHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private TokenStrategy tokenStrategy;

    public LoginUserMethodArgumentResolver(TokenStrategy tokenStrategy) {
        this.tokenStrategy = tokenStrategy;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(Long.class)&&methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = RequestHolder.getToken();
        return tokenStrategy.getUserId(token);
    }
}
