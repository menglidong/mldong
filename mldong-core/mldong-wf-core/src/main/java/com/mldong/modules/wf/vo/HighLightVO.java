package com.mldong.modules.wf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mldong
 * @date 2023/10/7
 */
@ApiModel(value = "HighLightVO", description = "流程高亮数据")
@Data
public class HighLightVO {
    @ApiModelProperty(value = "历史节点名称集合")
    private List<String> historyNodeNames = new ArrayList<>();
    @ApiModelProperty(value = "历史边名称集合")
    private List<String> historyEdgeNames = new ArrayList<>();
    @ApiModelProperty(value = "进行中节点名称集合")
    private List<String> activeNodeNames = new ArrayList<>();
}
