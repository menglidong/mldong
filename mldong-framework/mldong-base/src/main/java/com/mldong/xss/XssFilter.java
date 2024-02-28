package com.mldong.xss;



import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.properties.GlobalProperties;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 * xss过滤器
 * @author mldong
 * @date 2022-01-06
 */
public class XssFilter implements Filter {
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private GlobalProperties globalProperties;
    public XssFilter(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUri = httpServletRequest.getRequestURI();
        // 获取不进行url过滤的接口
        List<String> ignoreXssFilterUrl = ConstantContextHolder.getIgnoreXssFilterUrl();
        ignoreXssFilterUrl.addAll(globalProperties.getIgnoreXssFilterUrlList());
        if (ignoreXssFilterUrl != null && ignoreXssFilterUrl.stream().anyMatch(s -> antPathMatcher.match(s,requestUri))) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        }
    }

}
