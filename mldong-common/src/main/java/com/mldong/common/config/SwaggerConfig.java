package com.mldong.common.config;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.collect.Lists;

@Configuration
@EnableSwagger2
@Profile({"dev","test"}) // 只有开发环境和测试环境才会生效
public class SwaggerConfig implements WebMvcConfigurer {

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
    	return new Docket(DocumentationType.SWAGGER_2)
    		.groupName("后台业务接口")
			.apiInfo(adminInfo())
            .select()
            //.apis(RequestHandlerSelectors.basePackage("com.mldong.modules"))
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(security());
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

}