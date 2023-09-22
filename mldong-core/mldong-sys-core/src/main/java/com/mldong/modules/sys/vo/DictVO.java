package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.Dict;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Data
@ApiModel(value = "DictVO对象", description = "字典VO")
public class DictVO extends Dict {
}
