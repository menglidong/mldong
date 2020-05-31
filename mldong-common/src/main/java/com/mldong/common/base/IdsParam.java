package com.mldong.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.validation.constraints.NotEmpty;
public class IdsParam {
	/**
	 * id集合
	 */
	@ApiModelProperty(value="id集合",required=true)
	@NotEmpty(message="集合不能为空")
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
}
