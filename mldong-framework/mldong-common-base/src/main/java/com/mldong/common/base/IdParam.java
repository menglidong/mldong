package com.mldong.common.base;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class IdParam {
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",required=true)
	@NotNull(message="id不能为空")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
