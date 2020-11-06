package com.mldong.common.tool;

import com.mldong.common.config.GlobalProperties;

public class WebTool {
    private WebTool() {}
    private static  GlobalProperties globalProperties;
    private static GlobalProperties getGlobalProperties () {
        if(globalProperties == null) {
            globalProperties = CxtTool.getBean(GlobalProperties.class);
        }
        return  globalProperties;
    }

    /**
     * 包含url,主要图片字段，加上域名
     * @param url
     * @return
     */
    public static String wrapUrl(String url) {
        if(StringTool.isEmpty(url)) {
            return url;
        }
        if(url.startsWith("http")) {
            return url;
        }
        return getGlobalProperties().getBaseImgUrl() + url;
    }
}
