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
 * 流程实例
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_instance")
@ApiModel(value = "ProcessInstance对象", description = "流程实例")
public class ProcessInstance implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("父流程ID，子流程实例才有值")
    private Long parentId;

    @ApiModelProperty("流程定义ID")
    private Long processDefineId;

    @ApiModelProperty("流程实例状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)")
    private Integer state;

    @ApiModelProperty("父流程依赖的节点名称")
    private String parentNodeName;

    @ApiModelProperty("业务编号")
    private String businessNo;

    @ApiModelProperty("流程发起人")
    private String operator;

    @ApiModelProperty("期望完成时间")
    private Date expireTime;

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
