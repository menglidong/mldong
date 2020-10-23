package com.mldong.interceptor.service;

import com.mldong.common.config.GlobalProperties;
import com.mldong.common.interceptor.AuthInterceptorService;
import com.mldong.common.token.TokenStrategy;
import com.mldong.modules.sys.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AdminAuthInterceptorServiceImpl implements AuthInterceptorService {
    @Autowired
    private GlobalProperties globalProperties;
    @Autowired
    private TokenStrategy tokenStrategy;
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public boolean verifyToken(String token) {
        return tokenStrategy.verifyToken(token);
    }
    @Override
    public boolean hasAuth(String token, String access) {
        Long userId = tokenStrategy.getUserId(token);
        if(userId.equals(globalProperties.getSuperAdminId())) {
            return true;
        }
        return loadUserAccessList(userId).contains(access);
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
    public Map<String,Object> getExt(String token) {
        return tokenStrategy.getExt(token);
    }
    @Cacheable(value = "access_user_id",key="#userId")
    public List<String> loadUserAccessList(Long userId) {
        if(userId.equals(globalProperties.getSuperAdminId())) {
            return Arrays.asList("admin");
        }
        return sysUserDao.selectUserAccess(userId).stream()
                .map(item->{
                    return item.getAccess();
                }).collect(Collectors.toList());
    }
}
