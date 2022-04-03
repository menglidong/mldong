package com.mldong.modules.wf.dto;

import com.mldong.common.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WfProcessPageParam extends PageParam<Process> {
    @ApiModelProperty(value = "流程定义状态", position = 5)
    private Integer state;
    @ApiModelProperty(value = "流程定义名称", position = 10)
    private String displayName;
    @ApiModelProperty(value = "流程定义唯一编码", position = 15)
    private String name;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
