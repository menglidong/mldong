package com.mldong.modules.wf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@ApiModel
public class WfOrderParam {
    @ApiModelProperty(value = "流程定义ID")
    @NotBlank(message = "流程定义ID不能为空")
    private String processId;
    @ApiModelProperty(value = "流程参数")
    @NotNull(message = "流程参数不能为空")
    private Map<String,Object> args;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }
}
