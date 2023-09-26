package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessInstance;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程实例
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessInstanceVO对象", description = "流程实例VO")
public class ProcessInstanceVO extends ProcessInstance {
}
