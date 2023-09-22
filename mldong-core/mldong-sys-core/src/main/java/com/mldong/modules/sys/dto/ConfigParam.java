package com.mldong.modules.sys.dto;
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
 * 配置
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Getter
@Setter
@TableName("sys_config")
@ApiModel(value = "ConfigParam对象", description = "配置")
public class ConfigParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配置ID", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "配置ID不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "配置名称", required = true)
    @NotBlank(message = "配置名称不能为空")
    private String name;

    @ApiModelProperty(value = "唯一编码", required = true)
    @NotBlank(message = "唯一编码不能为空")
    private String code;

    @ApiModelProperty(value = "分组编码", required = true)
    @NotBlank(message = "分组编码不能为空")
    private String groupCode;

    @ApiModelProperty(value = "配置内容")
    private String content;

    @ApiModelProperty(value = "是否系统")
    private Integer isSys;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;

    @ApiModelProperty(value = "备注")
    private String remark;


}
