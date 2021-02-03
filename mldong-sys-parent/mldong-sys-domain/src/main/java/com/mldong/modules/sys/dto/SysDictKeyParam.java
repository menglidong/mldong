package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import com.mldong.common.annotation.FlagValidator;

public class SysDictKeyParam {
	@ApiModelProperty(value="字典唯一编码(表名_字段名)",required=true)
	@NotBlank(message="字典唯一编码不能为空")
	private String dictKey;
	@ApiModelProperty(value="类型不能为空",required=true)
	@FlagValidator(values="enum,db,custom",message="类型只能是enum、db、custom",required=true)
	private String type;
	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
