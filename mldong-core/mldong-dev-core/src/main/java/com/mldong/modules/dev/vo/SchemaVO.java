package com.mldong.modules.dev.vo;

import com.mldong.modules.dev.entity.Schema;
import io.swagger.annotations.ApiModel;
import lombok.Data;

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
}
