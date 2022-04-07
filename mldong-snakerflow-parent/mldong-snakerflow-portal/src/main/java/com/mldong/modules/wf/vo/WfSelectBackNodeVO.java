package com.mldong.modules.wf.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 可还回的节点实体类
 * @author mldong
 * @date 2022/4/7
 */
public class WfSelectBackNodeVO {
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    @ApiModelProperty(value = "任务节点唯一编码")
    private String taskKey;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }
}
