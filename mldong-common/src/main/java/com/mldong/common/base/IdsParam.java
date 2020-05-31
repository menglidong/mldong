package com.mldong.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
public class IdsParam {
	/**
	 * id集合
	 */
	@ApiModelProperty(value="id集合",required=true)
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
}
