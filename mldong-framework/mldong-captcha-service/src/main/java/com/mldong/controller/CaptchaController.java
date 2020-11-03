package com.mldong.controller;

import com.google.code.kaptcha.Producer;
import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.tool.Base64Tool;
import com.mldong.config.CaptchaConfiguration;
import com.mldong.vo.CaptchaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Api(tags = "图片验证码")
@RestController
@ConditionalOnBean({CaptchaConfiguration.class, RedisTemplate.class})
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @PostMapping("captchaImage")
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @AuthIgnore
    @GetMapping("/captchaImage")
    public CommonResult<CaptchaVo> captchaImage() {
        String code = captchaProducer.createText();
        BufferedImage bufferedImage = captchaProducer.createImage(code);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException(GlobalErrEnum.GL99990100);
        }
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(CommonConstants.CAPTCHA_CODE_KEY+uuid, code, 5, TimeUnit.MINUTES);
        return CommonResult.success(new CaptchaVo(uuid, Base64Tool.encode(os.toByteArray())));
    }
}
