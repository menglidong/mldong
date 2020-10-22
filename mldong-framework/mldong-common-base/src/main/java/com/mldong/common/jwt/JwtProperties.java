package com.mldong.common.jwt;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	/**
	 * 密钥
	 */
	private String secret="2YjHCFFOjycSPI6wrngimTe9bH4PYBESXfAnf9fvdeV848GGHs1kwOJilVgwF3qG";
	/**
	 * 有效时间(默认2小时)
	 */
	private long expireTime=7200000;
	/**
	 * 刷新有效时间
	 */
	private long refreshExpireTime;
	/**
	 * 签发者
	 */
	private String issuer="mldong";
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public long getRefreshExpireTime() {
		return refreshExpireTime;
	}
	public void setRefreshExpireTime(long refreshExpireTime) {
		this.refreshExpireTime = refreshExpireTime;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
}
