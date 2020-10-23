package com.mldong.common.token.impl;

import java.io.Serializable;
import java.util.Map;

public class RedisUser implements Serializable {
    private Long userId;
    private String userName;
    private Map<String,Object> ext;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }
}
