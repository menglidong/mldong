package com.mldong.captcha;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author mldong
 * @date 2024/2/28
 */
@Component
@RequiredArgsConstructor
public class CaptchaManager {
    private final CaptchaCache captchaCache;

    /**
     * 生成图片二维码并存储到redis中
     * @param width
     * @param height
     * @param len
     * @param charType
     * @return
     */
    public CaptchaModel create(Integer width, Integer height, Integer len, Integer charType) {
        CaptchaModel captchaModel = new CaptchaModel();
        String uuid = StrUtil.uuid();
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(Convert.toInt(width, 130), Convert.toInt(height, 48), Convert.toInt(len, 5));
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Convert.toInt(charType, Captcha.TYPE_NUM_AND_UPPER));
        // 验证码存入redis
        captchaCache.put(uuid, specCaptcha.text(), TimeUnit.MINUTES.toSeconds(10));
        captchaModel.setUuid(uuid);
        captchaModel.setBase64(specCaptcha.toBase64());
        return captchaModel;
    }

    /**
     *
     * @param width
     * @param height
     * @param len
     * @return
     */
    public CaptchaModel create(int width, int height, int len) {
        return create(width, height, len, Captcha.TYPE_NUM_AND_UPPER);
    }
    public CaptchaModel create() {
        return create(130, 48, 5, Captcha.TYPE_NUM_AND_UPPER);
    }

    /**
     *
     * @param param
     * @return
     */
    public CaptchaModel create(CaptchaParam param) {
        return create(param.getWidth(), param.getHeight(), param.getLen(), param.getCharType());
    }

    /**
     * 校验图片验证码
     * @param uuid
     * @param code
     * @return
     */
    public boolean validate(String uuid, String code) {
        return code.equalsIgnoreCase(captchaCache.get(uuid));
    }
}
