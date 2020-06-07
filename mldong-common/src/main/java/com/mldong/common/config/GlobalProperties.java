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
	
}
