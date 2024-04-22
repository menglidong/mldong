package com.mldong.captcha;

import cn.hutool.extra.spring.SpringUtil;
import com.mldong.annotation.CaptchaValid;
import com.mldong.exception.ServiceException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mldong
 * @date 2024/2/29
 */
public class CaptchaInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            CaptchaValid captchaValid = handlerMethod.getMethodAnnotation(CaptchaValid.class);
            if(captchaValid!=null) {
                CaptchaManager captchaManager = SpringUtil.getBean(CaptchaManager.class);
                if(!captchaManager.validate(request, captchaValid)){
                    ServiceException.throwBiz(99999999, "图片验证码错误");
                }
            }
        }
        return true;
    }
}
