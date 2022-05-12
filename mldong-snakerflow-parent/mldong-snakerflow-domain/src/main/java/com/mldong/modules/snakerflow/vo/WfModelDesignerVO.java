package com.mldong.modules.snakerflow.vo;

import com.mldong.modules.snakerflow.entity.WfModelDesigner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author mldong
 * @date 2022/5/12
 */
@ApiModel(description="模型设计返回VO")
public class WfModelDesignerVO extends WfModelDesigner {
    @ApiModelProperty(value = "流程定义xml")
    private String xml;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
