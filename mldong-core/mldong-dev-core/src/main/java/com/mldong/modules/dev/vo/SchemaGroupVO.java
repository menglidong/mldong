package com.mldong.modules.dev.vo;

import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.entity.SchemaGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 模型分组
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Data
@ApiModel(value = "SchemaGroupVO对象", description = "模型分组VO")
public class SchemaGroupVO extends SchemaGroup {
    @ApiModelProperty("模型列表")
    private List<SchemaVO> schemaList;
}
