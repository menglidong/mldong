package com.mldong.modules.wf.dto;

import com.mldong.common.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Order;

@ApiModel
public class WfOrderPageParam extends PageParam<HistoryOrder> {
    @ApiModelProperty(value = "流程实例状态", position = 5)
    private Integer orderState;
    @ApiModelProperty(value = "流程名称", position = 10)
    private String displayName;
    @ApiModelProperty(value = "流程唯一编码", position = 15)
    private String name;
    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
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
