package com.mldong.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IdStrParam {
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private String id;
}
