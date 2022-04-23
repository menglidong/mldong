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
 * <p>Table: sys_user_login_times - 用户登录次数</p>
 * @since 2022-04-23 05:26:04
 */
@Table(name="sys_user_login_times")
@ApiModel(description="用户登录次数")
public class SysUserLoginTimes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "用户id", position = 10)
    private Long userId;
    @ApiModelProperty(value = "登录ip", position = 15)
    private String loginIp;
    @ApiModelProperty(value = "登录次数", position = 20)
    private Integer times;
    @ApiModelProperty(value = "创建时间", position = 25)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 30)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 35)
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
     * 获取登录ip
     *
     */
    public String getLoginIp(){
        return this.loginIp;
    }
	 /**
     * 设置登录ip
     *
     * @param loginIp
     */
    public void setLoginIp(String loginIp){
        this.loginIp = loginIp;
    }
    /**
     * 获取登录次数
     *
     */
    public Integer getTimes(){
        return this.times;
    }
	 /**
     * 设置登录次数
     *
     * @param times
     */
    public void setTimes(Integer times){
        this.times = times;
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