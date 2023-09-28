package com.mldong.modules.wf.vo;

import cn.hutool.json.JSONObject;
import com.mldong.modules.wf.entity.ProcessInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "显示名称")
    private String displayName;
    @ApiModelProperty(value = "唯一编码")
    private String name;
    @ApiModelProperty(value = "流程设计json对象")
    private JSONObject jsonObject;
}
