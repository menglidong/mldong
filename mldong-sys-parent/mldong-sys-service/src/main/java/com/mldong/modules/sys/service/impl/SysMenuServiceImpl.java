package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.mldong.common.validator.ValidatorTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.sys.dto.SysMenuParam;
import com.mldong.modules.sys.dto.SysMenuPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.mapper.SysMenuMapper;
import com.mldong.modules.sys.service.SysMenuService;
/**
 * <p>业务接口实现层</p>
 * <p>菜单</p>
 *
 * @since 2020-06-07 09:45:41
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysMenuParam param) {
		ValidatorTool.checkUnique(sysMenuMapper, SysMenu.class,"routeName", param.getRouteName());
		Date now = new Date();
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(param, sysMenu);
		sysMenu.setCreateTime(now);
		sysMenu.setUpdateTime(now);
		sysMenu.setIsDeleted(YesNoEnum.NO);
		return sysMenuMapper.insertSelective(sysMenu);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysMenuParam param) {
		ValidatorTool.checkUniqueOnUpdate(sysMenuMapper, SysMenu.class, "routeName", param.getRouteName(), param.getId());
		Date now = new Date();
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(param, sysMenu);
		sysMenu.setUpdateTime(now);
		return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysMenu upUser = new SysMenu();
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysMenu.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysMenuMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysMenuMapper.deleteByCondition(condition);
	}

	@Override
	public SysMenu get(Long id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysMenu> list(SysMenuPageParam param) {
		Page<SysMenu> page =param.buildPage(true);
		page.setOrderBy("sort asc");
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysMenu sysMenu = new SysMenu();
			sysMenuMapper.select(sysMenu);
		} else {
			sysMenuMapper.selectByCondition(ConditionUtil.buildCondition(SysMenu.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
