package com.mldong.config;

import com.mldong.swagger.config.SwaggerDocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2022/04/23
 */
@Configuration
public class SysDocketConfig {
    @Bean
    public SwaggerDocket systemSwaggerDocket() {
        return new SwaggerDocket("01.系统管理", "com.mldong.modules.sys");
    }
}
