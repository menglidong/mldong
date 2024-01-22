package com.mldong.modules.dev.dto;
import cn.hutool.core.lang.Dict;
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
 * 模型字段
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Getter
@Setter
@TableName("dev_schema_field")
@ApiModel(value = "SchemaFieldParam对象", description = "模型字段")
public class SchemaFieldParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "所属模型", required = true)
    @NotNull(message = "所属模型不能为空")
    private Long schemaId;

    @ApiModelProperty(value = "字段名称", required = true)
    @NotBlank(message = "字段名称不能为空")
    private String fieldName;

    @ApiModelProperty(value = "字段注释", required = true)
    @NotBlank(message = "字段注释不能为空")
    private String remark;

    @ApiModelProperty(value = "字段长度")
    private Integer fieldSize;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "是否主键")
    private Integer isPrimary;

    @ApiModelProperty(value = "允许为空")
    private Integer nullable;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "表单组件")
    private String component;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "扩展属性JSON")
    private String variable;
    @ApiModelProperty(value = "扩展属性JSON对象")
    private Dict ext;

}
