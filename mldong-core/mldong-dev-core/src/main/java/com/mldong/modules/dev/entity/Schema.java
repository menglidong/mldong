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
 * 数据模型
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Getter
@Setter
@TableName("dev_schema")
@ApiModel(value = "Schema对象", description = "数据模型")
public class Schema implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("所属分组")
    private Long schemaGroupId;

    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("表注释")
    private String remark;

    @ApiModelProperty("额外说明")
    private String otherRemark;

    @ApiModelProperty("表类型")
    private Integer tableType;

    @ApiModelProperty("表单类型")
    private Integer formType;

    @ApiModelProperty("是否树")
    private Integer isTree;

    @ApiModelProperty("排序")
    private Long sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("搜索表单key，可用于排序")
    private String searchFormKeys;

    @ApiModelProperty("列表key，可用于排序")
    private String listKeys;

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
