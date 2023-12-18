package com.mldong.config;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mldong.auth.AuthInterceptor;
import com.mldong.web.MldongFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mldong
 * @date 2023/9/20
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    // 注册sa-token拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new AuthInterceptor(handle -> {
            // 登录认证：除白名单路径外均需要登录认证
            SaRouter.notMatch(CollectionUtil.newArrayList(
                    "/",
                    "/sys/login",
                    //前端的
                    "/favicon.ico",
                    //swagger相关的
                    "/doc.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v2/api-docs",
                    "/v2/api-docs-ext")).match("/**").check(StpUtil::checkLogin);
        }));
    }

    /**
     * json自定义序列化工具,long转string
     *
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder
                        .serializerByType(Long.class, ToStringSerializer.instance)
                        .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }

    /**
     * 自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean mldongFilterRegistrationBean () {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MldongFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
