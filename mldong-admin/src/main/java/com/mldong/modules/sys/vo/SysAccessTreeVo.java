package com.mldong.modules.sys.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.mldong.common.access.model.SysAccessModel;

public class SysAccessTreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6701575768250365532L;
	@ApiModelProperty("默认展开节点")
	private List<String> defaultExpandedKeys;
	@ApiModelProperty("默认选中节点")
	private List<String> defaultCheckedKeys;
	@ApiModelProperty("节点集合")
	private List<SysAccessModel> rows;
	public List<String> getDefaultExpandedKeys() {
		return defaultExpandedKeys;
	}
	public void setDefaultExpandedKeys(List<String> defaultExpandedKeys) {
		this.defaultExpandedKeys = defaultExpandedKeys;
	}
	public List<String> getDefaultCheckedKeys() {
		return defaultCheckedKeys;
	}
	public void setDefaultCheckedKeys(List<String> defaultCheckedKeys) {
		this.defaultCheckedKeys = defaultCheckedKeys;
	}
	public List<SysAccessModel> getRows() {
		return rows;
	}
	public void setRows(List<SysAccessModel> rows) {
		this.rows = rows;
	}
	
	
}
