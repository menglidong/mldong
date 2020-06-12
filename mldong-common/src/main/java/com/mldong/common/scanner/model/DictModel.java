package com.mldong.common.scanner.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class DictModel implements Serializable{

	private static final long serialVersionUID = -832930180178912158L;

	@ApiModelProperty("字典名称")
	private String name;

	@ApiModelProperty("字典唯一编码")
	private String dictKey;
	/**
	 * 字典项
	 */
	@ApiModelProperty("字典项集合")
	private List<DictItemModel> items;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public List<DictItemModel> getItems() {
		return items;
	}
	public void setItems(List<DictItemModel> items) {
		this.items = items;
	}
	
	
}
