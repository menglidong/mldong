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
	private Object dictItemValue;
	@ApiModelProperty("字典项键")
	private String dictItemKey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getDictItemValue() {
		return dictItemValue;
	}
	public void setDictItemValue(Object dictItemValue) {
		this.dictItemValue = dictItemValue;
	}

	public String getDictItemKey() {
		return dictItemKey;
	}

	public void setDictItemKey(String dictItemKey) {
		this.dictItemKey = dictItemKey;
	}
}
