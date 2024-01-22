package com.mldong.modules.dev.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.dev.entity.Schema;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class SchemaPageParam extends PageParam<Schema> {

}