package com.mldong.modules.wf.entity;
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
@ApiModel(value = "ProcessTask对象", description = "流程任务")
public class ProcessTask implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("流程实例ID")
    private Long processInstanceId;

    @ApiModelProperty("任务名称编码")
    private String taskName;

    @ApiModelProperty("任务显示名称")
    private String displayName;

    @ApiModelProperty("任务类型(0：主办任务；1：协办任务)")
    private Integer taskType;

    @ApiModelProperty("参与类型(0：普通参与；1：会签参与)")
    private Integer performType;

    @ApiModelProperty("任务状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)")
    private Integer taskState;

    @ApiModelProperty("任务处理人")
    private String operator;

    @ApiModelProperty("任务完成时间")
    private Date finishTime;

    @ApiModelProperty("任务期待完成时间")
    private Date expireTime;

    @ApiModelProperty("任务处理表单KEY")
    private String formKey;

    @ApiModelProperty("父任务ID")
    private Long taskParentId;

    @ApiModelProperty("附属变量json存储")
    private String variable;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建用户")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("更新用户")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

}
