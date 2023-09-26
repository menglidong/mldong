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
 * 流程任务
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_task")
@ApiModel(value = "ProcessTaskParam对象", description = "流程任务")
public class ProcessTaskParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "流程实例ID", required = true)
    @NotNull(message = "流程实例ID不能为空")
    private Long processInstanceId;

    @ApiModelProperty(value = "任务名称编码", required = true)
    @NotBlank(message = "任务名称编码不能为空")
    private String taskName;

    @ApiModelProperty(value = "任务显示名称", required = true)
    @NotBlank(message = "任务显示名称不能为空")
    private String displayName;

    @ApiModelProperty(value = "任务类型(0：主办任务；1：协办任务)")
    private Integer taskType;

    @ApiModelProperty(value = "参与类型(0：普通参与；1：会签参与)")
    private Integer performType;

    @ApiModelProperty(value = "任务状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)")
    private Integer taskState;

    @ApiModelProperty(value = "任务处理人")
    private String operator;

    @ApiModelProperty(value = "任务完成时间")
    private Date finishTime;

    @ApiModelProperty(value = "任务期待完成时间")
    private Date expireTime;

    @ApiModelProperty(value = "任务处理表单KEY")
    private String formKey;

    @ApiModelProperty(value = "父任务ID")
    private Long taskParentId;

    @ApiModelProperty(value = "附属变量json存储")
    private String variable;


}
