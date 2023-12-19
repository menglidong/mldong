package com.mldong.modules.biz.config;



import com.mldong.base.SwaggerDocket;
import com.mldong.consts.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2023/12/18
 */
@Configuration
public class BizDocketConfig {
    @Bean
    public SwaggerDocket bizSwaggerDocket() {
        return SwaggerDocket.builder().groupName("10.业务管理").basePackage(CommonConstant.DEFAULT_PACKAGE_NAME+".modules.biz").build();
    }
}
