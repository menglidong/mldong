package com.mldong.modules.sys.dto;

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
import java.util.List;
/**
 * <p>
 * r_用户角色关系
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Getter
@Setter
@TableName("sys_user_role")
@ApiModel(value = "UserRoleParam对象", description = "r_用户角色关系")
public class UserRoleParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空",groups = {GrantRole.class})
    private Long userId;

    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    @ApiModelProperty(value = "角色ID集合", required = true)
    @NotNull(message = "角色ID不能为空",groups = {GrantRole.class})
    private List<Long> roleIdList;
    public interface GrantRole{

    }
}
