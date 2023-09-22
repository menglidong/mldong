package com.mldong.auth;

import com.mldong.cache.base.AbstractRedisCacheOperator;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 角色权限码缓存
 * @author mldong
 * @date 2023/9/21
 */
public class RolePermCodeCache extends AbstractRedisCacheOperator<RolePermCode> {
    public RolePermCodeCache(RedisTemplate<String, RolePermCode> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public String getCommonKeyPrefix() {
        return "ROLE_PERM_CODE:";
    }

    /**
     * 根据解决id和应用编码删除
     * @param roleId
     * @param appCode
     */
    public void remove(Long roleId,String appCode) {
        String key = appCode + "_" + roleId;
        this.remove(key.toUpperCase());
    }
}
