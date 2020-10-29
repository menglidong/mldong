package com.mldong.common.base;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AuditParam {
    @ApiModelProperty(value="审核的记录编号集合",required=true)
    @NotEmpty(message="审核的记录编号集合不能为空")
    private List<Long> ids;
    @ApiModelProperty(value="审核不通过原因")
    private String rejectReason;
    @ApiModelProperty(value="是否通过(1->否,2->是)",required=true)
    @NotNull(message="通过标识不能为空")
    private YesNoEnum pass;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public YesNoEnum getPass() {
        return pass;
    }

    public void setPass(YesNoEnum pass) {
        this.pass = pass;
    }
}
