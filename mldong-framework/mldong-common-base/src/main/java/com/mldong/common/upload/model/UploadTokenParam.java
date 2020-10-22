package com.mldong.common.upload.model;
/**
 * 创建上传凭证入参实体
 * @author mldong
 *
 */
public class UploadTokenParam {
	/**
	 * 业务类型
	 */
	private String bizType;
	/**
	 * 限定上传文件大小最小值，单位`byte`。（0为不限制）
	 */
	private long fileSizeMin;
	/**
	 * 限定上传文件大小最大值，单位`byte`。（0为不限制）
	 */
	private long fileSizeMax;
	/**
	 * 限定用户上传后辍(多个逗号分割)
	 */
	private String fileExt;
	/**
	 * 访问地址前辍
	 */
	private String baseUrl;
	/**
	 * 回调地址
	 */
	private String callbackUrl;
	/**
	 * 命名策略
	 */
	private String namingStrategy;
	/**
	 * 是否记录
	 */
	private boolean isRecord;
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public long getFileSizeMin() {
		return fileSizeMin;
	}
	public void setFileSizeMin(long fileSizeMin) {
		this.fileSizeMin = fileSizeMin;
	}
	public long getFileSizeMax() {
		return fileSizeMax;
	}
	public void setFileSizeMax(long fileSizeMax) {
		this.fileSizeMax = fileSizeMax;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getNamingStrategy() {
		return namingStrategy;
	}
	public void setNamingStrategy(String namingStrategy) {
		this.namingStrategy = namingStrategy;
	}
	public boolean isRecord() {
		return isRecord;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
	
}
