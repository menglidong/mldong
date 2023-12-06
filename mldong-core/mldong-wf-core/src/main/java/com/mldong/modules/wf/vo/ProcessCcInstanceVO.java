package com.mldong.modules.wf.vo;

import com.mldong.modules.wf.entity.ProcessCcInstance;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 流程实例抄送
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
@Data
@ApiModel(value = "ProcessCcInstanceVO对象", description = "流程实例抄送VO")
public class ProcessCcInstanceVO extends ProcessCcInstance {
}
