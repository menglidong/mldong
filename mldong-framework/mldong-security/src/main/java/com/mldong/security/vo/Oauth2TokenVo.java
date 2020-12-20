package com.mldong.security.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Oauth2获取Token返回信息封装
 */
public class Oauth2TokenVo {
    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String token;
    /**
     * 刷新令牌
     */
    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    @ApiModelProperty(value = "访问令牌头前缀")
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    @ApiModelProperty(value = "有效时间（秒）")
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
