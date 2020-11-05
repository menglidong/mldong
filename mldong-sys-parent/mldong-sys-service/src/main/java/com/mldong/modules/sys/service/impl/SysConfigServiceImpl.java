package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

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
import com.mldong.modules.sys.dto.SysConfigParam;
import com.mldong.modules.sys.dto.SysConfigPageParam;
import com.mldong.modules.sys.entity.SysConfig;
import com.mldong.modules.sys.mapper.SysConfigMapper;
import com.mldong.modules.sys.service.SysConfigService;
// START###################
// ###################END
/**
 * <p>业务接口实现层</p>
 * <p>参数配置</p>
 *
 * @since 2020-11-05 08:22:30
 */
@Service
public class SysConfigServiceImpl implements SysConfigService{
	@Autowired
	private SysConfigMapper sysConfigMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysConfigParam param) {
		Date now = new Date();
		SysConfig sysConfig = new SysConfig();
		BeanUtils.copyProperties(param, sysConfig);
		sysConfig.setCreateTime(now);
		sysConfig.setUpdateTime(now);
		sysConfig.setIsDeleted(YesNoEnum.NO);
		return sysConfigMapper.insertSelective(sysConfig);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysConfigParam param) {
		Date now = new Date();
		SysConfig sysConfig = new SysConfig();
		BeanUtils.copyProperties(param, sysConfig);
		sysConfig.setUpdateTime(now);
		return sysConfigMapper.updateByPrimaryKeySelective(sysConfig);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysConfig upSysConfig = new SysConfig();
		upSysConfig.setUpdateTime(now);
		Condition condition = new Condition(SysConfig.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysConfigMapper.updateByConditionSelective(upSysConfig, condition);
		// 逻辑删除
		return sysConfigMapper.deleteByCondition(condition);
	}

	@Override
	public SysConfig get(Long id) {
		return sysConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysConfig> list(SysConfigPageParam param) {
		Page<SysConfig> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysConfig sysConfig = new SysConfig();
			sysConfigMapper.select(sysConfig);
		} else {
			sysConfigMapper.selectByCondition(ConditionUtil.buildCondition(SysConfig.class, whereParams));		}
		return CommonPage.toPage(page);
	}
	// START###################
	// ###################END
}