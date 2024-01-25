package com.mldong.modules.biz.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 演示
 * </p>
 *
 * @author mldong
 * @since 2023-12-28
 */
@Getter
@Setter
@TableName("biz_demo")
@ApiModel(value = "Demo对象", description = "演示")
public class Demo implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("多行文本")
    private String inputTextArea;

    @ApiModelProperty("自动完成")
    private String autoComplete;

    @ApiModelProperty("开关")
    @JsonProperty("mSwitch")
    private String mSwitch;

    @ApiModelProperty("分割线")
    private String divider;

    @ApiModelProperty("远程下拉")
    private String apiSelect;

    @ApiModelProperty("字典")
    private String dict;

    @ApiModelProperty("远程树")
    private String apiTreeSelect;

    @ApiModelProperty("远程单选组")
    private String apiRadioGroup;

    @ApiModelProperty("远程多选组")
    private String apiCheckboxGroup;

    @ApiModelProperty("日期")
    private String datePicker;

    @ApiModelProperty("日期区间选择")
    private String rangePicker;

    @ApiModelProperty("远程级联选择")
    private String apiCascader;

    @ApiModelProperty("文件上传")
    private String upload;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("是否删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}
