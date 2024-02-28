package com.mldong.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局属性
 * @author mldong
 * @date 2024/2/8
 */
@Configuration
@ConfigurationProperties(prefix = "g")
@Data
public class GlobalProperties {
    /**
     * 演示环境黑名单，该名单下的url不允许访问
     */
    private List<String> blacklist =  new ArrayList<>();
    /**
     * 忽略权限的url
     */
    private List<String> ignoreUrlList = new ArrayList<>();
    /**
     * 获取放开xss过滤的接口
     */
    private List<String> ignoreXssFilterUrlList = new ArrayList<>();
}
