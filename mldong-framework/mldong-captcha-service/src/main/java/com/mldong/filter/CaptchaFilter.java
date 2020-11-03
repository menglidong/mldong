package com.mldong.filter;

import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.tool.StringTool;
import com.mldong.config.CaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器中的全局异常不好处理，改成拦截器了
 */
//@Component
//@ConditionalOnBean({CaptchaConfiguration.class, RedisTemplate.class})
public class CaptchaFilter extends OncePerRequestFilter {
    @Autowired
    private CaptchaProperties properties;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
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
