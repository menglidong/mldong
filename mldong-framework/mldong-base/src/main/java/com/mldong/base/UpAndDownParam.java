package com.mldong.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author mldong
 * @date 2023/9/27
 */
@Data
@ApiModel(value = "UpAndDownParam对象", description = "启用/禁用实体")
public class UpAndDownParam {
    @ApiModelProperty(value = "id集合")
    private List<Object> ids;
    @ApiModelProperty(value = "操作类型")
    private YesNoEnum opType;
}
