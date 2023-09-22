package com.mldong.dict.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DictItemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597276140499576903L;
	@ApiModelProperty("字典项名称")
	private String name;
	@ApiModelProperty("字典项值")
	private Object dictItemValue;
	@ApiModelProperty("字典项键")
	private String dictItemKey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
