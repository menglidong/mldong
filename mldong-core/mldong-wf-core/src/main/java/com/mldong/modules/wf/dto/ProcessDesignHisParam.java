package com.mldong.modules.wf.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mldong.validation.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * <p>
 * 流程设计历史
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
@Getter
@Setter
@TableName("wf_process_design_his")
@ApiModel(value = "ProcessDesignHisParam对象", description = "流程设计历史")
public class ProcessDesignHisParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "流程设计ID", required = true)
    @NotNull(message = "流程设计ID不能为空")
    private Long processDesignId;

    @ApiModelProperty(value = "流程模型定义")
    private byte[] content;


}
