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
 * 流程任务和参与人关系
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_task_actor")
@ApiModel(value = "ProcessTaskActorParam对象", description = "流程任务和参与人关系")
public class ProcessTaskActorParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "流程任务ID", required = true)
    @NotNull(message = "流程任务ID不能为空")
    private Long processTaskId;

    @ApiModelProperty(value = "参与者ID", required = true)
    @NotBlank(message = "参与者ID不能为空")
    private String actorId;


}
