package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.VisLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 系统访问日志表
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
@Data
@ApiModel(value = "VisLogVO对象", description = "系统访问日志表VO")
public class VisLogVO extends VisLog {
}
