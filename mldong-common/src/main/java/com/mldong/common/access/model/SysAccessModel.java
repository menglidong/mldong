package com.mldong.common.access.model;

import java.io.Serializable;
import java.util.List;
/**
 * 权限资源模型
 * @author mldong
 *
 */
public class SysAccessModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5248234808496873095L;
	/**
	 * 主键，这里和access一至
	 */
	private String id;
	/**
	 * 权限标识，与请求uri对应,/sys/user/save===>sys:user:save
	 */
	private String access;
	/**
	 * 请求uri，与RequestMapper对应
	 */
	private String uri;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限备注
	 */
	private String remark;
	/**
	 * 排序,暂不实现
	 */
	private int sort;
	/**
	 * 子权限
	 */
	private List<SysAccessModel> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public List<SysAccessModel> getChildren() {
		return children;
	}
	public void setChildren(List<SysAccessModel> children) {
		this.children = children;
	}
	
}
