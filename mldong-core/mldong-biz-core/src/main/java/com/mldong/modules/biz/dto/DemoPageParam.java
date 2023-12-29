package com.mldong.modules.biz.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.biz.entity.Demo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class DemoPageParam extends PageParam<Demo> {
}