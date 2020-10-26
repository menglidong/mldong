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
 * <p>Table: sys_upload_config - 上传配置</p>
 * @since 2020-10-26 09:58:37
 */
@Table(name="sys_upload_config")
@ApiModel(description="上传配置")
public class SysUploadConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "业务名称")
    private String bizName;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "限定上传文件大小最小值，单位`byte`。（0为不限制）")
    private Long fileSizeMin;
    @ApiModelProperty(value = "限定上传文件大小最大值，单位`byte`。（0为不限制）")
    private Long fileSizeMax;
    @ApiModelProperty(value = "限定用户上传后辍(多个逗号分割)")
    private String fileExt;
    @ApiModelProperty(value = "访问地址前辍")
    private String baseUrl;
    @ApiModelProperty(value = "回调地址")
    private String callbackUrl;
    @ApiModelProperty(value = "命名策略")
    private String namingStrategy;
    @ApiModelProperty(value = "是否记录(1->不记录|NO,2->记录|YES)")
    private YesNoEnum isRecord;
    @ApiModelProperty(value = "备注")
    private String remark;
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
     * 获取业务名称
     *
     */
    public String getBizName(){
        return this.bizName;
    }
	 /**
     * 设置业务名称
     *
     * @param bizName
     */
    public void setBizName(String bizName){
        this.bizName = bizName;
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
     * 获取限定上传文件大小最小值，单位`byte`。（0为不限制）
     *
     */
    public Long getFileSizeMin(){
        return this.fileSizeMin;
    }
	 /**
     * 设置限定上传文件大小最小值，单位`byte`。（0为不限制）
     *
     * @param fileSizeMin
     */
    public void setFileSizeMin(Long fileSizeMin){
        this.fileSizeMin = fileSizeMin;
    }
    /**
     * 获取限定上传文件大小最大值，单位`byte`。（0为不限制）
     *
     */
    public Long getFileSizeMax(){
        return this.fileSizeMax;
    }
	 /**
     * 设置限定上传文件大小最大值，单位`byte`。（0为不限制）
     *
     * @param fileSizeMax
     */
    public void setFileSizeMax(Long fileSizeMax){
        this.fileSizeMax = fileSizeMax;
    }
    /**
     * 获取限定用户上传后辍(多个逗号分割)
     *
     */
    public String getFileExt(){
        return this.fileExt;
    }
	 /**
     * 设置限定用户上传后辍(多个逗号分割)
     *
     * @param fileExt
     */
    public void setFileExt(String fileExt){
        this.fileExt = fileExt;
    }
    /**
     * 获取访问地址前辍
     *
     */
    public String getBaseUrl(){
        return this.baseUrl;
    }
	 /**
     * 设置访问地址前辍
     *
     * @param baseUrl
     */
    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    /**
     * 获取回调地址
     *
     */
    public String getCallbackUrl(){
        return this.callbackUrl;
    }
	 /**
     * 设置回调地址
     *
     * @param callbackUrl
     */
    public void setCallbackUrl(String callbackUrl){
        this.callbackUrl = callbackUrl;
    }
    /**
     * 获取命名策略
     *
     */
    public String getNamingStrategy(){
        return this.namingStrategy;
    }
	 /**
     * 设置命名策略
     *
     * @param namingStrategy
     */
    public void setNamingStrategy(String namingStrategy){
        this.namingStrategy = namingStrategy;
    }
    /**
     * 获取是否记录(1->不记录|NO,2->记录|YES)
     *
     */
    public YesNoEnum getIsRecord(){
        return this.isRecord;
    }
	 /**
     * 设置是否记录(1->不记录|NO,2->记录|YES)
     *
     * @param isRecord
     */
    public void setIsRecord(YesNoEnum isRecord){
        this.isRecord = isRecord;
    }
    /**
     * 获取备注
     *
     */
    public String getRemark(){
        return this.remark;
    }
	 /**
     * 设置备注
     *
     * @param remark
     */
    public void setRemark(String remark){
        this.remark = remark;
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