package com.mldong.modules.sys.vo;

import com.mldong.modules.sys.entity.FileInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author mldong
 * @since 2024-02-23
 */
@Data
@ApiModel(value = "FileInfoVO对象", description = "文件信息VO")
public class FileInfoVO extends FileInfo {
}
