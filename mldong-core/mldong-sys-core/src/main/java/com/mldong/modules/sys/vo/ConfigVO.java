package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.Config;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Data
@ApiModel(value = "ConfigVO对象", description = "配置VO")
public class ConfigVO extends Config {
}
