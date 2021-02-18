package com.mldong.controller;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.PhoneParam;
import com.mldong.common.sms.SmsHandler;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(tags = "短信验证码")
@RestController
public class SmsController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired(required = false)
    private SmsHandler smsHandler;
    private static final String SMS_KEY = "SMS_CODE:";
    @PostMapping("sendSms")
    @ApiOperation(value = "发送短信验证码")
    @AuthIgnore
    public CommonResult<?> sendSms(@RequestBody @Validated PhoneParam param) {
        String uuid = Md5Tool.md5(param.getMobilePhone());
        String smsCode = StringTool.getRandomNumber(4);
        redisTemplate.opsForValue().set(SMS_KEY+uuid, smsCode, 5, TimeUnit.MINUTES);
        if(smsHandler !=null) {
            smsHandler.sendOut(param.getMobilePhone(),smsCode);
        }
        return CommonResult.success();
    }
}
