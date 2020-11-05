package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import tk.mybatis.mapper.annotation.LogicDelete;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
import com.mldong.common.base.CodedEnum;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>实体类</p>
 * <p>Table: sys_notice - </p>
 * @since 2020-11-05 10:28:09
 */
@Table(name="sys_notice")
@ApiModel(description="")
public class SysNotice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="编号")
    private Long id;
    @ApiModelProperty(value = "公告标题")
    private String title;
    @ApiModelProperty(value = "公告类型(10->通知|TZ,20->公告|GG)")
    private TypeEnum type;
    @ApiModelProperty(value = "公告内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;

    /**
     * 获取编号
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置编号
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取公告标题
     *
     */
    public String getTitle(){
        return this.title;
    }
	 /**
     * 设置公告标题
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 获取公告类型(10->通知|TZ,20->公告|GG)
     *
     */
    public TypeEnum getType(){
        return this.type;
    }
	 /**
     * 设置公告类型(10->通知|TZ,20->公告|GG)
     *
     * @param type
     */
    public void setType(TypeEnum type){
        this.type = type;
    }
    /**
     * 获取公告内容
     *
     */
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置公告内容
     *
     * @param content
     */
    public void setContent(String content){
        this.content = content;
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

    @DictEnum(key="sys_notice_type",name="公告类型")
    public enum TypeEnum implements CodedEnum {
		/**
		 * 通知
		 */
		TZ(10, "通知"),
		/**
		 * 公告
		 */
		GG(20, "公告");

		private int value;
		private String name;
		@JsonCreator
	    public static TypeEnum forValue(int value) {
	        return CodedEnum.codeOf(TypeEnum.class, value).get();

	    }
		TypeEnum(int value, String name) {
			this.value = value;
			this.name = name;
		}
		@JsonValue
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	
    }
}