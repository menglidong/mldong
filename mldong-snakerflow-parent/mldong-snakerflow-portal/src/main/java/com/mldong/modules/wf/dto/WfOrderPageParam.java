package com.mldong.modules.wf.dto;

import com.mldong.common.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Order;

@ApiModel
public class WfOrderPageParam extends PageParam<HistoryOrder> {

}
