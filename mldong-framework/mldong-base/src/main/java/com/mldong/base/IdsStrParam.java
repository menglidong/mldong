package com.mldong.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author mldong
 * @date 2024/2/5
 */
@Data
public class IdsStrParam {
    @ApiModelProperty("ids集合")
    @NotEmpty(message = "ids不能为空")
    private List<String> ids;
}
