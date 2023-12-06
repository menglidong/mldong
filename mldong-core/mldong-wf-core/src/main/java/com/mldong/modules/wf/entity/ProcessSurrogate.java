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
 * 流程委托代理
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
@Getter
@Setter
@TableName("wf_process_surrogate")
@ApiModel(value = "ProcessSurrogate对象", description = "流程委托代理")
public class ProcessSurrogate implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("流程名称")
    private String processName;

    @ApiModelProperty("授权人")
    private String operator;

    @ApiModelProperty("代理人")
    private String surrogate;

    @ApiModelProperty("授权开始时间")
    private Date startTime;

    @ApiModelProperty("授权结束时间")
    private Date endTime;

    @ApiModelProperty("是否启用")
    private Integer enabled;

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
