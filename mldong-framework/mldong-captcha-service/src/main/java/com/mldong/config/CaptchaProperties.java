package com.mldong.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "captcha")
//@ConditionalOnProperty(prefix = "captcha", name = "open", havingValue = "true", matchIfMissing = false)
public class CaptchaProperties {
    /**
     * 图片边框
     */
    private String border = "yes";
    /**
     * 边框颜色
     */
    private String borderColor = "105,179,90";
    /**
     * 字体颜色
     */
    private String textproducerFontColor = "blue";
    /**
     * 图片宽
     */
    private String imageWidth = "100";
    /**
     * 图片高
     */
    private String imageHeight = "50";
    /**
     * 字体大小
     */
    private String textproducerFontSize = "27";
    /**
     * session key
     */
    private String sessionKey = "captchaCode";
    /**
     * 验证码长度
     */
    private String textproducerCharLenght = "4";
    /**
     * 字体
     */
    private String textproducerFontNames = "宋体,楷体,微软雅黑";
    /**
     * 验证码字符串
     */
    private String textproducerCharString = "0123456789";
    private String kaptchaObscurificatorImpl = "com.google.code.kaptcha.impl.WaterRipple";
    private String noiseColor = "black";
    private String noiseImpl = "com.google.code.kaptcha.impl.DefaultNoise";
    private String backgroundClearFrom = "185,56,213";
    private String backgroundClearTo = "white";
    private String textproducerCharSpace = "3";

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getTextproducerFontColor() {
        return textproducerFontColor;
    }

    public void setTextproducerFontColor(String textproducerFontColor) {
        this.textproducerFontColor = textproducerFontColor;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getTextproducerFontSize() {
        return textproducerFontSize;
    }

    public void setTextproducerFontSize(String textproducerFontSize) {
        this.textproducerFontSize = textproducerFontSize;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getTextproducerCharLenght() {
        return textproducerCharLenght;
    }

    public void setTextproducerCharLenght(String textproducerCharLenght) {
        this.textproducerCharLenght = textproducerCharLenght;
    }

    public String getTextproducerFontNames() {
        return textproducerFontNames;
    }

    public void setTextproducerFontNames(String textproducerFontNames) {
        this.textproducerFontNames = textproducerFontNames;
    }

    public String getTextproducerCharString() {
        return textproducerCharString;
    }

    public void setTextproducerCharString(String textproducerCharString) {
        this.textproducerCharString = textproducerCharString;
    }

    public String getKaptchaObscurificatorImpl() {
        return kaptchaObscurificatorImpl;
    }

    public void setKaptchaObscurificatorImpl(String kaptchaObscurificatorImpl) {
        this.kaptchaObscurificatorImpl = kaptchaObscurificatorImpl;
    }

    public String getNoiseColor() {
        return noiseColor;
    }

    public void setNoiseColor(String noiseColor) {
        this.noiseColor = noiseColor;
    }

    public String getNoiseImpl() {
        return noiseImpl;
    }

    public void setNoiseImpl(String noiseImpl) {
        this.noiseImpl = noiseImpl;
    }

    public String getBackgroundClearFrom() {
        return backgroundClearFrom;
    }

    public void setBackgroundClearFrom(String backgroundClearFrom) {
        this.backgroundClearFrom = backgroundClearFrom;
    }

    public String getBackgroundClearTo() {
        return backgroundClearTo;
    }

    public void setBackgroundClearTo(String backgroundClearTo) {
        this.backgroundClearTo = backgroundClearTo;
    }

    public String getTextproducerCharSpace() {
        return textproducerCharSpace;
    }

    public void setTextproducerCharSpace(String textproducerCharSpace) {
        this.textproducerCharSpace = textproducerCharSpace;
    }
}
