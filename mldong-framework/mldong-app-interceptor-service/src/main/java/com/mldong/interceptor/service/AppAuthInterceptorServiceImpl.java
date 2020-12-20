package com.mldong.interceptor.service;

import com.mldong.common.config.GlobalProperties;
import com.mldong.common.interceptor.AuthInterceptorService;
import com.mldong.common.token.TokenStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class AppAuthInterceptorServiceImpl implements AuthInterceptorService {
    @Autowired
    private GlobalProperties globalProperties;
    @Autowired
    private TokenStrategy tokenStrategy;
    @Override
    public boolean verifyToken(String token) {
        return tokenStrategy.verifyToken(token);
    }
    @Override
    public boolean hasAuth(Long userId, String access) {
//        Long userId = tokenStrategy.getUserId(token);
//        if(userId.equals(globalProperties.getSuperAdminId())) {
//            return true;
//        }
        return true;
    }
    @Override
    public Long getUserId(String token) {
        return tokenStrategy.getUserId(token);
    }

    @Override
    public String getUserName(String token) {
        return tokenStrategy.getUserName(token);
    }

    @Override
    public Map<String, Object> getExt(String token) {
        return tokenStrategy.getExt(token);
    }

}
