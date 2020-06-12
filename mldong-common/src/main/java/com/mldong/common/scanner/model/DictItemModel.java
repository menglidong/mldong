package com.mldong.common.scanner.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DictItemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597276140499576903L;
	@ApiModelProperty("字典项名称")
	private String name;
	@ApiModelProperty("字典项值")
	private int dictItemValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDictItemValue() {
		return dictItemValue;
	}
	public void setDictItemValue(int dictItemValue) {
		this.dictItemValue = dictItemValue;
	}
	
}
