package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessSurrogate;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程委托代理
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
@Data
@ApiModel(value = "ProcessSurrogateVO对象", description = "流程委托代理VO")
public class ProcessSurrogateVO extends ProcessSurrogate {
}
