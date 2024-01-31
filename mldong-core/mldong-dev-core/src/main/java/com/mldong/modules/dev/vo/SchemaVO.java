package com.mldong.modules.dev.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.entity.SchemaField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 数据模型
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Data
@ApiModel(value = "SchemaVO对象", description = "数据模型VO")
public class SchemaVO extends Schema {
    @ApiModelProperty(value = "模块名")
    private String moduleName;
    @ApiModelProperty(value = "小驼峰")
    private String tableCamelName;
    @ApiModelProperty(value = "类名")
    private String className;
    @ApiModelProperty("列信息")
    private List<SchemaFieldVO> columns;
    @ApiModelProperty("扩展属性")
    private JSONObject ext;
    @ApiModelProperty(value = "模型分组")
    private SchemaGroupVO schemaGroup;
    public JSONObject getExt() {
        if(JSONUtil.isTypeJSON(getVariable())) {
            ext = JSONUtil.parseObj(getVariable());
        }
        if(ObjectUtil.isNull(ext)) {
            ext = new JSONObject();
        }
        return ext;
    }

    public String getModuleName() {
        String tableName = getTableName();
        return tableName.substring(0,tableName.indexOf("_"));
    }

    public String getTableCamelName() {
        String tableName = getTableName();
        if(StrUtil.isEmpty(tableName)) return tableName;
        String removePrefix = tableName.replace(getModuleName() + "_","");
        return StrUtil.toCamelCase(removePrefix);
    }

    public String getClassName() {
        String tableCamelName = getTableCamelName();
        return StrUtil.upperFirst(tableCamelName);
    }
}
