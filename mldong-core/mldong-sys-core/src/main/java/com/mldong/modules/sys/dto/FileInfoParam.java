package com.mldong.modules.sys.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.mldong.validation.Groups;
/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author mldong
 * @since 2024-02-23
 */
@Getter
@Setter
@TableName("sys_file_info")
@ApiModel(value = "FileInfoParam对象", description = "文件信息")
public class FileInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件信息ID", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "文件信息ID不能为空", groups = {Groups.Update.class})
    private Long id;

    @ApiModelProperty(value = "文件访问地址", required = true)
    @NotBlank(message = "文件访问地址不能为空")
    private String url;

    @ApiModelProperty(value = "文件大小，单位字节")
    private Long size;

    @ApiModelProperty(value = "文件大小，有单位")
    private String sizeInfo;

    @ApiModelProperty(value = "文件名称")
    private String filename;

    @ApiModelProperty(value = "原始文件名")
    private String originalFilename;

    @ApiModelProperty(value = "基础存储路径")
    private String basePath;

    @ApiModelProperty(value = "存储路径")
    private String path;

    @ApiModelProperty(value = "文件扩展名")
    private String ext;

    @ApiModelProperty(value = "MIME类型")
    private String contentType;

    @ApiModelProperty(value = "存储平台")
    private String platform;

    @ApiModelProperty(value = "缩略图访问路径")
    private String thUrl;

    @ApiModelProperty(value = "缩略图大小，单位字节")
    private String thFilename;

    @ApiModelProperty(value = "缩略图大小，单位字节")
    private Long thSize;

    @ApiModelProperty(value = "缩略图大小，有单位")
    private String thSizeInfo;

    @ApiModelProperty(value = "缩略图MIME类型")
    private String thContentType;

    @ApiModelProperty(value = "文件所属对象id")
    private String objectId;

    @ApiModelProperty(value = "文件所属对象类型，例如用户头像，评价图片")
    private String objectType;

    @ApiModelProperty(value = "附加属性")
    private String attr;


}
