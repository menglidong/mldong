package com.m.filter;

import com.m.config.CaptchaProperties;
import com.mldong.common.annotation.VerifyCaptcha;
import com.mldong.common.base.CommonError;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.CxtTool;
import com.mldong.common.tool.JsonTool;
import com.mldong.common.tool.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 过滤器中的全局异常不好处理，改成拦截器了
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Autowired
    private CaptchaProperties properties;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${spring.profiles.active}")
    private String profiles;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("dev".equals(profiles) || "test".equals(profiles)) {
            // 开发环境与测试环境不校验验收码
            filterChain.doFilter(request, response);
            return;
        }
        HandlerMapping handlerMapping = CxtTool.getApplicationContext().getBean(HandlerMapping.class);
        HandlerExecutionChain handlerExecutionChain = null;
        try {
            handlerExecutionChain = handlerMapping.getHandler(request);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }
        VerifyCaptcha verifyCaptcha = null;
        if(handlerExecutionChain!=null) {
            Object handler = handlerExecutionChain.getHandler();
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                verifyCaptcha = handlerMethod.getMethodAnnotation(VerifyCaptcha.class);
            }
        }
        if(verifyCaptcha!=null) {
            String captcha = getCaptcha(request);
            if(StringTool.isEmpty(captcha)) {
                writeErrJson(response,GlobalErrEnum.GL99990012);
                return;
            }
            String uuid = getUUID(request);
            if(StringTool.isEmpty(uuid)) {
                writeErrJson(response,GlobalErrEnum.GL99990012);
                return;
            }
            String cacheCaptcha = redisTemplate.opsForValue().get(CommonConstants.CAPTCHA_CODE_KEY+uuid);
            if(!captcha.equals(cacheCaptcha)) {
                writeErrJson(response,GlobalErrEnum.GL99990012);
                return;
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
    private void writeErrJson(HttpServletResponse response, CommonError error){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer=null;
        try {
            writer=response.getWriter();
            writer.write(JsonTool.toJson(CommonResult.error(error)));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer!=null) {
                writer.close();
            }
        }
    }
}
