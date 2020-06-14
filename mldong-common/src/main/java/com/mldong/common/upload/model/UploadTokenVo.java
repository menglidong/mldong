package com.mldong.common.upload.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 创建上传凭证返回实体
 * @author mldong
 *
 */
public class UploadTokenVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8862086775224787600L;
	@ApiModelProperty(value="业务id")
	private String bizId;
	@ApiModelProperty(value="上传凭证")
	private String uploadToken;
	@ApiModelProperty(value="凭证过期时长(s)")
	private long expireSeconds;
	@ApiModelProperty(value="上传配置")
	private UploadTokenParam uploadConfig;
	
	public UploadTokenVo(String bizId, String uploadToken, long expireSeconds,
			UploadTokenParam uploadConfig) {
		super();
		this.bizId = bizId;
		this.uploadToken = uploadToken;
		this.expireSeconds = expireSeconds;
		this.uploadConfig = uploadConfig;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public long getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public UploadTokenParam getUploadConfig() {
		return uploadConfig;
	}
	public void setUploadConfig(UploadTokenParam uploadConfig) {
		this.uploadConfig = uploadConfig;
	}
	
}
