package com.mldong.modules.wf.config;



import com.mldong.base.SwaggerDocket;
import com.mldong.consts.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mldong
 * @date 2023/12/18
 */
@Configuration
public class WfDocketConfig {
    @Bean
    public SwaggerDocket wfSwaggerDocket() {
        return SwaggerDocket.builder().groupName("02.工作流").basePackage(CommonConstant.DEFAULT_PACKAGE_NAME+".modules.wf").build();
    }
}
