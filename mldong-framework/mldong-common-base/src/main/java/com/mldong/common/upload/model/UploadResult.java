package com.mldong.common.upload.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 上传结果实体
 * @author mldong
 *
 */
public class UploadResult {
	/**
	 * 上传记录id
	 */
	@ApiModelProperty(value="上传记录id")
	private Long uploadRecordId;
	/**
	 * 业务id
	 */
	@ApiModelProperty(value="业务id")
	private String bizId;
	/**
	 * 业务类型
	 */
	@ApiModelProperty(value="业务类型")
	private String bizType;
	/**
	 * 基础路径
	 */
	@ApiModelProperty(value="基础路径")
	private String baseUrl;
	/**
	 * 文件保存的资源路径
	 */
	@ApiModelProperty(value="文件保存的资源路径")
	private String url;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value="文件大小")
	private Long fileSize;
	/**
	 * 媒体类型
	 */
	@ApiModelProperty(value="媒体类型")
	private String mimeType;
	/**
	 * 文件名
	 */
	@ApiModelProperty(value="文件名")
	private String fileName;
	/**
	 * 文件后辍
	 */
	@ApiModelProperty(value="文件后辍")
	private String fileExt;

	public Long getUploadRecordId() {
		return uploadRecordId;
	}

	public void setUploadRecordId(Long uploadRecordId) {
		this.uploadRecordId = uploadRecordId;
	}

	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	
}
