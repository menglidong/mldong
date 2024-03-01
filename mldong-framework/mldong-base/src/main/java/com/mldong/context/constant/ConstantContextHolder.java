package com.mldong.context.constant;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.consts.CommonConstant;
import com.mldong.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * 系统参数配置获取句柄
 * @author mldong
 * @date 2023/9/21
 */
@Slf4j
public class ConstantContextHolder {
    /**
     * 获取缓存前辍
     * @return
     */
    public static String getCacheKeyPrefix() {
        String cacheKeyPrefix = getSysConfig("CACHE_KEY_PREFIX",String.class,false);
        if(StrUtil.isEmpty(cacheKeyPrefix)) {
            // 不存在时，使用应用名+环境类型为前轰
            Environment environment = SpringUtil.getBean(Environment.class);
            String applicationName = environment.getProperty("spring.application.name");
            String profilesActive = environment.getProperty("spring.profiles.active");
            cacheKeyPrefix = StrUtil.format("{}-{}:",applicationName,profilesActive);
        }
        return cacheKeyPrefix;
    }
    /**
     * 获取默认密码
     * @return
     */
    public static String getDefaultPassword() {
        return getSysConfigWithDefault("M_DEFAULT_PASSWORD", String.class, CommonConstant.DEFAULT_PASSWORD);
    }
    /**
     * 获取放开xss过滤的接口
     * @return
     */
    public static List<String> getIgnoreXssFilterUrl() {
        String moleIgnoreXssFilterUrl = getSysConfigWithDefault("MOLE_IGNORE_XSS_FILTER_URL", String.class, null);
        if (ObjectUtil.isEmpty(moleIgnoreXssFilterUrl)) {
            return CollectionUtil.newArrayList();
        } else {
            return CollectionUtil.toList(moleIgnoreXssFilterUrl.split(","));
        }
    }
    /**
     * 获取验证码 开关标识
     * @return
     */
    public static Boolean getCaptchaOpenFlag() {
        return getSysConfigWithDefault("MOLE_CAPTCHA_OPEN", Boolean.class, false);
    }
    /**
     * 获取上传基本目录，一般配置为项目名
     * @return
     */
    public static String getUploadBasePath() {
        return getSysConfigWithDefault("UPLOAD_BASE_PATH",String.class,"");
    }
    /**
     * 获取图片服务器地址
     * @return
     */
    public static String getImgBaseUrl() {
        return getSysConfigWithDefault("IMG_BASE_URL", String.class,"");
    }

    /**
     * 获取平台接口地址
     * @return
     */
    public static String getPlatformApiBaseUrl() {
        return getSysConfig("PLATFORM_API_BASE_URL",String.class,false);
    }
    /**
     * 获取授权白名单账号，该白名单下不限制授权类型
     * @return
     */
    public static List<String> getGrantTypeWhiteListAccount() {
        String s = getSysConfigWithDefault("GRANT_TYPE_WHITE_LIST_ACCOUNT", String.class, "superAdmin,admin,mldong");
        return CollectionUtil.newArrayList(s.split(","));
    }
    /**
     * 获取config表中的配置，如果为空返回默认值
     *
     * @param code   变量名称，对应sys_config表中的code
     * @param clazz        返回变量值的类型
     * @param defaultValue 如果结果为空返回此默认值
     */
    public static <T> T getSysConfigWithDefault(String code, Class<T> clazz, T defaultValue) {
        Dict map = Dict.parse(System.getenv());
        // 一些特殊的参数，优先中环境变量中获取
        String configValue = map.get(code,ConstantContext.me().getStr(code));
        if (ObjectUtil.isEmpty(configValue)) {
            // 将默认值加入到缓存常量
            log.warn(">>> 系统配置sys_config表中存在空项，code为：{}，系统采用默认值：{}", code, defaultValue);
            ConstantContext.me().put(code, defaultValue);
            return defaultValue;
        } else {
            try {
                return Convert.convert(clazz, configValue);
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    /**
     * 获取config表中的配置，如果为空，是否抛出异常
     *
     * @param code   变量名称，对应sys_config表中的code
     * @param clazz        返回变量值的类型
     * @param nullThrowExp 若为空是否抛出异常
     */
    public static <T> T getSysConfig(String code, Class<T> clazz, Boolean nullThrowExp) {
        Dict map = Dict.parse(System.getenv());
        // 一些特殊的参数，优先中环境变量中获取
        String content = map.get(code,ConstantContext.me().getStr(code));
        if (ObjectUtil.isEmpty(content)) {
            if (nullThrowExp) {
                String format = StrUtil.format(">>> 系统配置sys_config表中存在空项，code为：{}", code);
                log.error(format);
                throw new ServiceException(99999999, format);
            } else {
                return null;
            }
        } else {
            try {
                return Convert.convert(clazz, content);
            } catch (Exception e) {
                if (nullThrowExp) {
                    String format = StrUtil.format(">>> 系统配置sys_config表中存在格式错误的值，code={}，content={}", code, content);
                    log.error(format);
                    throw new ServiceException(99999999, format);
                } else {
                    return null;
                }
            }
        }
    }

}
