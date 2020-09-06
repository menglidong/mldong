package com.mldong.generator.core.model;

import java.util.ArrayList;
import java.util.List;

import com.mldong.common.util.StringUtil;

/**
 * 列对象模型
 * @author mldong
 *
 */
public class Column {
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 是否为主键
	 */
	private boolean primaryKey;
	/**
	 * 是否为外键
	 */
	private boolean foreignKey;
	/**
	 * 列长度
	 */
	private long size;
	/**
	 * 小数点位数
	 */
	private int decimalDigits;
	/**
	 * 是否为空
	 */
	private boolean nullable;
	/**
	 * 是否默认自增
	 */
	private boolean autoincrement;
	/**
	 * 默认值
	 */
	private String defaultValue;
	/**
	 * 字段注释
	 */
	private String remark;
	/**
	 * 数据库类型
	 */
	private String dataType;
	
	/**
	 * 实体类对应属性
	 */
	private String javaProperty;
	/**
	 * set方法名称
	 */
	private String setMethodName;
	/**
	 * get方法名称
	 */
	private String getMethodName;
	/**
	 * java数据类型
	 */
	private String javaType;
	/**
	 * 数据类型全狗
	 */
	private String fullJavaType;
	/**
	 * 枚举集合
	 */
	private List<CodedType> codedTypes = new ArrayList<>();
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public boolean isForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isAutoincrement() {
		return autoincrement;
	}
	public void setAutoincrement(boolean autoincrement) {
		this.autoincrement = autoincrement;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getJavaProperty() {
		return javaProperty;
	}
	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}
	public String getSetMethodName() {
		return setMethodName;
	}
	public void setSetMethodName(String setMethodName) {
		this.setMethodName = setMethodName;
	}
	public String getGetMethodName() {
		return getMethodName;
	}
	public void setGetMethodName(String getMethodName) {
		this.getMethodName = getMethodName;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getFullJavaType() {
		return fullJavaType;
	}
	public void setFullJavaType(String fullJavaType) {
		this.fullJavaType = fullJavaType;
	}
	public List<CodedType> getCodedTypes() {
		return codedTypes;
	}
	public void setCodedTypes(List<CodedType> codedTypes) {
		this.codedTypes = codedTypes;
	}
	public String getGetterMethodName() {
        StringBuilder sb = new StringBuilder();

        sb.append(javaProperty);
        if (Character.isLowerCase(sb.charAt(0)) && ((sb.length() == 1) || Character.isLowerCase(sb.charAt(1)))) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        if ("boolean".equals(javaType)) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }

        return sb.toString();
    }

    public String getSetterMethodName() {
        StringBuilder sb = new StringBuilder();

        sb.append(javaProperty);
        if (Character.isLowerCase(sb.charAt(0)) && ((sb.length() == 1) || Character.isLowerCase(sb.charAt(1)))) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        sb.insert(0, "set");

        return sb.toString();
    }
    public boolean isBigDecimal() {
        return "BigDecimal".equals((javaType));
    }

    public boolean isBoolean() {
        return "boolean".equals((javaType));
    }

    public boolean isDate() {
        return "Date".equals((javaType));
    }
   
    public boolean isCodedType () {
    	return null!=this.codedTypes&&!this.codedTypes.isEmpty();
    }
    public String getCodedRemark() {
    	if(StringUtil.isNotEmpty(remark)) {
    		if(isCodedType()) {
        		return remark.substring(0, remark.indexOf("("));
    		} else {
    			return remark;
    		}
    	}
    	return "";
    }
}
