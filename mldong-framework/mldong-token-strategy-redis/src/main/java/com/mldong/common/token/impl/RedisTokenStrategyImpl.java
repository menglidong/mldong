package com.mldong.common.token.impl;

import com.mldong.common.token.TokenStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisTokenStrategyImpl implements TokenStrategy {
    @Value("${g.userType:__ADMIN__}")
    private String userType;
    @Value("${g.tokenExpireTimeout:24}")
    private Long tokenExpireTimeout;
    @Resource
    private RedisTemplate<String,RedisUser> redisTemplate;
    @Override
    public String generateToken(Long userId, String userName, Map<String, Object> map) {
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String key = userType + ":" + token;
        RedisUser user = new RedisUser();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setExt(map);
        if(redisTemplate.opsForValue().get(key) == null) {
            //将登陆的信息保存如redis
            redisTemplate.opsForValue().set(key, user);
        }
        //设置token有效的时间 2*60*60s=2h
        redisTemplate.expire(key, tokenExpireTimeout, TimeUnit.HOURS);
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        String key = userType + ":" + token;
        RedisUser redisUser =  redisTemplate.opsForValue().get(key);
        if(redisUser !=null ) {
            redisTemplate.expire(key, tokenExpireTimeout, TimeUnit.HOURS);
            return  true;
        }
        return false;
    }

    @Override
    public Long getUserId(String token) {
        String key = userType + ":" + token;
        RedisUser user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }

    @Override
    public String getUserName(String token) {
        String key = userType + ":" + token;
        RedisUser user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            return null;
        }
        return user.getUserName();
    }

    @Override
    public Map<String, Object> getExt(String token) {
        String key = userType + ":" + token;
        RedisUser user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            return null;
        }
        return user.getExt();
    }

    @Override
    public int removeToken(String token) {
        String key = userType + ":" + token;
        redisTemplate.delete(key);
        return 1;
    }
}
