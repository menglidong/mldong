package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.RoleMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * r_角色菜单关系
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Data
@ApiModel(value = "RoleMenuVO对象", description = "r_角色菜单关系VO")
public class RoleMenuVO extends RoleMenu {
}
