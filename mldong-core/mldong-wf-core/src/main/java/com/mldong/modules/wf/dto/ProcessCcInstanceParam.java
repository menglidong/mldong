package com.mldong.modules.wf.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 流程实例抄送
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
@Getter
@Setter
@TableName("wf_process_cc_instance")
@ApiModel(value = "ProcessCcInstanceParam对象", description = "流程实例抄送")
public class ProcessCcInstanceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "流程实例ID", required = true)
    @NotNull(message = "流程实例ID不能为空")
    private Long processInstanceId;

    @ApiModelProperty(value = "被抄送人ID", required = true)
    @NotBlank(message = "被抄送人ID不能为空")
    private String actorId;

    @ApiModelProperty(value = "抄送状态(1:已读；0：未读)")
    private Integer state;


}
