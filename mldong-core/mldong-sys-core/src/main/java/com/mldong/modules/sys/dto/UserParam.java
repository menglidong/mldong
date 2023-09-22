package com.mldong.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mldong.validation.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * <p>
 * 用户
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "UserParam对象", description = "用户")
public class UserParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "用户ID不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "密码加盐")
    private String salt;

    @ApiModelProperty(value = "手机号")
    private String mobilePhone;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "管理员类型<sys_admin_type>")
    private Integer adminType;

    @ApiModelProperty(value = "性别<sys_sex>")
    private Integer sex;

    @ApiModelProperty(value = "是否锁定")
    private Integer isLocked;

    @ApiModelProperty(value = "所属部门")
    private Long deptId;

    @ApiModelProperty(value = "所属岗位")
    private Long postId;

    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "新密码", position = 80, required = false)
    @NotBlank(message = "用户新密码不能为空",groups = {UpdatePwd.class})
    private String newPassword;

    public interface UpdatePwd {}

}
