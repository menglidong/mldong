package com.mldong.modules.sys.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.sys.entity.VisLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class VisLogPageParam extends PageParam<VisLog> {
}