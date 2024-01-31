package com.mldong.modules.dev.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.dev.entity.SchemaField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 模型字段
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Data
@ApiModel(value = "SchemaFieldVO对象", description = "模型字段VO")
public class SchemaFieldVO extends SchemaField {
    @ApiModelProperty(value = "小驼峰")
    private String fieldCamelName;
    @ApiModelProperty("扩展属性")
    private JSONObject ext;
    @ApiModelProperty(value = "列表字段排序")
    private Integer listSort;
    @ApiModelProperty(value = "搜索字段排序")
    private Integer searchSort;
    public JSONObject getExt() {
        if(JSONUtil.isTypeJSON(getVariable())) {
            ext = JSONUtil.parseObj(getVariable());
        }
        if(ObjectUtil.isNull(ext)) {
            ext = new JSONObject();
        }
        return ext;
    }
    public String getFieldCamelName() {
        String fieldName = getFieldName();
        if(StrUtil.isEmpty(fieldName)) return fieldName;
        return StrUtil.toCamelCase(fieldName);
    }
}
