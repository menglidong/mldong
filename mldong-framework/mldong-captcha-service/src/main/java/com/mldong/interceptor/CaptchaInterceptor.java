package com.mldong.interceptor;

import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.tool.StringTool;
import com.mldong.config.CaptchaConfiguration;
import com.mldong.config.CaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@ConditionalOnBean({CaptchaConfiguration.class, RedisTemplate.class})
public class CaptchaInterceptor implements HandlerInterceptor {
    @Autowired
    private CaptchaProperties properties;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${spring.profiles.active}")
    private String profiles;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if("dev".equals(profiles) || "test".equals(profiles)) {
            // 开发环境与测试环境不校验验收码
            return true;
        }
        if(request.getRequestURI().contains("login") || request.getRequestURI().contains("Login") ) {
            String captcha = getCaptcha(request);
            if(StringTool.isEmpty(captcha)) {
                throw new BizException(GlobalErrEnum.GL99990012);
            }
            String uuid = getUUID(request);
            if(StringTool.isEmpty(uuid)) {
                throw new BizException(GlobalErrEnum.GL99990012);
            }
            String cacheCaptcha = redisTemplate.opsForValue().get(CommonConstants.CAPTCHA_CODE_KEY+uuid);
            if(!captcha.equals(cacheCaptcha)) {
                throw new BizException(GlobalErrEnum.GL99990012);
            }
            redisTemplate.delete(CommonConstants.CAPTCHA_CODE_KEY+uuid);
            return true;
        }
        return true;
    }
    private String getUUID (HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        if(StringTool.isEmpty(uuid)) {
            uuid = request.getHeader("uuid");
        }
        return uuid;
    }
    private String getCaptcha(HttpServletRequest request) {
        String captcha = request.getParameter(properties.getSessionKey());
        if(StringTool.isEmpty(captcha)) {
            captcha = request.getHeader(properties.getSessionKey());
        }
        return captcha;
    }
}
