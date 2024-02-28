package com.mldong.captcha;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片验证码入参
 * @author mldong
 * @date 2024/2/28
 */
@Data
public class CaptchaParam {
    @ApiModelProperty(value = "图片宽度")
    private Integer width;
    @ApiModelProperty(value = "图片高度")
    private Integer height;
    @ApiModelProperty(value = "字符长度")
    private Integer len;
    @ApiModelProperty(value = "字符类型")
    private Integer charType;
}
