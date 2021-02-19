package com.mldong.controller;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.PhoneParam;
import com.mldong.common.sms.SmsHandler;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import com.mldong.dto.SmsVerifyParam;
import com.mldong.tool.SmsTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(tags = "短信验证码")
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired(required = false)
    private SmsHandler smsHandler;
    @Value("${spring.profiles.active}")
    private String profiles;
    private static final String SMS_KEY = "SMS_CODE:";
    @PostMapping("sendOut")
    @ApiOperation(value = "发送短信验证码")
    @AuthIgnore
    public CommonResult<?> sendOut(@RequestBody @Validated PhoneParam param) {
        String uuid = Md5Tool.md5(param.getMobilePhone());
        String smsCode = "8888";
        if(!("dev".equals(profiles) || "test".equals(profiles))) {
            // 非开发与测试
            smsCode = StringTool.getRandomNumber(4);
        }
        // 是否发送逻辑还是由处理类来定吧，就不放在上面条件中限制了
        if(smsHandler !=null) {
            smsHandler.sendOut(param.getMobilePhone(),smsCode);
        }
        redisTemplate.opsForValue().set(SMS_KEY+uuid, smsCode, 5, TimeUnit.MINUTES);
        return CommonResult.success();
    }
    @PostMapping("verify")
    @ApiOperation(value = "短信验证码校验")
    @AuthIgnore
    public CommonResult<?> verify(@RequestBody @Validated SmsVerifyParam param) {
        boolean bool = SmsTool.verify(param.getMobilePhone(), param.getSmsCode(),false);
        if(bool) {
            return CommonResult.success();
        } else {
            return CommonResult.fail("验证码错误", null);
        }
    }
}
