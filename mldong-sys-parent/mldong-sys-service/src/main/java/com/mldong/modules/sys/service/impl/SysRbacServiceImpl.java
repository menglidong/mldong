package com.mldong.modules.sys.service.impl;

import java.util.*;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


import tk.mybatis.mapper.entity.Condition;


import com.github.pagehelper.Page;
import com.mldong.common.access.AccessInitProcessor;
import com.mldong.common.access.model.SysAccessModel;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.IdAndIdsParam;
import com.mldong.common.config.GlobalProperties;
import com.mldong.common.interceptor.AuthInterceptorService;
import com.mldong.common.token.TokenStrategy;
import com.mldong.modules.sys.dao.SysUserDao;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.entity.SysRoleAccess;
import com.mldong.modules.sys.entity.SysRoleMenu;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.entity.SysUserRole;
import com.mldong.modules.sys.mapper.SysMenuMapper;
import com.mldong.modules.sys.mapper.SysRoleAccessMapper;
import com.mldong.modules.sys.mapper.SysRoleMenuMapper;
import com.mldong.modules.sys.mapper.SysUserRoleMapper;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.vo.SysAccessTreeVo;
import com.mldong.modules.sys.vo.SysMenuTreeVo;

@Service
public class SysRbacServiceImpl implements SysRbacService, AuthInterceptorService{
	@Autowired
	private AccessInitProcessor accessInitProcessor;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleAccessMapper sysRoleAccessMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private TokenStrategy tokenStrategy;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Override
	public SysAccessTreeVo listAccessTree(Long userId, Long roleId) {
		SysAccessTreeVo vo = new SysAccessTreeVo();
		vo.setDefaultCheckedKeys(new ArrayList<>());
		vo.setDefaultExpandedKeys(new ArrayList<>());
		SysRoleAccess q = new SysRoleAccess();
		q.setRoleId(roleId);
		List<SysRoleAccess> roleAccessList = sysRoleAccessMapper.select(q);
		roleAccessList.forEach(item->{
			vo.getDefaultCheckedKeys().add(item.getAccess());
		});
		List<SysAccessModel> allAccess = accessInitProcessor.getAccessList();
		vo.setRows(allAccess);
		// 默认展开第一行
		allAccess.forEach(item->{
			vo.getDefaultExpandedKeys().add(item.getAccess());
		});
		return vo;
	}
	@Override
	public SysMenuTreeVo listMenuByRoleId(Long userId, Long roleId) {
		SysMenuTreeVo vo = new SysMenuTreeVo();
		vo.setDefaultCheckedKeys(new ArrayList<>());
		vo.setDefaultExpandedKeys(new ArrayList<>());
		SysRoleMenu q = new SysRoleMenu();
		q.setRoleId(roleId);
		List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.select(q);
		roleMenuList.forEach(item->{
			vo.getDefaultCheckedKeys().add(item.getMenuId().toString());
		});
		List<SysMenu> allMenu = sysMenuMapper.selectAll();
		vo.setRows(allMenu);
		// 默认展开第一行
		allMenu.stream().filter(item->{
			return new Long(0L).equals(item.getParentId());
		}).forEach(item->{
			vo.getDefaultExpandedKeys().add(item.getId().toString());
		});
		return vo;
	}
	@Override
	public CommonPage<SysUser> listUserByRoleId(SysUserWithRoleIdPageParam param) {
		Page<SysUser> page = param.buildPage();
		sysUserDao.selectUserByRoleId(param);
		return CommonPage.toPage(page);
	}
	@Caching(evict={
			@CacheEvict(value="access_user_id")
	})
	@Override
	public int saveUserRole(IdAndIdsParam param) {
		Date now = new Date();
		Long roleId = Long.parseLong(param.getId());
		param.getIds().forEach(id->{
			Long userId = Long.parseLong(id);
			SysUserRole q = new SysUserRole();
			q.setUserId(userId);
			q.setRoleId(roleId);
			int count = sysUserRoleMapper.selectCount(q);
			if(count == 0) {
				q.setCreateTime(now);
				q.setUpdateTime(now);
				sysUserRoleMapper.insertSelective(q);
			}
		});
		return 1;
	}
	@Caching(evict={
			@CacheEvict(value="access_user_id")
	})
	@Override
	public int deleteUserRole(IdAndIdsParam param) {
		Condition condition = new Condition(SysUserRole.class);
    	condition.createCriteria().andEqualTo("roleId", param.getId())
    	.andIn("userId", param.getIds());
		return sysUserRoleMapper.deleteByCondition(condition);
	}
	@Override
	public CommonPage<SysUser> listUserNoInRole(SysUserWithRoleIdPageParam param) {
		Page<SysUser> page = param.buildPage();
		sysUserDao.selectUserNoInRole(param);
		return CommonPage.toPage(page);
	}
	@Caching(evict={
			@CacheEvict(value="access_user_id")
	})
	@Override
	public int saveRoleAccess(IdAndIdsParam param) {
		Date now = new Date();
		Long roleId = Long.parseLong(param.getId());
		param.getIds().forEach(access->{
			SysRoleAccess q = new SysRoleAccess();
			q.setAccess(access);
			q.setRoleId(roleId);
			int count = sysRoleAccessMapper.selectCount(q);
			if(count == 0) {
				q.setCreateTime(now);
				q.setUpdateTime(now);
				sysRoleAccessMapper.insertSelective(q);
			}
		});
		
		Condition delConditin = new Condition(SysRoleAccess.class);
		if(param.getIds().isEmpty()) {
			delConditin.createCriteria().andEqualTo("roleId", roleId);
			// 删除不在已选中列表中资源
			sysRoleAccessMapper.deleteByCondition(delConditin);
		} else {
			delConditin.createCriteria().andEqualTo("roleId", roleId)
			.andNotIn("access", param.getIds());
			// 删除不在已选中列表中资源
			sysRoleAccessMapper.deleteByCondition(delConditin);
		}
		return 1;
	}
	@Caching(evict={
			@CacheEvict(value="access_user_id")
	})
	@Override
	public int deleteRoleAccess(IdAndIdsParam param) {
		Condition condition = new Condition(SysRoleAccess.class);
    	condition.createCriteria().andEqualTo("roleId", param.getId())
    	.andIn("access", param.getIds());
		return sysRoleAccessMapper.deleteByCondition(condition);
	}
	@Caching(evict={
			@CacheEvict(value="menu_user_id")
	})
	@Override
	public int saveRoleMenu(IdAndIdsParam param) {
		Date now = new Date();
		Long roleId = Long.parseLong(param.getId());
		param.getIds().forEach(id->{
			Long menuId = Long.parseLong(id);
			SysRoleMenu q = new SysRoleMenu();
			q.setMenuId(menuId);
			q.setRoleId(roleId);
			int count = sysRoleMenuMapper.selectCount(q);
			if(count == 0) {
				q.setCreateTime(now);
				q.setUpdateTime(now);
				sysRoleMenuMapper.insertSelective(q);
			}
		});
		Condition delConditin = new Condition(SysRoleMenu.class);
		if(param.getIds().isEmpty()) {
			delConditin.createCriteria().andEqualTo("roleId", roleId);
			// 删除不在已选中列表中资源
			sysRoleMenuMapper.deleteByCondition(delConditin);
		} else {
			delConditin.createCriteria().andEqualTo("roleId", roleId)
			.andNotIn("menuId", param.getIds());
			// 删除不在已选中列表中资源
			sysRoleMenuMapper.deleteByCondition(delConditin);
		}
		return 1;
	}
	@Caching(evict={
			@CacheEvict(value="menu_user_id")
	})
	@Override
	public int deleteRoleMenu(IdAndIdsParam param) {
		Condition condition = new Condition(SysRoleMenu.class);
    	condition.createCriteria().andEqualTo("roleId", param.getId())
    	.andIn("menuId", param.getIds());
		return sysRoleMenuMapper.deleteByCondition(condition);
	}
	@Override
	public boolean hasAccess(Long userId, String access) {
		if(userId.equals(globalProperties.getSuperAdminId())) {
			return true;
		}
 		return loadUserAccessList(userId).contains(access);
	}
	@Override
	@Cacheable(value = "access_user_id",key="#userId")
	public List<String> loadUserAccessList(Long userId) {
		if(userId.equals(globalProperties.getSuperAdminId())) {
			return Arrays.asList("admin");
		}
		return sysUserDao.selectUserAccess(userId).stream()
				.map(item->{
					return item.getAccess();
				}).collect(Collectors.toList());
	}
	@Override
	@Cacheable(value = "menu_user_id",key="#userId")
	public List<SysMenu> loadUserMenuList(Long userId) {
		if(userId.equals(globalProperties.getSuperAdminId())) {
			return sysMenuMapper.selectAll();
		}
		List<SysMenu> userMenuList =  sysUserDao.selectUserMenu(userId);
		List<SysMenu> noParentList = userMenuList.stream().filter(item->{
			return userMenuList.stream().filter(itemm-> {
				return itemm.getId().equals(item.getParentId());
			}).count() == 0 && (item.getParentId() !=null || !new Long(0).equals(item.getParentId()));
		}).collect(Collectors.toList());
		tranfer(userMenuList, noParentList,1);
		return userMenuList;
	}
	/**
	 * 递归转换，追加父级菜单
	 * @param userMenuList 当前用户菜单
	 * @param noParentList 没有父节点的菜单
	 * @param level 深度，支支持5级
	 */
	private void tranfer(List<SysMenu> userMenuList, List<SysMenu> noParentList, int level){
		if(noParentList.isEmpty() || level > 5) {
			return;
		}
		Condition condition = new Condition(SysMenu.class);
		condition.createCriteria().andIn("id", noParentList.stream().map(item->{
			return item.getParentId();
		}).collect(Collectors.toList()));
		List<SysMenu> newList = sysMenuMapper.selectByCondition(condition);
		if(newList.isEmpty()) {
			return;
		}
		userMenuList.addAll(newList);
		List<SysMenu> newNoParentList = userMenuList.stream().filter(item->{
			return userMenuList.stream().filter(itemm-> {
				return itemm.getId().equals(item.getParentId());
			}).count() == 0 && (item.getParentId() !=null || !new Long(0).equals(item.getParentId()));
		}).collect(Collectors.toList());
		tranfer(userMenuList, newNoParentList,level+1);
	}
	@Override
	public boolean verifyToken(String token) {
		return tokenStrategy.verifyToken(token);
	}
	@Override
	public boolean hasAuth(String token, String access) {
		Long userId = tokenStrategy.getUserId(token);
		return hasAccess(userId, access);
	}
	@Override
	public Long getUserId(String token) {
		return tokenStrategy.getUserId(token);
	}

	@Override
	public String getUserName(String token) {
		return tokenStrategy.getUserName(token);
	}

	@Override
	public Map<String,Object> getExt(String token) {
		return tokenStrategy.getExt(token);
	}
}
