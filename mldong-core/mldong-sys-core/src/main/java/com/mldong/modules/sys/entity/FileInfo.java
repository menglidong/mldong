package com.mldong.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "FileInfo对象", description = "文件信息")
public class FileInfo implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件信息ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("文件访问地址")
    private String url;

    @ApiModelProperty("文件大小，单位字节")
    private Long size;

    @ApiModelProperty("文件大小，有单位")
    private String sizeInfo;

    @ApiModelProperty("文件名称")
    private String filename;

    @ApiModelProperty("原始文件名")
    private String originalFilename;

    @ApiModelProperty("基础存储路径")
    private String basePath;

    @ApiModelProperty("存储路径")
    private String path;

    @ApiModelProperty("文件扩展名")
    private String ext;

    @ApiModelProperty("MIME类型")
    private String contentType;

    @ApiModelProperty("存储平台")
    private String platform;

    @ApiModelProperty("缩略图访问路径")
    private String thUrl;

    @ApiModelProperty("缩略图大小，单位字节")
    private String thFilename;

    @ApiModelProperty("缩略图大小，单位字节")
    private Long thSize;

    @ApiModelProperty("缩略图大小，有单位")
    private String thSizeInfo;

    @ApiModelProperty("缩略图MIME类型")
    private String thContentType;

    @ApiModelProperty("文件所属对象id")
    private String objectId;

    @ApiModelProperty("文件所属对象类型，例如用户头像，评价图片")
    private String objectType;

    @ApiModelProperty("附加属性")
    private String attr;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建用户")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("更新用户")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
