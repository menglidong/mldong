package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.sys.dto.SysDictItemParam;
import com.mldong.modules.sys.dto.SysDictItemPageParam;
import com.mldong.modules.sys.entity.SysDictItem;
import com.mldong.modules.sys.mapper.SysDictItemMapper;
import com.mldong.modules.sys.service.SysDictItemService;
/**
 * <p>业务接口实现层</p>
 * <p>字典项</p>
 *
 * @since 2020-06-11 11:49:37
 */
@Service
public class SysDictItemServiceImpl implements SysDictItemService{
	@Autowired
	private SysDictItemMapper sysDictItemMapper;
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysDictItemParam param) {
		Date now = new Date();
		SysDictItem sysDictItem = new SysDictItem();
		BeanUtils.copyProperties(param, sysDictItem);
		sysDictItem.setCreateTime(now);
		sysDictItem.setUpdateTime(now);
		sysDictItem.setIsDeleted(YesNoEnum.NO);
		return sysDictItemMapper.insertSelective(sysDictItem);
	}
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysDictItemParam param) {
		Date now = new Date();
		SysDictItem sysDictItem = new SysDictItem();
		BeanUtils.copyProperties(param, sysDictItem);
		sysDictItem.setUpdateTime(now);
		return sysDictItemMapper.updateByPrimaryKeySelective(sysDictItem);
	}
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysDictItem upUser = new SysDictItem();
		upUser.setIsDeleted(YesNoEnum.YES);
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysDictItem.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysDictItemMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysDictItemMapper.updateByConditionSelective(upUser, condition);
	}

	@Override
	public SysDictItem get(Long id) {
		return sysDictItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysDictItem> list(SysDictItemPageParam param) {
		Page<SysDictItem> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysDictItem sysDictItem = new SysDictItem();
			sysDictItemMapper.select(sysDictItem);
		} else {
			sysDictItemMapper.selectByCondition(ConditionUtil.buildCondition(SysDictItem.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
