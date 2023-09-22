package com.mldong.cache.base;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.cache.CacheOperator;
import com.mldong.context.constant.ConstantContextHolder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 * 基于redis的缓存封装
 * @author mldong
 * @date 2023-09-21
 */
public abstract class AbstractRedisCacheOperator<T> implements CacheOperator<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private String cacheKeyPrefix = "";
    public AbstractRedisCacheOperator(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        cacheKeyPrefix = ConstantContextHolder.getCacheKeyPrefix();
    }

    @Override
    public void put(String key, T value) {
        redisTemplate.boundValueOps(cacheKeyPrefix+getCommonKeyPrefix() + key).set(value);
    }

    @Override
    public void put(String key, T value, Long timeoutSeconds) {
        redisTemplate.boundValueOps(cacheKeyPrefix+getCommonKeyPrefix() + key).set(value, timeoutSeconds, TimeUnit.SECONDS);
    }

    @Override
    public T get(String key) {
        return redisTemplate.boundValueOps(cacheKeyPrefix+getCommonKeyPrefix() + key).get();
    }

    @Override
    public void remove(String... key) {
        ArrayList<String> keys = CollectionUtil.toList(key);
        List<String> withPrefixKeys = keys.stream().map(i -> cacheKeyPrefix+getCommonKeyPrefix() + i).collect(Collectors.toList());
        redisTemplate.delete(withPrefixKeys);
    }

    @Override
    public Collection<String> getAllKeys() {
        Set<String> keys = redisTemplate.keys(cacheKeyPrefix+getCommonKeyPrefix() + "*");
        if (keys != null) {
            // 去掉缓存key的common prefix前缀
            return keys.stream().map(key -> StrUtil.removePrefix(key, cacheKeyPrefix+getCommonKeyPrefix())).collect(Collectors.toSet());
        } else {
            return CollectionUtil.newHashSet();
        }
    }

    @Override
    public Collection<T> getAllValues() {
        Set<String> keys = redisTemplate.keys(cacheKeyPrefix+getCommonKeyPrefix() +"*");
        if (keys != null) {
            return redisTemplate.opsForValue().multiGet(keys);
        } else {
            return CollectionUtil.newArrayList();
        }
    }

    @Override
    public Map<String, T> getAllKeyValues() {
        Collection<String> allKeys = this.getAllKeys();
        HashMap<String, T> results = new HashMap<>();
        for (String key : allKeys) {
            results.put(key, this.get(key));
        }
        return results;
    }
}
