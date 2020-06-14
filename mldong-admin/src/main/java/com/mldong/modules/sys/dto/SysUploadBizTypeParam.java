package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class SysUploadBizTypeParam {
	@ApiModelProperty(value="业务类型")
	@NotBlank(message="业务类型不能为空")
	private String bizType;

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
