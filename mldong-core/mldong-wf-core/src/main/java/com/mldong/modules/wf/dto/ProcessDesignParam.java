package com.mldong.modules.wf.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mldong.validation.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * <p>
 * 流程设计
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
@Getter
@Setter
@TableName("wf_process_design")
@ApiModel(value = "ProcessDesignParam对象", description = "流程设计")
public class ProcessDesignParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "唯一编码", required = true)
    @NotBlank(message = "唯一编码不能为空")
    private String name;
    @ApiModelProperty(value = "显示名称", required = true)
    @NotBlank(message = "显示名称不能为空")
    private String displayName;
    @ApiModelProperty("流程分类")
    private String type;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("是否已部署")
    private Integer isDeployed;
    @ApiModelProperty("备注")
    private String remark;

}
