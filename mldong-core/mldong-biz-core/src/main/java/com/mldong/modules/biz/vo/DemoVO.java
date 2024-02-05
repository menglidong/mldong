package com.mldong.modules.biz.vo;

import com.mldong.modules.biz.entity.Demo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 演示
 * </p>
 *
 * @author mldong
 * @since 2023-12-28
 */
@Data
@ApiModel(value = "DemoVO对象", description = "演示VO")
public class DemoVO extends Demo {
    @ApiModelProperty(value = "禁用的key列表")
    public List<String> $disabledKeyList;
    @ApiModelProperty(value = "隐藏的key列表")
    public List<String> $hideKeyList;
}
