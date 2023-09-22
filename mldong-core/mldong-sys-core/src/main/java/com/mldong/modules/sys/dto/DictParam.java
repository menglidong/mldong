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
 * 字典
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Getter
@Setter
@TableName("sys_dict")
@ApiModel(value = "DictParam对象", description = "字典")
public class DictParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典ID", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "字典ID不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String name;

    @ApiModelProperty(value = "唯一编码", required = true)
    @NotBlank(message = "唯一编码不能为空")
    private String code;

    @ApiModelProperty(value = "数据类型(1:字符串；2：整型)")
    private Integer dataType;

    @ApiModelProperty(value = "分组编码", required = true)
    @NotBlank(message = "分组编码不能为空")
    private String groupCode;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "枚举编码")
    @NotBlank(message = "枚举编码不能为空",groups = {DictType.class})
    private String dictType;
    public interface DictType {}

}
