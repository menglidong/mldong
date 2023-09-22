package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.Dept;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Data
@ApiModel(value = "DeptVO对象", description = "部门VO")
public class DeptVO extends Dept {
}
