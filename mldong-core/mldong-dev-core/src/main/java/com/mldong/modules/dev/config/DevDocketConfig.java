package com.mldong.modules.dev.config;



import com.mldong.base.SwaggerDocket;
import com.mldong.consts.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2024/01/24
 */
@Configuration
public class DevDocketConfig {
    @Bean
    public SwaggerDocket devSwaggerDocket() {
        return SwaggerDocket.builder().groupName("03.在线开发").basePackage(CommonConstant.DEFAULT_PACKAGE_NAME+".modules.dev").build();
    }
}
