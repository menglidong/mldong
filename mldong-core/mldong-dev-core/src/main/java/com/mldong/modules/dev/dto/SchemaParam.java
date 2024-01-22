package com.mldong.modules.dev.dto;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
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
@ApiModel(value = "SchemaParam对象", description = "数据模型")
public class SchemaParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class,UpdateListKeys.class,UpdateSearchFormKeys.class})
    private Long id;

    @ApiModelProperty(value = "所属分组")
    private Long schemaGroupId;

    @ApiModelProperty(value = "表名称", required = true)
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    @ApiModelProperty(value = "表注释", required = true)
    @NotBlank(message = "表注释不能为空")
    private String remark;

    @ApiModelProperty(value = "额外说明")
    private String otherRemark;

    @ApiModelProperty(value = "表类型")
    private Integer tableType;

    @ApiModelProperty(value = "表单类型")
    private Integer formType;

    @ApiModelProperty(value = "是否树")
    private Integer isTree;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "搜索表单key，可用于排序")
    private String searchFormKeys;

    @ApiModelProperty(value = "列表key，可用于排序")
    private String listKeys;

    @ApiModelProperty(value = "扩展属性JSON")
    private String variable;
    @ApiModelProperty(value = "扩展属性JSON对象")
    private Dict ext;

    @ApiModelProperty(value = "表集合")
    @NotEmpty(message = "表名称列表不能为空",groups = ImportSchema.class)
    private List<String> tableNames;

    public interface ImportSchema {}
    public interface UpdateListKeys {}
    public interface UpdateSearchFormKeys {}
}
