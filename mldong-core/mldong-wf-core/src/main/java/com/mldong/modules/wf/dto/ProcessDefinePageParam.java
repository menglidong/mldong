package com.mldong.modules.wf.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.wf.entity.ProcessDefine;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class ProcessDefinePageParam extends PageParam<ProcessDefine> {
}