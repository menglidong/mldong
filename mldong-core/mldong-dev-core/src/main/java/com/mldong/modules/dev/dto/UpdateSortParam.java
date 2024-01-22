package com.mldong.modules.dev.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author mldong
 * @Description 更新排序参数
 * @date 2024-01-22
 */
@Data
@ApiModel
public class UpdateSortParam {
    @ApiModelProperty(value = "模型字段ID")
    @NotNull(message = "模型ID不能为空")
    private Long schemaId;
    @ApiModelProperty(value = "拖拽行ID")
    @NotNull(message = "拖拽行ID不能为空")
    private Long dragRowId;
    @ApiModelProperty(value = "拖动到指定行ID")
    @NotNull(message = "拖动到指定行ID不能为空")
    private Long hoverRowId;
}
