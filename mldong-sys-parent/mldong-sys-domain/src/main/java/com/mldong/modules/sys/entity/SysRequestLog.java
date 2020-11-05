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
// START###################
// ###################END
/**
 * <p>实体类</p>
 * <p>Table: sys_request_log - 请求日志</p>
 * @since 2020-11-05 10:15:38
 */
@Table(name="sys_request_log")
@ApiModel(description="请求日志")
public class SysRequestLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "请求唯一标识")
    private String trackId;
    @ApiModelProperty(value = "请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)")
    private RequestTypeEnum requestType;
    @ApiModelProperty(value = "请求路径")
    private String uri;
    @ApiModelProperty(value = "请求url参数")
    private String queryString;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "操作说明")
    private String description;
    @ApiModelProperty(value = "客户端ip")
    private String ip;
    @ApiModelProperty(value = "请求体")
    private String body;
    @ApiModelProperty(value = "请求token")
    private String token;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "返回参数")
    private String returnData;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;
// START###################
// ###################END
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
     * 获取请求唯一标识
     *
     */
    public String getTrackId(){
        return this.trackId;
    }
	 /**
     * 设置请求唯一标识
     *
     * @param trackId
     */
    public void setTrackId(String trackId){
        this.trackId = trackId;
    }
    /**
     * 获取请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)
     *
     */
    public RequestTypeEnum getRequestType(){
        return this.requestType;
    }
	 /**
     * 设置请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)
     *
     * @param requestType
     */
    public void setRequestType(RequestTypeEnum requestType){
        this.requestType = requestType;
    }
    /**
     * 获取请求路径
     *
     */
    public String getUri(){
        return this.uri;
    }
	 /**
     * 设置请求路径
     *
     * @param uri
     */
    public void setUri(String uri){
        this.uri = uri;
    }
    /**
     * 获取请求url参数
     *
     */
    public String getQueryString(){
        return this.queryString;
    }
	 /**
     * 设置请求url参数
     *
     * @param queryString
     */
    public void setQueryString(String queryString){
        this.queryString = queryString;
    }
    /**
     * 获取请求方式
     *
     */
    public String getMethod(){
        return this.method;
    }
	 /**
     * 设置请求方式
     *
     * @param method
     */
    public void setMethod(String method){
        this.method = method;
    }
    /**
     * 获取操作说明
     *
     */
    public String getDescription(){
        return this.description;
    }
	 /**
     * 设置操作说明
     *
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
    /**
     * 获取客户端ip
     *
     */
    public String getIp(){
        return this.ip;
    }
	 /**
     * 设置客户端ip
     *
     * @param ip
     */
    public void setIp(String ip){
        this.ip = ip;
    }
    /**
     * 获取请求体
     *
     */
    public String getBody(){
        return this.body;
    }
	 /**
     * 设置请求体
     *
     * @param body
     */
    public void setBody(String body){
        this.body = body;
    }
    /**
     * 获取请求token
     *
     */
    public String getToken(){
        return this.token;
    }
	 /**
     * 设置请求token
     *
     * @param token
     */
    public void setToken(String token){
        this.token = token;
    }
    /**
     * 获取用户id
     *
     */
    public Long getUserId(){
        return this.userId;
    }
	 /**
     * 设置用户id
     *
     * @param userId
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /**
     * 获取用户名
     *
     */
    public String getUserName(){
        return this.userName;
    }
	 /**
     * 设置用户名
     *
     * @param userName
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
    /**
     * 获取返回参数
     *
     */
    public String getReturnData(){
        return this.returnData;
    }
	 /**
     * 设置返回参数
     *
     * @param returnData
     */
    public void setReturnData(String returnData){
        this.returnData = returnData;
    }
    /**
     * 获取开始时间
     *
     */
    public Date getStartTime(){
        return this.startTime;
    }
	 /**
     * 设置开始时间
     *
     * @param startTime
     */
    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    /**
     * 获取结束时间
     *
     */
    public Date getEndTime(){
        return this.endTime;
    }
	 /**
     * 设置结束时间
     *
     * @param endTime
     */
    public void setEndTime(Date endTime){
        this.endTime = endTime;
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
// START###################
// ###################END
    @DictEnum(key="sys_request_log_request_type",name="请求类型")
    public enum RequestTypeEnum implements CodedEnum {
		/**
		 * 添加
		 */
		SAVE(10, "添加"),
		/**
		 * 修改
		 */
		UPDATE(20, "修改"),
		/**
		 * 删除
		 */
		REMOVE(30, "删除"),
		/**
		 * 导入
		 */
		IMPORT(40, "导入"),
		/**
		 * 导出
		 */
		EXPORT(50, "导出"),
		/**
		 * 其他
		 */
		OTHER(99, "其他");
		// START###################
		// ###################END
		private int value;
		private String name;
		@JsonCreator
	    public static RequestTypeEnum forValue(int value) {
	        return CodedEnum.codeOf(RequestTypeEnum.class, value).get();

	    }
		RequestTypeEnum(int value, String name) {
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