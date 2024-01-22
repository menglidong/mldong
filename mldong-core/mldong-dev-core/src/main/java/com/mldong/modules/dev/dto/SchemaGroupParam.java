package com.mldong.modules.dev.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
/**
 * <p>
 * 模型分组
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Getter
@Setter
@TableName("dev_schema_group")
@ApiModel(value = "SchemaGroupParam对象", description = "模型分组")
public class SchemaGroupParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "分组名称", required = true)
    @NotBlank(message = "分组名称不能为空")
    private String name;

    @ApiModelProperty(value = "唯一编码", required = true)
    @NotBlank(message = "唯一编码不能为空")
    private String code;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "备注")
    private String remark;


}
