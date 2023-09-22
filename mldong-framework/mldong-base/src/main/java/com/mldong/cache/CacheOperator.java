package com.mldong.cache;

import java.util.Collection;
import java.util.Map;

/**
 * 缓存操作的基础接口，可以实现不同种缓存实现
 * <p>
 * 泛型为cache的值类class类型
 * @author mldong
 * @date 2023-09-21
 */
public interface CacheOperator<T> {

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    void put(String key, T value);

    /**
     * 添加缓存（带过期时间，单位是秒）
     * @param key
     * @param value
     * @param timeoutSeconds
     */
    void put(String key, T value, Long timeoutSeconds);

    /**
     * 通过缓存key获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void remove(String... key);

    /**
     * 获得缓存的所有key列表（不带common prefix的）
     * @return
     */
    Collection<String> getAllKeys();

    /**
     * 获得缓存的所有值列表
     * @return
     */
    Collection<T> getAllValues();

    /**
     * 获取所有的key，value
     *
     * @return 键值map
     */
    Map<String, T> getAllKeyValues();

    /**
     * 通用缓存的前缀，用于区分不同业务
     * <p>
     * 如果带了前缀，所有的缓存在添加的时候，key都会带上这个前缀
     *
     * @return 缓存前缀
     */
    String getCommonKeyPrefix();

}
