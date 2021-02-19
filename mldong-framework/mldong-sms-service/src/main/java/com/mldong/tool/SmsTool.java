package com.mldong.tool;

import com.mldong.common.tool.CxtTool;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SmsTool {
    private static final String SMS_KEY = "SMS_CODE:";
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 校验验证码
     * @param mobilePhone
     * @param smsCode
     * @return
     */
    public static boolean verify(String mobilePhone,String smsCode) {
        return verify(mobilePhone, smsCode, true);
    }
    /**
     * 校验验证码
     * @param mobilePhone
     * @param smsCode
     * @return
     */
    public static boolean verify(String mobilePhone,String smsCode, boolean isDeleted) {
        String uuid = Md5Tool.md5(mobilePhone);
        RedisTemplate<String,String> redisTemplate = CxtTool.getBean(SmsTool.class).getRedisTemplate();
        String smsCodeValue =  redisTemplate.opsForValue().get(SMS_KEY+uuid);
        if(StringTool.isEmpty(smsCodeValue)) {
            return false;
        }
        if(smsCodeValue.equals(smsCode)) {
            if(isDeleted) {
                redisTemplate.delete(SMS_KEY+uuid);
            }
            return true;
        }
        return false;
    }
    public RedisTemplate<String,String> getRedisTemplate() {
        return redisTemplate;
    }
}
