package com.mldong.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mldong.common.validator.ValidatorTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.scanner.DictScanner;
import com.mldong.common.scanner.model.DictItemModel;
import com.mldong.common.scanner.model.DictModel;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.sys.dto.SysDictKeyParam;
import com.mldong.modules.sys.dto.SysDictPageParam;
import com.mldong.modules.sys.dto.SysDictParam;
import com.mldong.modules.sys.entity.SysDict;
import com.mldong.modules.sys.entity.SysDictItem;
import com.mldong.modules.sys.mapper.SysDictItemMapper;
import com.mldong.modules.sys.mapper.SysDictMapper;
import com.mldong.modules.sys.service.SysDictService;
/**
 * <p>业务接口实现层</p>
 * <p>字典</p>
 *
 * @since 2020-06-11 11:49:37
 */
@Service
public class SysDictServiceImpl implements SysDictService{
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private SysDictItemMapper sysDictItemMapper;
	@Autowired
	private DictScanner dictScanner;
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysDictParam param) {
		ValidatorTool.checkUnique(sysDictMapper,SysDict.class, "dictKey", param.getDictKey());
		Date now = new Date();
		SysDict sysDict = new SysDict();
		BeanUtils.copyProperties(param, sysDict);
		sysDict.setCreateTime(now);
		sysDict.setUpdateTime(now);
		sysDict.setIsDeleted(YesNoEnum.NO);
		return sysDictMapper.insertSelective(sysDict);
	}
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysDictParam param) {
		ValidatorTool.checkUniqueOnUpdate(sysDictMapper,SysDict.class, "dictKey", param.getDictKey(), param.getId());
		Date now = new Date();
		SysDict sysDict = new SysDict();
		BeanUtils.copyProperties(param, sysDict);
		sysDict.setUpdateTime(now);
		return sysDictMapper.updateByPrimaryKeySelective(sysDict);
	}
	@Caching(evict={
			@CacheEvict(value="sys_dict_key")
	})
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysDict upUser = new SysDict();
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysDict.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysDictMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysDictMapper.deleteByCondition(condition);
	}

	@Override
	public SysDict get(Long id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysDict> list(SysDictPageParam param) {
		Page<SysDict> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysDict sysDict = new SysDict();
			sysDictMapper.select(sysDict);
		} else {
			sysDictMapper.selectByCondition(ConditionUtil.buildCondition(SysDict.class, whereParams));		}
		return CommonPage.toPage(page);
	}
	@Cacheable(value = "sys_dict_key",key="#param.dictKey+'-'+#param.type")
	@Override
	public DictModel getByDictKey(SysDictKeyParam param) {
		DictModel dictModel = null;
		if("enum".equals(param.getType())) {
			dictModel =  dictScanner.getDictMap().get(param.getDictKey());
		}
		if(null != dictModel) {
			return dictModel;
		}
		SysDict q = new SysDict();
		q.setDictKey(param.getDictKey());
		SysDict dict = sysDictMapper.selectOne(q);
		if(null == dict) {
			return null;
		}
		Condition condition = new Condition(SysDictItem.class);
		condition.orderBy("sort").asc();
		condition.createCriteria().andEqualTo("dictId",dict.getId());
		List<SysDictItem> list = sysDictItemMapper.selectByCondition(condition);
		if(list.isEmpty()) {
			return null;
		}
		dictModel =  new DictModel();
		dictModel.setDictKey(dict.getDictKey());
		dictModel.setName(dict.getName());
		BeanUtils.copyProperties(dict, dictModel);
		List<DictItemModel> items = new ArrayList<DictItemModel>();
		dictModel.setItems(items);
		list.forEach(dictItem->{
			DictItemModel item = new DictItemModel();
			item.setName(dictItem.getName());
			item.setDictItemValue(dictItem.getDictItemValue());
		});
		return dictModel;
	}
	@Override
	public List<DictModel> listAllEnum() {
		return dictScanner.getDictList();
	}
}
