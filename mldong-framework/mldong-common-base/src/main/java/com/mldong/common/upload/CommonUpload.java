package com.mldong.common.upload;

import com.mldong.common.upload.model.UploadResult;
import com.mldong.common.upload.model.UploadTokenParam;
import com.mldong.common.upload.model.UploadTokenVo;
/**
 * 通过上传接口定义
 * @author mldong
 *
 */
public interface CommonUpload {
	/**
	 * 回调请求类型
	 */
	public final static String CALLBACK_BODY_TYPE = "application/json";
	/**
	 * 回调请求时会在请求
	 */
	public final static String CALLBACK_AUTH_HEADER = "Authorization";
	/**
	 * 创建上传凭证
	 * @return
	 */
	public UploadTokenVo createUploadToken(UploadTokenParam param);
	/**
	 * 回调处理
	 * @param callbackAuthHeader
	 * @param callbackUrl
	 * @param callbackBody
	 * @return
	 */
	public UploadResult handleCallback(String callbackAuthHeader, String callbackUrl, String callbackBody);
}
