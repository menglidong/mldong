package com.mldong.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.cache.CacheOperator;
import com.mldong.context.constant.ConstantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Redis锁工具
 * @author mldong
 */
@Slf4j
public class RedisUtil {

    /**
     * 加锁
     * @param key
     * @param value
     * @param timeout 超时自动释放
     * @param timeUnit 单位
     */
    public static boolean lock(String key, String value, int timeout, TimeUnit timeUnit){
        Boolean b = false;
        RedisTemplate redisTemplate = SpringUtil.getBean(
                new TypeReference<RedisTemplate<String,String>>(){}
        );
        String cacheKeyPrefix = ConstantContextHolder.getCacheKeyPrefix();
        // setIfAbsent说明：
        // 如果为空就set值，并返回1
        // 如果存在(不为空)不进行操作，并返回0
        if(timeout <=0) {
            b = redisTemplate.opsForValue().setIfAbsent(cacheKeyPrefix+key, value);
        } else {
            b = redisTemplate.opsForValue().setIfAbsent(cacheKeyPrefix+key, value, timeout, timeUnit);
        }
        if(b){
            log.debug("lock success!");
        }else{
            log.debug("lock err!");
        }
        return b;
    }
    /**
     * 释放锁
     * @param key
     */
    public static void releaseLock(String key){
        String cacheKeyPrefix = ConstantContextHolder.getCacheKeyPrefix();
        RedisTemplate redisTemplate = SpringUtil.getBean(
                new TypeReference<RedisTemplate<String,String>>(){}
        );
        redisTemplate.delete(cacheKeyPrefix+key);
        log.debug("releaseLock success!");
    }

    /**
     * 延迟双删
     * @param cacheOperator 缓存操作类
     * @param function 删除前执行方法
     * @param object 入参
     * @param idKey
     * @param millis 延时时间-毫秒
     */
    public static <T,R,M> R delayedDoubleRemove(CacheOperator<M> cacheOperator, Function<T,R> function, T object, String idKey, long millis) {
        List<String> keys = new ArrayList<>();
        // 列表处理
        if(object instanceof Iterator){
            Iterator iterator = (Iterator) object;
            while (iterator.hasNext()) {
                Object next = iterator.next();
                keys.add(StrUtil.toString(ReflectUtil.getFieldValue(next,idKey)));
            }
        } else {
            keys.add(StrUtil.toString(ReflectUtil.getFieldValue(object,idKey)));
        }
        String [] keyArr = keys.toArray(new String[]{});
        // 先删除
        cacheOperator.remove(keyArr);
        // 再更新
        R res = function.apply(object);
        // 异步再延时删除
        ThreadUtil.execAsync(()->{
            ThreadUtil.safeSleep(millis);
            cacheOperator.remove(keyArr);
        });
        return res;
    }
    /**
     * 延迟双删
     * @param cacheOperator 缓存操作类
     * @param function 删除前执行方法
     * @param object 入参
     * @param idKey
     */
    public static <T,R,M> R delayedDoubleRemove(CacheOperator<M> cacheOperator, Function<T,R> function, T object, String idKey) {
        return delayedDoubleRemove(cacheOperator,function,object,idKey,DateUnit.SECOND.getMillis()*5);
    }
    /**
     * 延迟双删
     * @param cacheOperator 缓存操作类
     * @param function 删除前执行方法
     * @param object 入参
     */
    public static <T,R,M> R delayedDoubleRemove(CacheOperator<M> cacheOperator, Function<T,R> function, T object) {
        // 异步再延时5s删除
        return delayedDoubleRemove(cacheOperator,function,object,"id",DateUnit.SECOND.getMillis()*5);
    }
}
