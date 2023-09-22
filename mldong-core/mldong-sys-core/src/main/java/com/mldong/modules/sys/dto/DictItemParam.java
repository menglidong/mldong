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
 * 字典项
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Getter
@Setter
@TableName("sys_dict_item")
@ApiModel(value = "DictItemParam对象", description = "字典项")
public class DictItemParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项ID", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "字典项ID不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "字典ID", required = true)
    @NotNull(message = "字典ID不能为空")
    private Long dictId;

    @ApiModelProperty(value = "字典项名称", required = true)
    @NotBlank(message = "字典项名称不能为空")
    private String name;

    @ApiModelProperty(value = "唯一编码", required = true)
    @NotBlank(message = "唯一编码不能为空")
    private String code;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;

    @ApiModelProperty(value = "备注")
    private String remark;


}
