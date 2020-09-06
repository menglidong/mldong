package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import java.util.Date;
import com.mldong.modules.sys.entity.SysRequestLog;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_request_log - 请求日志</p>
 * @since 2020-09-06 07:33:38
 */
@ApiModel(description="请求日志")
public class SysRequestLogParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "请求唯一标识",required=false)
    private String trackId;
    @ApiModelProperty(value = "请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)",required=false)
    private SysRequestLog.RequestTypeEnum requestType;
    @ApiModelProperty(value = "请求路径",required=false)
    private String uri;
    @ApiModelProperty(value = "请求url参数",required=false)
    private String queryString;
    @ApiModelProperty(value = "请求方式",required=false)
    private String method;
    @ApiModelProperty(value = "操作说明",required=false)
    private String description;
    @ApiModelProperty(value = "客户端ip",required=false)
    private String ip;
    @ApiModelProperty(value = "请求体",required=false)
    private String body;
    @ApiModelProperty(value = "请求token",required=false)
    private String token;
    @ApiModelProperty(value = "用户id",required=false)
    private Long userId;
    @ApiModelProperty(value = "用户名",required=false)
    private String userName;
    @ApiModelProperty(value = "返回参数",required=false)
    private String returnData;
    @ApiModelProperty(value = "开始时间",required=false)
    private Date startTime;
    @ApiModelProperty(value = "结束时间",required=false)
    private Date endTime;
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
    public SysRequestLog.RequestTypeEnum getRequestType(){
        return this.requestType;
    }
	 /**
     * 设置请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)
     *
     * @param requestType
     */
    public void setRequestType(SysRequestLog.RequestTypeEnum requestType){
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
}