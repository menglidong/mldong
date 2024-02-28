package com.mldong.captcha;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片验证码返回实体
 * @author mldong
 * @date 2024/2/28
 */
@Data
public class CaptchaModel implements Serializable {
    private String uuid;
    private String base64;
}
