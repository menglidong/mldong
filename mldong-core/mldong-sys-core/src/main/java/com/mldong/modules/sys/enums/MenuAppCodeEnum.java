package com.mldong.modules.sys.enums;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;
import com.mldong.exception.ServiceException;

@DictEnum(key="sys_menu_app_code",name="应用编码类型")
public enum MenuAppCodeEnum implements CodedEnum {
    PLATFORM(10,"平台", null),
    ;
    private Integer code;
    private String message;
    private String grantTypes;
    @JsonCreator
    public static MenuAppCodeEnum forValue(Integer value) {
        return CodedEnum.codeOf(MenuAppCodeEnum.class, value).get();
    }
    MenuAppCodeEnum(Integer code, String message, String grantTypes) {
        this.code = code;
        this.message = message;
        this.grantTypes = grantTypes;
    }
    /**
     * 校验授权方式
     * @param appCode
     * @param grantType
     */
    public static void checkGrantType(String appCode,String grantType) {
        if(StrUtil.isEmpty(grantType)) {
            grantType = "password";
        }
        MenuAppCodeEnum menuAppCodeEnum = CodedEnum.codeOf(MenuAppCodeEnum.class, appCode).orElse(MenuAppCodeEnum.PLATFORM);
        if(StrUtil.isNotEmpty(menuAppCodeEnum.grantTypes)) {
            if(!ArrayUtil.contains(menuAppCodeEnum.grantTypes.split(","),grantType)) {
                ServiceException.throwBiz(99999999,StrUtil.format("不支持{}登录方式",grantType));
            }
        }
    }
    @JsonValue
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

