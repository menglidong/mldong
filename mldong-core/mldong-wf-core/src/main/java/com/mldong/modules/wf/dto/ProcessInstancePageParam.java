package com.mldong.modules.wf.dto;

import com.mldong.base.PageParam;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.entity.ProcessInstance;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class ProcessInstancePageParam extends PageParam<ProcessInstance> {
    private YesNoEnum isCC=YesNoEnum.NO;
}