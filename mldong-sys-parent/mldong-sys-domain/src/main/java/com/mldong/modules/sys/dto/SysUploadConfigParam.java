package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mldong.common.base.YesNoEnum;
import com.mldong.common.validator.Groups;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_upload_config - 上传配置</p>
 * @since 2020-06-14 10:55:36
 */
@ApiModel(description="上传配置")
public class SysUploadConfigParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "业务类型",required=true)
    @NotBlank(message="业务类型不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String bizType;
    @ApiModelProperty(value = "限定上传文件大小最小值，单位`byte`。（0为不限制）",required=false)
    private Long fileSizeMin;
    @ApiModelProperty(value = "限定上传文件大小最大值，单位`byte`。（0为不限制）",required=false)
    private Long fileSizeMax;
    @ApiModelProperty(value = "限定用户上传后辍(多个逗号分割)",required=true)
    @NotBlank(message="限定用户上传后辍(多个逗号分割)不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String fileExt;
    @ApiModelProperty(value = "访问地址前辍",required=false)
    private String baseUrl;
    @ApiModelProperty(value = "回调地址",required=false)
    private String callbackUrl;
    @ApiModelProperty(value = "命名策略",required=true)
    @NotBlank(message="命名策略不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String namingStrategy;
    @ApiModelProperty(value = "是否记录(1->不记录|NO,2->记录|YES)",required=true)
    private YesNoEnum isRecord;
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
}