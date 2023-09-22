package com.mldong.log;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("log-doFilter");
        LogRequestWrapper logRequestWrapper = new LogRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(logRequestWrapper, servletResponse);
    }
}
