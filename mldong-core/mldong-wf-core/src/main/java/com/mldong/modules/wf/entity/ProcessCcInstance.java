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
 * 流程实例抄送
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
@Getter
@Setter
@TableName("wf_process_cc_instance")
@ApiModel(value = "ProcessCcInstance对象", description = "流程实例抄送")
public class ProcessCcInstance implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("流程实例ID")
    private Long processInstanceId;

    @ApiModelProperty("被抄送人ID")
    private String actorId;

    @ApiModelProperty("抄送状态(1:已读；0：未读)")
    private Integer state;

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
