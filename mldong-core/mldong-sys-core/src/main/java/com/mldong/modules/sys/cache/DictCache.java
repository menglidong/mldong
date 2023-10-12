package com.mldong.modules.sys.cache;

import com.mldong.cache.base.AbstractRedisCacheOperator;
import com.mldong.dict.model.DictModel;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author mldong
 * @date 2023/7/26
 */
public class DictCache extends AbstractRedisCacheOperator<DictModel> {
    public DictCache(RedisTemplate<String, DictModel> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public String getCommonKeyPrefix() {
        return "sys_dict:";
    }
}
