package com.mldong.config;

import com.mldong.swagger.config.SwaggerDocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2022/04/23
 */
@Configuration
public class CmsDocketConfig {
    @Bean
    public SwaggerDocket cmsSwaggerDocket() {
        return new SwaggerDocket("02.内容管理", "com.mldong.modules.cms");
    }
}
