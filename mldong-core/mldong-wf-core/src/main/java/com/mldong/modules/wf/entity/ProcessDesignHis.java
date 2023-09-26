package com.mldong.modules.wf.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 流程设计历史
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("wf_process_design_his")
@ApiModel(value = "ProcessDesignHis对象", description = "流程设计历史")
public class ProcessDesignHis implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("流程设计ID")
    private Long processDesignId;

    @ApiModelProperty("流程模型定义")
    private byte[] content;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建用户")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

}
