package com.mldong.modules.sys.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.mldong.modules.sys.entity.SysMenu;

public class SysUserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4233669943292250548L;
	@ApiModelProperty(value="用户id")
	private Long userId;
	@ApiModelProperty(value="用户名")
	private String userName;
	@ApiModelProperty(value="姓名")
	private String realName;
	@ApiModelProperty(value="头像")
	private String avatar;
	@ApiModelProperty(value="权限标识集合")
	private List<String> accessList;
	@ApiModelProperty(value="路由菜单集合")
	private List<SysMenu> menuList;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<String> getAccessList() {
		return accessList;
	}
	public void setAccessList(List<String> accessList) {
		this.accessList = accessList;
	}
	public List<SysMenu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}
	
}
