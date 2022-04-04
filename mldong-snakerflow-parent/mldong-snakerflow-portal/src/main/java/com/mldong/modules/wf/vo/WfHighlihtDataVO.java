package com.mldong.modules.wf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
@ApiModel
public class WfHighlihtDataVO {
    @ApiModelProperty(value = "历史节点名称集合")
    private List<String> historyNodeNames = new ArrayList<>();
    @ApiModelProperty(value = "历史边名称集合")
    private List<String> historyEdgeNames = new ArrayList<>();
    @ApiModelProperty(value = "进行中节点名称集合")
    private List<String> activeNodeNames = new ArrayList<>();

    public List<String> getHistoryNodeNames() {
        return historyNodeNames;
    }

    public void setHistoryNodeNames(List<String> historyNodeNames) {
        this.historyNodeNames = historyNodeNames;
    }

    public List<String> getHistoryEdgeNames() {
        return historyEdgeNames;
    }

    public void setHistoryEdgeNames(List<String> historyEdgeNames) {
        this.historyEdgeNames = historyEdgeNames;
    }

    public List<String> getActiveNodeNames() {
        return activeNodeNames;
    }

    public void setActiveNodeNames(List<String> activeNodeNames) {
        this.activeNodeNames = activeNodeNames;
    }
}
