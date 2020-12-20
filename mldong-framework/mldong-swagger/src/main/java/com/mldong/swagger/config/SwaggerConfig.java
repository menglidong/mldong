package com.mldong.swagger.config;

import io.swagger.annotations.ApiOperation;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@Profile({"dev","test","demo"}) // 只有开发环境和测试环境才会生效
public class SwaggerConfig implements WebMvcConfigurer {
    @Value("${security.enable:false}")
    private Boolean securityEnabled;
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 这个是默认的swaggerui
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 这个是knife4j的ui
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	
    @Bean
    public Docket adminApi() {
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
    		.groupName("后台业务接口")
			.apiInfo(adminInfo())
            .select()
            //.apis(RequestHandlerSelectors.basePackage("com.mldong.modules"))
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build();
            if(Boolean.FALSE.equals(securityEnabled)) {
                docket.securitySchemes(security());
            } else {
                //整合oauth2
                docket.securitySchemes(Collections.singletonList(apiKey()))
                        .securityContexts(Collections.singletonList(securityContext()));
            }
        return docket;
    }
    
    private ApiInfo adminInfo() {
    	return new ApiInfoBuilder()
        .title("接口文档-后台管理接口")
        .description("获取资源用 GET ,其余采用POST")
        .version("1.0")
        .build();
    }

    
    private List<ApiKey> security() {
    	// 这里配置请求策略-header["Auth-Token"]
    	return Lists.newArrayList(
    			new ApiKey("Auth-Token", "Auth-Token", "header")
    	);
   }
    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
    /**
     * swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("web", "token");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer",authorizationScopes));
    }

}