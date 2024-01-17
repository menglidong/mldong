package com.mldong.modules.dev.vo;

import com.mldong.modules.dev.entity.SchemaField;
import io.swagger.annotations.ApiModel;
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
}
