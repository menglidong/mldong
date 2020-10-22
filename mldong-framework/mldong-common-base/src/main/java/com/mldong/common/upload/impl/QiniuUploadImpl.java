package com.mldong.common.upload.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.config.GlobalProperties;
import com.mldong.common.exception.BizException;
import com.mldong.common.tool.DateTool;
import com.mldong.common.tool.JsonTool;
import com.mldong.common.tool.StringTool;
import com.mldong.common.upload.CommonUpload;
import com.mldong.common.upload.UploadMimeType;
import com.mldong.common.upload.model.UploadResult;
import com.mldong.common.upload.model.UploadTokenParam;
import com.mldong.common.upload.model.UploadTokenVo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
/**
 * 通过上传七牛实现
 * @author mldong
 *
 */
@Component
public class QiniuUploadImpl implements CommonUpload{
	@Autowired
	private GlobalProperties globalProperties;
	@Override
	public UploadTokenVo createUploadToken(UploadTokenParam param) {
		String accessKey = globalProperties.getQiniuAccessKey();
		String secretKey = globalProperties.getQiniuSecretKey();
		String bucket = globalProperties.getQiniuBucket();
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		// 自定义资源名支持魔法变量和自定义变量
		String bizType = param.getBizType();
		String dateString = DateTool.dateToString(new Date(), "yyyyMM");
		String uuid = UUID.randomUUID().toString();
		putPolicy.put("saveKey", String.format("%s/%s/%s$(ext)", bizType,dateString,uuid));
		// 限定上传文件大小最小值
		putPolicy.put("fsizeMin", param.getFileSizeMin());
		// 限定上传文件大小最大值
		if(param.getFileSizeMax()>0) {
			putPolicy.put("fsizeLimit", param.getFileSizeMax());
		}
		// 限制文件类型
		String mimeType = UploadMimeType.getMimeType(param.getFileExt(), ";");
		if(StringTool.isNotEmpty(mimeType)) {
			putPolicy.put("mimeLimit", mimeType);
		}
		
		// 返回结果定义
		Map<String,String> result = new HashMap<String, String>();
		result.put("baseUrl","$(x:baseUrl)");
		result.put("bizId","${x:bizId}");
		result.put("bizType","$(x:bizType)");
		result.put("fileExt","$(ext)");
		result.put("fileName","$(fname)");
		result.put("fileSize","$(fsize)");
		result.put("mimeType","$(mimeType)");
		result.put("url","$(key)");
		String body = JsonTool.toJson(result);
		// 设置回调
		if(StringTool.isNotEmpty(param.getCallbackUrl())) {
			putPolicy.put("callbackUrl", param.getCallbackUrl());
			putPolicy.put("callbackBody", body);
			putPolicy.put("callbackBodyType", CALLBACK_BODY_TYPE);
		}
		putPolicy.put("returnBody", body);
		long expireSeconds = 3600;
		String uploadToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return new UploadTokenVo(UUID.randomUUID().toString(), uploadToken, expireSeconds, param);
	}

	@Override
	public UploadResult handleCallback(String callbackAuthHeader,
			String callbackUrl, String callbackBody) {
		String accessKey = globalProperties.getQiniuAccessKey();
		String secretKey = globalProperties.getQiniuSecretKey();
		Auth auth = Auth.create(accessKey, secretKey);
		boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody.getBytes(), CALLBACK_BODY_TYPE);
		if(validCallback) {
			return JsonTool.jsonToBean(callbackBody, UploadResult.class);
		}
		throw new BizException(GlobalErrEnum.GL99990100);
	}

}
