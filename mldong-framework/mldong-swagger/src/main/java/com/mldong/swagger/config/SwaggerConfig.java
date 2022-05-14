package com.mldong.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@Profile({"dev","test","demo"}) // 只有开发环境和测试环境才会生效
public class SwaggerConfig implements WebMvcConfigurer, ApplicationContextAware {
    // 定义分割符
    public static final String SPLIT = ";";
    private ApplicationContext applicationContext;
    @Value("${security.enable:false}")
    private Boolean securityEnabled;
    @Autowired(required = false)
    private List<SwaggerDocket> swaggerDocketList;
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 这个是默认的swaggerui
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 这个是knife4j的ui
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    private ApiInfo apiinfo(SwaggerDocket swaggerDocket) {
    	return new ApiInfoBuilder()
        .title(swaggerDocket.getGroupName()+"接口文档")
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
    /**
     * 声明基础包
     *
     * @param basePackage 基础包路径
     * @return
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    /**
     * 校验基础包
     *
     * @param basePackage 基础包路径
     * @return
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(SPLIT)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }
    /**
     * 检验基础包实例
     *
     * @param requestHandler 请求处理类
     * @return
     */
    @SuppressWarnings("deprecation")
    private static Optional<? extends Class<?>> declaringClass(RequestHandler requestHandler) {
        return Optional.fromNullable(requestHandler.declaringClass());
    }
    /**
     * 动态分组
     */
    @Bean
    public List<SwaggerDocket> docketList() {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getBeanFactory();
        for(int i=0,len=swaggerDocketList.size();i<len;i++) {
            SwaggerDocket swaggerDocket = swaggerDocketList.get(i);
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Docket.class);
            beanDefinitionBuilder
                    .addConstructorArgValue(DocumentationType.SWAGGER_2);
            BeanDefinition beanDefinition=beanDefinitionBuilder.getBeanDefinition();
            beanFactory.registerBeanDefinition("docketApi"+i, beanDefinition);
            Docket docket = this.applicationContext.getBean("docketApi"+i, Docket.class);
            docket.groupName(swaggerDocket.getGroupName())
                    .apiInfo(apiinfo(swaggerDocket))
                    .select()
                    .apis(basePackage(swaggerDocket.getBasePackage()))
                    //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
                    .build();
            if(Boolean.FALSE.equals(securityEnabled)) {
                docket.securitySchemes(security());
            } else {
                //整合oauth2
                docket.securitySchemes(Collections.singletonList(apiKey()))
                        .securityContexts(Collections.singletonList(securityContext()));
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}