package com.mldong.config;

import com.mldong.swagger.config.SwaggerDocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2022/04/23
 */
@Configuration
public class WfDocketConfig {
    @Bean
    public SwaggerDocket wfSwaggerDocket() {
        return new SwaggerDocket("03.工作流", "com.mldong.modules.wf");
    }
}
