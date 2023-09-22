package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.UserRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * r_用户角色关系
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Data
@ApiModel(value = "UserRoleVO对象", description = "r_用户角色关系VO")
public class UserRoleVO extends UserRole {
}
