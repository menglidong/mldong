package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Data
@ApiModel(value = "UserVO对象", description = "用户VO")
public class UserVO extends User {
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "岗位名称")
    private String postName;
    @ApiModelProperty(value = "角色ID集合")
    private String roleIds;
}
