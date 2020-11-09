package com.mldong.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "g")
public class GlobalProperties {
	/**
	 * 超级管理员id
	 */
	private Long superAdminId = 1L;

	/**
	 * 超级管理员角色id
	 */
	private Long superAdminRoleId = 1L;
	/**
	 * 用户名或密码登录错误允许的最大值
	 */
	private int maxErrLoginTimes = 5;
	/**
	 * 默认密码
	 */
	private String defaultPassword = "mldong@321";
	/**
	 * 七牛云ak
	 */
	private String qiniuAccessKey = "";
	/**
	 * 七牛云sk
	 */
	private String qiniuSecretKey = "";
	/**
	 * 七牛云空间名称
	 */
	private String qiniuBucket = "";
	/**
	 * 图片基础路径
	 */
	private String baseImgUrl="http://qiniu.mldong.com/";
	/**
	 * 是否开启敏感词过滤
	 */
	private boolean openSensitive = true;
	/**
	 * 系在启key
	 */
	private List<String> sensitiveKeys = new ArrayList<>();
	@PostConstruct
	private void init() {
		if(sensitiveKeys.isEmpty()) {
			sensitiveKeys.add("password");
			sensitiveKeys.add("realName");
			sensitiveKeys.add("mobilePhone");
			sensitiveKeys.add("cardNo");
			sensitiveKeys.add("confirmPassword");
			sensitiveKeys.add("oldPassword");
		}
	}
	public Long getSuperAdminId() {
		return superAdminId;
	}

	public void setSuperAdminId(Long superAdminId) {
		this.superAdminId = superAdminId;
	}

	public Long getSuperAdminRoleId() {
		return superAdminRoleId;
	}

	public void setSuperAdminRoleId(Long superAdminRoleId) {
		this.superAdminRoleId = superAdminRoleId;
	}

	public int getMaxErrLoginTimes() {
		return maxErrLoginTimes;
	}

	public void setMaxErrLoginTimes(int maxErrLoginTimes) {
		this.maxErrLoginTimes = maxErrLoginTimes;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getQiniuAccessKey() {
		return qiniuAccessKey;
	}

	public void setQiniuAccessKey(String qiniuAccessKey) {
		this.qiniuAccessKey = qiniuAccessKey;
	}

	public String getQiniuSecretKey() {
		return qiniuSecretKey;
	}

	public void setQiniuSecretKey(String qiniuSecretKey) {
		this.qiniuSecretKey = qiniuSecretKey;
	}

	public String getQiniuBucket() {
		return qiniuBucket;
	}

	public void setQiniuBucket(String qiniuBucket) {
		this.qiniuBucket = qiniuBucket;
	}

	public String getBaseImgUrl() {
		return baseImgUrl;
	}

	public void setBaseImgUrl(String baseImgUrl) {
		this.baseImgUrl = baseImgUrl;
	}

	public boolean isOpenSensitive() {
		return openSensitive;
	}

	public void setOpenSensitive(boolean openSensitive) {
		this.openSensitive = openSensitive;
	}

	public List<String> getSensitiveKeys() {
		return sensitiveKeys;
	}

	public void setSensitiveKeys(List<String> sensitiveKeys) {
		this.sensitiveKeys = sensitiveKeys;
	}
}
