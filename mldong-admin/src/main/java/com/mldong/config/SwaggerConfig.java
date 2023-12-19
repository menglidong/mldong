package com.mldong.config;

import cn.hutool.core.collection.CollectionUtil;
import com.mldong.base.SwaggerDocket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ClassUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

/**
 *
 * swagger配置
 * @author mldong
 * @date 2022-01-06
 */
@Configuration
@EnableSwagger2WebMvc
@RequiredArgsConstructor
@Profile(value = {"dev","local","test","demo"})
public class SwaggerConfig implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final List<SwaggerDocket> swaggerDocketList;
    private List<Parameter> getParameters() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> parameters = CollectionUtil.newArrayList();
        parameters.add(parameter);
        return parameters;
    }

    private ApiInfo defaultApiInfo() {
        return new ApiInfoBuilder()
                .title("Flow Doc")
                .description("Flow Doc文档")
                .termsOfServiceUrl("https://gitee.com/mldong/mldong")
                .contact(new Contact("mldong", "https://gitee.com/mldong/mldong", ""))
                .version("1.0")
                .build();
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
                    List<Parameter> parameters = getParameters();
                    docket.apiInfo(defaultApiInfo())
                .groupName(swaggerDocket.getGroupName())
                .select()
                .apis(basePackage(swaggerDocket.getBasePackage()))
                .build()
                .globalOperationParameters(parameters)
                            .securitySchemes(Collections.singletonList(apiKey()))
                            .securityContexts(Collections.singletonList(securityContext()));
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(",")) {
                boolean isMatch = ClassUtils.getPackageName(input).startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }


    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)).orElse(true);
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return ofNullable(input.declaringClass());
    }

}
