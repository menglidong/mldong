package com.mldong.util;

import cn.hutool.core.util.StrUtil;
import com.mldong.context.constant.ConstantContextHolder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * url包裹工具
 * @author mldong
 * @date 2023/7/2
 */
public class UrlWrapUtil {
    private UrlWrapUtil() {};

    /**
     * 文件地址包裹处理
     * @param url
     * @return
     */
    public static String wrap(String url) {
        if(StrUtil.isEmpty(url)) return null;
        String imgBaseUrl = ConstantContextHolder.getImgBaseUrl();
        // url1,url2,url3
        // url1;bizType1,url2;bizType2,url3;bizType3
        String [] urls = url.split(",");
        return Arrays.stream(urls).map(item->{
            String bizType = null;
            if(StrUtil.isNotEmpty(item)) {
                String [] splitItem = item.split(";");
                item = splitItem[0];
                if(splitItem.length == 2) {
                    bizType = splitItem[1];
                }
            }
            String wrapUrl = _wrap(imgBaseUrl, item);
            if(StrUtil.isNotEmpty(wrapUrl) && StrUtil.isNotEmpty(bizType)) {
                return wrapUrl +";"+ bizType;
            }
            return wrapUrl;
        }).filter(item->{
            return StrUtil.isNotEmpty(item);
        }).collect(Collectors.joining(","));
    }

    /**
     * 去掉baseUrl
     * @param url
     * @return
     */
    public static String unWrap(String url) {
        if(url == null) return null;
        if(StrUtil.isEmpty(url)) return "";
        String imgBaseUrl = ConstantContextHolder.getImgBaseUrl();
        String [] urls = url.split(",");
        return Arrays.stream(urls).map(item->{
            String bizType = null;
            if(StrUtil.isNotEmpty(item)) {
                String [] splitItem = item.split(";");
                item = splitItem[0];
                if(splitItem.length == 2) {
                    bizType = splitItem[1];
                }
            }
            String unWrapUrl =  _unWrap(imgBaseUrl, item);
            if(StrUtil.isNotEmpty(unWrapUrl) && StrUtil.isNotEmpty(bizType)) {
                return unWrapUrl +";"+ bizType;
            }
            return unWrapUrl;
        }).filter(item->{
            return StrUtil.isNotEmpty(item);
        }).collect(Collectors.joining(","));
    }
    private static String _wrap(String baseUrl,String url){
        if(StrUtil.isEmpty(url)) return null;
        if(url.startsWith("http")) return url;
        return baseUrl + url;
    }
    private static String _unWrap(String baseUrl,String url){
        if(StrUtil.isEmpty(url)) return null;
        if(url.startsWith("http")) return url;
        return url.replace(baseUrl, "");
    }
}
