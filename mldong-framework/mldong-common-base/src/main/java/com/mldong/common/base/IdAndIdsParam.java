package com.mldong.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
/**
 * 一对多关系模型参数
 * @author mldong
 *
 */
public class IdAndIdsParam {
	@ApiModelProperty(value="主表id",required=true)
	private String id;
	@ApiModelProperty(value="关联表id集合",required=true)
	private List<String> ids;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
