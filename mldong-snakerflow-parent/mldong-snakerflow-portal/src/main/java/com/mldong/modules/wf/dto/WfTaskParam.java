package com.mldong.modules.wf.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class WfTaskParam {
    @ApiModelProperty(value = "任务ID", position = 5)
    @NotNull(message = "任务ID不能为空")
    private String taskId;
    @ApiModelProperty(value = "流程参数", position = 10)
    @NotNull(message = "流程参数不能为空")
    private Map<String,Object> args;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }
}
