package com.mldong.captcha;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.annotation.CaptchaValid;
import com.mldong.consts.CommonConstant;
import com.mldong.web.QueryParamHolder;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
        return validate(uuid, code, false);
    }
    /**
     * 校验图片验证码
     * @param uuid
     * @param code
     * @param isRemoveWhenSuccess 校验成功是否删除
     * @return
     */
    public boolean validate(String uuid, String code, boolean isRemoveWhenSuccess) {
        if(StrUtil.isEmpty(uuid) || StrUtil.isEmpty(code)) {
            return false;
        }
        boolean flag = code.equalsIgnoreCase(captchaCache.get(uuid));
        if(flag && isRemoveWhenSuccess) {
            captchaCache.remove(uuid);
        }
        return flag;
    }

    /**
     * 从请求正文中获取校验
     * @param request
     * @return
     */
    public boolean validate(HttpServletRequest request) {
        return validate(request, null);
    }
    /**
     * 从请求正文中获取校验
     * @param request
     * @return
     */
    public boolean validate(HttpServletRequest request, CaptchaValid captchaValid) {
        String captchaUuidKey = CommonConstant.CAPTCHA_UUID_KEY;
        String captchaCodeKey = CommonConstant.CAPTCHA_CODE_KEY;
        if(captchaValid!=null) {
            captchaUuidKey = captchaValid.uuidKey();
            captchaCodeKey = captchaValid.codeKey();
        }
        // 从请求对象或请求头中获取
        String uuid = Convert.toStr(request.getParameter(captchaUuidKey), request.getHeader(captchaUuidKey));
        String code = Convert.toStr(request.getParameter(captchaCodeKey), request.getHeader(captchaCodeKey));
        if(StrUtil.isEmpty(uuid) && ObjectUtil.isNotEmpty(QueryParamHolder.me())) {
            // 从请求正文中获取
            uuid = QueryParamHolder.me().getStr(CommonConstant.CAPTCHA_UUID_KEY);
        }
        if(StrUtil.isEmpty(code) && ObjectUtil.isNotEmpty(QueryParamHolder.me())) {
            // 从请求正文中获取
            code = QueryParamHolder.me().getStr(CommonConstant.CAPTCHA_CODE_KEY);
        }
        return validate(uuid, code, true);
    }
}
