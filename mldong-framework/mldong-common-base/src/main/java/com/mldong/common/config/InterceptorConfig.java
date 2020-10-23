package com.mldong.common.config;

import java.util.List;

import com.mldong.common.support.LoginUserMethodArgumentResolver;
import com.mldong.common.token.TokenStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 自定义拦截器配置
 * @author mldong
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired(required=false)
	private List<HandlerInterceptor> interceptorList;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(interceptorList!=null) {
			interceptorList.forEach(interceptor->{
				registry.addInterceptor(interceptor)
				.excludePathPatterns("/swagger-resources/**","/v2/api-docs-ext/**","/v2/api-docs/**","/druid/**");
			});
		}
	}
	@Autowired
	private TokenStrategy tokenStrategy;
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginUserMethodArgumentResolver(tokenStrategy));
	}
}
