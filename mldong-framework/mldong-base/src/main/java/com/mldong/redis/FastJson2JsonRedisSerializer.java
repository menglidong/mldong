package com.mldong.redis;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 *
 * redis序列化器
 * @author mldong
 * @date 2023-09-21
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> clazz;

    /**
     * 构造函数
     *
     */
    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化
     *
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (ObjectUtil.isEmpty(t)) {
            return new byte[0];
        }
        return JSONUtil.toJsonStr(t).getBytes(Charset.defaultCharset());
    }

    /**
     * 反序列化
     *
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (ObjectUtil.isEmpty(bytes)) {
            return null;
        }
        String str = new String(bytes, Charset.defaultCharset());
        return (T) JSONUtil.toBean(str, this.clazz);
    }

}
