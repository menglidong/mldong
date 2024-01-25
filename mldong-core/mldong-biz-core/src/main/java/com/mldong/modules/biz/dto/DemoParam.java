package com.mldong.modules.biz.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
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
@ApiModel(value = "DemoParam对象", description = "演示")
public class DemoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键id不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "多行文本")
    private String inputTextArea;

    @ApiModelProperty(value = "自动完成")
    private String autoComplete;

    @ApiModelProperty(value = "开关")
    @JsonProperty("mSwitch")
    private String mSwitch;

    @ApiModelProperty(value = "分割线")
    private String divider;

    @ApiModelProperty(value = "远程下拉")
    private String apiSelect;

    @ApiModelProperty(value = "字典")
    private String dict;

    @ApiModelProperty(value = "远程树")
    private String apiTreeSelect;

    @ApiModelProperty(value = "远程单选组")
    private String apiRadioGroup;

    @ApiModelProperty(value = "远程多选组")
    private String apiCheckboxGroup;

    @ApiModelProperty(value = "日期")
    private String datePicker;

    @ApiModelProperty(value = "日期区间选择")
    private String rangePicker;

    @ApiModelProperty(value = "远程级联选择")
    private String apiCascader;

    @ApiModelProperty(value = "文件上传")
    private String upload;


}
