package com.mldong.tool;

import com.mldong.common.tool.CxtTool;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import org.springframework.data.redis.core.RedisTemplate;

public class SmsTool {
    private static final String SMS_KEY = "SMS_CODE:";

    /**
     * 校验验证码
     * @param mobilePhone
     * @param smsCode
     * @return
     */
    public static boolean verify(String mobilePhone,String smsCode) {
        String uuid = Md5Tool.md5(mobilePhone);
        RedisTemplate redisTemplate = CxtTool.getBean(RedisTemplate.class);
        String smsCodeValue = (String) redisTemplate.opsForValue().get(SMS_KEY+uuid);
        if(StringTool.isEmpty(smsCodeValue)) {
            return false;
        }
        if(smsCodeValue.equals(smsCode)) {
            redisTemplate.delete(SMS_KEY+uuid);
            return true;
        }
        return false;
    }
}
