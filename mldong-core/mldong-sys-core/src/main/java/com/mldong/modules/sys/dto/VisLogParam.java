package com.mldong.modules.sys.dto;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 系统访问日志表
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
@Getter
@Setter
@TableName("sys_vis_log")
@ApiModel(value = "VisLogParam对象", description = "系统访问日志表")
public class VisLogParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否执行成功（Y-是，N-否）")
    private String success;

    @ApiModelProperty(value = "具体消息")
    private String message;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "地址")
    private String location;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "操作类型（字典 1登入 2登出）", required = true)
    @NotNull(message = "操作类型（字典 1登入 2登出）不能为空")
    private Integer visType;

    @ApiModelProperty(value = "访问时间")
    private Date visTime;

    @ApiModelProperty(value = "访问账号")
    private String account;

    @ApiModelProperty(value = "签名数据（除ID外）")
    private String signValue;
    
}
