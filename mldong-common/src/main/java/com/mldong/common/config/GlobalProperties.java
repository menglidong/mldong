package com.mldong.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
	
}
