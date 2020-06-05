package com.mldong.generator.core.model;
/**
 * 特殊注释转换=>字典类型实体
 * @author mldong
 *
 */
public class CodedType {
	/**
	 * 值
	 */
	private int value;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 键名
	 */
	private String name;
	public CodedType(int value, String remark, String name) {
		super();
		this.value = value;
		this.remark = remark;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
	
}
