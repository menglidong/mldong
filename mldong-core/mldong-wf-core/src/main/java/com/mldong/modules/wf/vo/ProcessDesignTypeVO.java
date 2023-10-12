package com.mldong.modules.wf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 流程设计分类
 * @author mldong
 * @date 2023/10/12
 */
@Data
@ApiModel(value = "ProcessDesignTypeVO", description = "流程设计分类")
public class ProcessDesignTypeVO {

    @ApiModelProperty(value = "流程分类编码")
    private String type;
    @ApiModelProperty(value = "流程分类显示文本")
    private String title;
    @ApiModelProperty(value = "流程设计")
    private List<ProcessDesignVO> items;
}
