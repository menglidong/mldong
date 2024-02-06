package com.mldong.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 系统访问日志表
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
@Getter
@Setter
@TableName("sys_vis_log")
@ApiModel(value = "VisLog对象", description = "系统访问日志表")
public class VisLog implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("是否执行成功（Y-是，N-否）")
    private String success;

    @ApiModelProperty("具体消息")
    private String message;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("地址")
    private String location;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("操作类型（字典 1登入 2登出）")
    private Integer visType;

    @ApiModelProperty("访问时间")
    private Date visTime;

    @ApiModelProperty("访问账号")
    private String account;

    @ApiModelProperty("签名数据（除ID外）")
    private String signValue;

}
