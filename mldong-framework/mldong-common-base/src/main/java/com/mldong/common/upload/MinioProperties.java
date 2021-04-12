package com.mldong.common.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("minio")
public class MinioProperties {
    private String url;
    private String accessKey;
    private String secretKey;
    private String defaultFolder;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDefaultFolder() {
        return defaultFolder;
    }

    public void setDefaultFolder(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }
}
