package com.mldong.modules.sys.config;

import com.mldong.dict.model.DictModel;
import com.mldong.modules.sys.cache.DictCache;
import com.mldong.redis.FastJson2JsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author mldong
 * @date 2023/7/26
 */
@Configuration
public class SysCacheConfig {
    /**
     * 缓存商品单位
     *
     */
    @Bean
    public DictCache dictCache(RedisTemplate<String, DictModel> redisTemplate) {
        return new DictCache(redisTemplate);
    }
    @Bean
    public RedisTemplate<String,  DictModel> dictCacheRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,  DictModel> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(DictModel.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
