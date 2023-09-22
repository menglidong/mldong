package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
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
}
