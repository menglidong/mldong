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
 * 流程实例
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_instance")
@ApiModel(value = "ProcessInstanceParam对象", description = "流程实例")
public class ProcessInstanceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "父流程ID，子流程实例才有值")
    private Long parentId;

    @ApiModelProperty(value = "流程定义ID")
    private Long processDefineId;

    @ApiModelProperty(value = "流程实例状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)")
    private Integer state;

    @ApiModelProperty(value = "父流程依赖的节点名称")
    private String parentNodeName;

    @ApiModelProperty(value = "业务编号")
    private String businessNo;

    @ApiModelProperty(value = "流程发起人")
    private String operator;

    @ApiModelProperty(value = "期望完成时间")
    private Date expireTime;

    @ApiModelProperty(value = "附属变量json存储")
    private String variable;


}
