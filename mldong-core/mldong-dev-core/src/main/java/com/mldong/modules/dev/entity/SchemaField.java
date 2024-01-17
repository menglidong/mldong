package com.mldong.modules.dev.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "SchemaField对象", description = "模型字段")
public class SchemaField implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("所属模型")
    private Long schemaId;

    @ApiModelProperty("字段名称")
    private String fieldName;

    @ApiModelProperty("字段注释")
    private String remark;

    @ApiModelProperty("字段长度")
    private Integer fieldSize;

    @ApiModelProperty("数据类型")
    private String dataType;

    @ApiModelProperty("是否主键")
    private Integer isPrimary;

    @ApiModelProperty("允许为空")
    private Integer nullable;

    @ApiModelProperty("默认值")
    private String defaultValue;

    @ApiModelProperty("表单组件")
    private String component;

    @ApiModelProperty("排序")
    private Long sort;

    @ApiModelProperty("扩展属性JSON")
    private String variable;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建用户")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("更新用户")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("是否删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}
