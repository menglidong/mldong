package com.m.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaConfiguration {
    @Autowired
    private CaptchaProperties captchaProperties;
    @Bean
    public Producer captchaProducer () {
        DefaultKaptcha dk = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", captchaProperties.getBorder());
        // 边框颜色
        properties.setProperty("kaptcha.border.color", captchaProperties.getBorderColor());
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", captchaProperties.getTextproducerFontColor());
        // 图片宽
        properties.setProperty("kaptcha.image.width", captchaProperties.getImageWidth());
        // 图片高
        properties.setProperty("kaptcha.image.height", captchaProperties.getImageHeight());
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", captchaProperties.getTextproducerFontSize());
        // session key
        properties.setProperty("kaptcha.session.key", captchaProperties.getSessionKey());
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", captchaProperties.getTextproducerCharLenght());
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", captchaProperties.getTextproducerFontNames());
        // 验证码字符串
        properties.setProperty("kaptcha.textproducer.char.string", captchaProperties.getTextproducerCharString());
        properties.setProperty("kaptcha.noise.color", captchaProperties.getNoiseColor());
        properties.setProperty("kaptcha.noise.impl", captchaProperties.getNoiseImpl());
        properties.setProperty("kaptcha.background.clear.from", captchaProperties.getBackgroundClearFrom());
        properties.setProperty("kaptcha.background.clear.to", captchaProperties.getBackgroundClearTo());
        properties.setProperty("kaptcha.textproducer.char.space", captchaProperties.getTextproducerCharSpace());
        Config config = new Config(properties);
        dk.setConfig(config);

        return dk;
    }
}
