package com.mldong.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class IdsParam {
    @ApiModelProperty("ids集合")
    @NotEmpty(message = "ids不能为空")
    private List<Long> ids;
}
