package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessTaskActor;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程任务和参与人关系
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessTaskActorVO对象", description = "流程任务和参与人关系VO")
public class ProcessTaskActorVO extends ProcessTaskActor {
}
