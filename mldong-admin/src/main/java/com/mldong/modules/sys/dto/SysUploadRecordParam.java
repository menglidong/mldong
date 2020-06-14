package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.NotBlank;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_upload_record - 上传记录</p>
 * @since 2020-06-14 10:16:54
 */
@ApiModel(description="上传记录")
public class SysUploadRecordParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotBlank(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "业务类型",required=false)
    private String bizType;
    @ApiModelProperty(value = "业务id",required=false)
    private Long bizId;
    @ApiModelProperty(value = "文件保存的资源路径",required=false)
    private String url;
    @ApiModelProperty(value = "上传的原始文件名",required=false)
    private String fileName;
    @ApiModelProperty(value = "资源大小，单位为字节",required=false)
    private Long fileSize;
    @ApiModelProperty(value = "资源类型",required=false)
    private String mimeType;
    @ApiModelProperty(value = "上传资源的后缀名",required=false)
    private String fileExt;
    /**
     * 获取主键
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置主键
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取业务类型
     *
     */
    public String getBizType(){
        return this.bizType;
    }
	 /**
     * 设置业务类型
     *
     * @param bizType
     */
    public void setBizType(String bizType){
        this.bizType = bizType;
    }
    /**
     * 获取业务id
     *
     */
    public Long getBizId(){
        return this.bizId;
    }
	 /**
     * 设置业务id
     *
     * @param bizId
     */
    public void setBizId(Long bizId){
        this.bizId = bizId;
    }
    /**
     * 获取文件保存的资源路径
     *
     */
    public String getUrl(){
        return this.url;
    }
	 /**
     * 设置文件保存的资源路径
     *
     * @param url
     */
    public void setUrl(String url){
        this.url = url;
    }
    /**
     * 获取上传的原始文件名
     *
     */
    public String getFileName(){
        return this.fileName;
    }
	 /**
     * 设置上传的原始文件名
     *
     * @param fileName
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    /**
     * 获取资源大小，单位为字节
     *
     */
    public Long getFileSize(){
        return this.fileSize;
    }
	 /**
     * 设置资源大小，单位为字节
     *
     * @param fileSize
     */
    public void setFileSize(Long fileSize){
        this.fileSize = fileSize;
    }
    /**
     * 获取资源类型
     *
     */
    public String getMimeType(){
        return this.mimeType;
    }
	 /**
     * 设置资源类型
     *
     * @param mimeType
     */
    public void setMimeType(String mimeType){
        this.mimeType = mimeType;
    }
    /**
     * 获取上传资源的后缀名
     *
     */
    public String getFileExt(){
        return this.fileExt;
    }
	 /**
     * 设置上传资源的后缀名
     *
     * @param fileExt
     */
    public void setFileExt(String fileExt){
        this.fileExt = fileExt;
    }
}