package com.mldong.common.base;
/**
 * 操作类型
 * @author mldong
 *
 */
public enum OperateTypeEnum {
	EQ("等于","=?"),
	NE("不等于","<>?"),
	GT("大于",">"),
	GE("大于等于",">=?"),
	LT("小于","<?"),
	LE("小于等于","<=?"),
	BT("区间范围","between ? and ?"),
	NBT("非区间范围","not between ? and ?"),
	LIKE("模糊","like ?"),
	LLIKE("左模糊","like ?"),
	RLIKE("右模糊","like ?"),
	IN("包含","in"),
	NIN("不包含","not in(?)"),
	OR("或", "or (?)")
	;
	OperateTypeEnum(String name,String value) {
		this.name = name;
		this.value = value;
	}
	private String name;
	private String value;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
