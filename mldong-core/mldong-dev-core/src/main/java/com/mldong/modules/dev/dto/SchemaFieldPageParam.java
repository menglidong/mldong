package com.mldong.modules.dev.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.dev.entity.SchemaField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class SchemaFieldPageParam extends PageParam<SchemaField> {
}