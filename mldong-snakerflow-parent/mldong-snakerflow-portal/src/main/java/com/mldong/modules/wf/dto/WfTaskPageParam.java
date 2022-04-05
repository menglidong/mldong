package com.mldong.modules.wf.dto;

import com.mldong.common.base.PageParam;
import io.swagger.annotations.ApiModelProperty;
import org.snaker.engine.entity.WorkItem;

public class WfTaskPageParam extends PageParam<WorkItem> {
    @ApiModelProperty(value = "流程名称", position = 10)
    private String displayName;
    @ApiModelProperty(value = "任务名称", position = 15)
    private String taskName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
