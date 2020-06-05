package com.mldong.generator.config.model;

/**
 * 数据库配置实体
 * @author mldong
 *
 */
public class DbConfigModel {
	/**
	 * jdbc驱动
	 */
	private String driverClass;
	/**
	 * url
	 */
	private String url;
	/**
	 * 数据库名
	 */
	private String dbName;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
