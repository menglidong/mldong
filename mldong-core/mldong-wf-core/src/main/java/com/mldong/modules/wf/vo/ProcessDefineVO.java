package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessDefine;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程定义
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessDefineVO对象", description = "流程定义VO")
public class ProcessDefineVO extends ProcessDefine {
}
