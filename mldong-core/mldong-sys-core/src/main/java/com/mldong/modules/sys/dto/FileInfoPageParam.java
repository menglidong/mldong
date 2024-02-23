package com.mldong.modules.sys.dto;

import com.mldong.base.PageParam;
import com.mldong.modules.sys.entity.FileInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class FileInfoPageParam extends PageParam<FileInfo> {
}