package com.mldong.common.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.mldong.common.logger.LoggerModel;
import com.mldong.common.tool.JsonTool;
/**
 * 全局的响应处理，这里能拿到控制层的返回参数，然后可以对该参数进行加密处理再返回，或是日志记录
 * @author mldong
 *
 */
@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object>{

	@Override
	public boolean supports(MethodParameter returnType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object returnData, MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		LoggerModel loggerModel = RequestHolder.getLoggerModel();
		if(null != loggerModel) {
			// 设置返回结果，这里拿到的是controller方法的返回值
			loggerModel.setReturnData(null==returnData?"":JsonTool.toJson(returnData));
		}
		return returnData;
	}

}
