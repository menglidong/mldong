package com.mldong.modules.wf.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
/**
 * <p>
 * 流程定义
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_define")
@ApiModel(value = "ProcessDefineParam对象", description = "流程定义")
public class ProcessDefineParam implements Serializable {

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

    @ApiModelProperty(value = "流程类型")
    private String type;

    @ApiModelProperty(value = "流程是否可用(1可用；0不可用)")
    private Integer state;

    @ApiModelProperty(value = "流程模型定义")
    private byte[] content;

    @ApiModelProperty(value = "版本")
    private Integer version;


}
