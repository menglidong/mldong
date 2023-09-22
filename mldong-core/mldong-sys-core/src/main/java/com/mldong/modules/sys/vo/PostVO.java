package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 岗位
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Data
@ApiModel(value = "PostVO对象", description = "岗位VO")
public class PostVO extends Post {
}
