package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessTask;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程任务
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessTaskVO对象", description = "流程任务VO")
public class ProcessTaskVO extends ProcessTask {
}
