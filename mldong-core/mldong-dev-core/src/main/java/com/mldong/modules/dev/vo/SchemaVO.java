package com.mldong.modules.dev.vo;

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
    @ApiModelProperty("列信息")
    private List<SchemaFieldVO> columns;
    @ApiModelProperty("扩展属性")
    private JSONObject ext;

    public JSONObject getExt() {
        if(JSONUtil.isTypeJSON(getVariable())) {
            ext = JSONUtil.parseObj(getVariable());
        }
        return ext;
    }

    public String getModuleName() {
        String tableName = getTableName();
        return tableName.substring(0,tableName.indexOf("_"));
    }
}
