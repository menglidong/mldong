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
 * <p>Table: sys_role - 角色</p>
 * @since 2020-12-22 11:35:02
 */
@Table(name="sys_role")
@ApiModel(description="角色")
public class SysRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "角色名称", position = 10)
    private String name;
    @ApiModelProperty(value = "角色标识(唯一)", position = 15)
    private String roleKey;
    @ApiModelProperty(value = "角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)", position = 20)
    private RoleTypeEnum roleType;
    @ApiModelProperty(value = "数据范围(10->所有数据权限|ALL,20->部门数据权限|DEPT,30->部门及以下数据权限|DEPT_CHILD,40->仅本人数据权限|MYSELF)", position = 25)
    private DataScopeEnum dataScope;
    @ApiModelProperty(value = "是否启用(1->禁用|NO,2->启用|YES)", position = 30)
    private YesNoEnum isEnabled;
    @ApiModelProperty(value = "备注", position = 35)
    private String remark;
    @ApiModelProperty(value = "创建时间", position = 40)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 45)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 50)
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
     * 获取角色名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置角色名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取角色标识(唯一)
     *
     */
    public String getRoleKey(){
        return this.roleKey;
    }
	 /**
     * 设置角色标识(唯一)
     *
     * @param roleKey
     */
    public void setRoleKey(String roleKey){
        this.roleKey = roleKey;
    }
    /**
     * 获取角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)
     *
     */
    public RoleTypeEnum getRoleType(){
        return this.roleType;
    }
	 /**
     * 设置角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)
     *
     * @param roleType
     */
    public void setRoleType(RoleTypeEnum roleType){
        this.roleType = roleType;
    }
    /**
     * 获取数据范围(10->所有数据权限|ALL,20->部门数据权限|DEPT,30->部门及以下数据权限|DEPT_CHILD,40->仅本人数据权限|MYSELF)
     *
     */
    public DataScopeEnum getDataScope(){
        return this.dataScope;
    }
	 /**
     * 设置数据范围(10->所有数据权限|ALL,20->部门数据权限|DEPT,30->部门及以下数据权限|DEPT_CHILD,40->仅本人数据权限|MYSELF)
     *
     * @param dataScope
     */
    public void setDataScope(DataScopeEnum dataScope){
        this.dataScope = dataScope;
    }
    /**
     * 获取是否启用(1->禁用|NO,2->启用|YES)
     *
     */
    public YesNoEnum getIsEnabled(){
        return this.isEnabled;
    }
	 /**
     * 设置是否启用(1->禁用|NO,2->启用|YES)
     *
     * @param isEnabled
     */
    public void setIsEnabled(YesNoEnum isEnabled){
        this.isEnabled = isEnabled;
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

    @DictEnum(key="sys_role_role_type",name="角色类型")
    public enum RoleTypeEnum implements CodedEnum {
		/**
		 * 管理员
		 */
		ADMIN(10, "管理员"),
		/**
		 * 流程审核员
		 */
		WORKFLOW(20, "流程审核员");

		private int value;
		private String name;
		@JsonCreator
	    public static RoleTypeEnum forValue(int value) {
	        return CodedEnum.codeOf(RoleTypeEnum.class, value).get();

	    }
		RoleTypeEnum(int value, String name) {
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
    @DictEnum(key="sys_role_data_scope",name="数据范围")
    public enum DataScopeEnum implements CodedEnum {
		/**
		 * 所有数据权限
		 */
		ALL(10, "所有数据权限"),
		/**
		 * 部门数据权限
		 */
		DEPT(20, "部门数据权限"),
		/**
		 * 部门及以下数据权限
		 */
		DEPT_CHILD(30, "部门及以下数据权限"),
		/**
		 * 仅本人数据权限
		 */
		MYSELF(40, "仅本人数据权限");

		private int value;
		private String name;
		@JsonCreator
	    public static DataScopeEnum forValue(int value) {
	        return CodedEnum.codeOf(DataScopeEnum.class, value).get();

	    }
		DataScopeEnum(int value, String name) {
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