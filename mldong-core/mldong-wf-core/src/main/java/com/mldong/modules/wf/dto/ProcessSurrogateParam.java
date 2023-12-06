package com.mldong.modules.wf.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
/**
 * <p>
 * 流程委托代理
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
@Getter
@Setter
@TableName("wf_process_surrogate")
@ApiModel(value = "ProcessSurrogateParam对象", description = "流程委托代理")
public class ProcessSurrogateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "流程名称")
    private String processName;

    @ApiModelProperty(value = "授权人", required = true)
    @NotBlank(message = "授权人不能为空")
    private String operator;

    @ApiModelProperty(value = "代理人", required = true)
    @NotBlank(message = "代理人不能为空")
    private String surrogate;

    @ApiModelProperty(value = "授权开始时间")
    private Date startTime;

    @ApiModelProperty(value = "授权结束时间")
    private Date endTime;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;


}
