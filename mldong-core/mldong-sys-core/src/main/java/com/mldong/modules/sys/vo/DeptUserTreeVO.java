package com.mldong.modules.sys.vo;

import cn.hutool.core.lang.Dict;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 部门用户树VO
 * @author mldong
 * @date 2023/10/10
 */
@Data
@ApiModel(value = "DeptUserTreeVO", description = "部门用户树VO")
public class DeptUserTreeVO {
    @ApiModelProperty(value = "部门/用户姓名")
    private String label;
    @ApiModelProperty(value = "部门/用户id")
    private Long value;
    @ApiModelProperty(value = "节点类型")
    private String nodeType;
    @ApiModelProperty(value = "是否禁止选中")
    private boolean disabled;
    @ApiModelProperty(value = "扩展信息")
    private Dict ext;
    @ApiModelProperty(value = "子机构/用户")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptUserTreeVO> children;
}
