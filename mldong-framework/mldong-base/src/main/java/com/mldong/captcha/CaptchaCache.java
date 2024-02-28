package com.mldong.captcha;

import com.mldong.cache.base.AbstractRedisCacheOperator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author mldong
 * @date 2024/2/28
 */
@Component
public class CaptchaCache extends AbstractRedisCacheOperator<String> {
    public CaptchaCache(RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public String getCommonKeyPrefix() {
        return "captcha:";
    }
}
