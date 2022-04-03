package com.mldong.modules.wf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class WfIdParam {
    @ApiModelProperty(value = "主键")
    @NotBlank(message = "ID不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
