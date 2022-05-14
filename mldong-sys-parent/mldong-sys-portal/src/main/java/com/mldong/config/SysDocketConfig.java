package com.mldong.config;

import cn.hutool.core.util.ArrayUtil;
import com.mldong.swagger.config.SwaggerConfig;
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
        String basePackage = ArrayUtil.join(new String[]{
                "com.mldong.modules.sys",
                "com.mldong.controller",
                "com.m.controller"
        }, SwaggerConfig.SPLIT);
        return new SwaggerDocket("01.系统管理", basePackage);
    }
}
