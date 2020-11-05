package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import tk.mybatis.mapper.annotation.LogicDelete;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>实体类</p>
 * <p>Table: sys_upload_record - 上传记录</p>
 * @since 2020-11-05 10:28:09
 */
@Table(name="sys_upload_record")
@ApiModel(description="上传记录")
public class SysUploadRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "业务id")
    private String bizId;
    @ApiModelProperty(value = "文件保存的资源路径")
    private String url;
    @ApiModelProperty(value = "上传的原始文件名")
    private String fileName;
    @ApiModelProperty(value = "资源大小，单位为字节")
    private Long fileSize;
    @ApiModelProperty(value = "资源类型")
    private String mimeType;
    @ApiModelProperty(value = "上传资源的后缀名")
    private String fileExt;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;

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
    public String getBizId(){
        return this.bizId;
    }
	 /**
     * 设置业务id
     *
     * @param bizId
     */
    public void setBizId(String bizId){
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
    /**
     * 获取创建时间
     *
     */
    public Date getCreateTime(){
        return this.createTime;
    }
	 /**
     * 设置创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     * 获取更新时间
     *
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }
	 /**
     * 设置更新时间
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 获取是否删除(1->未删除|NO,2->已删除|YES)
     *
     */
    public YesNoEnum getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除(1->未删除|NO,2->已删除|YES)
     *
     * @param isDeleted
     */
    public void setIsDeleted(YesNoEnum isDeleted){
        this.isDeleted = isDeleted;
    }

}