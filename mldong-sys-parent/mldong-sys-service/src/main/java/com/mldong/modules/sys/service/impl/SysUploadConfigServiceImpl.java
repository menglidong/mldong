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
import com.mldong.modules.sys.dto.SysUploadConfigParam;
import com.mldong.modules.sys.dto.SysUploadConfigPageParam;
import com.mldong.modules.sys.entity.SysUploadConfig;
import com.mldong.modules.sys.mapper.SysUploadConfigMapper;
import com.mldong.modules.sys.service.SysUploadConfigService;
/**
 * <p>业务接口实现层</p>
 * <p>上传配置</p>
 *
 * @since 2020-06-14 10:55:36
 */
@Service
public class SysUploadConfigServiceImpl implements SysUploadConfigService{
	@Autowired
	private SysUploadConfigMapper sysUploadConfigMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysUploadConfigParam param) {
		Date now = new Date();
		SysUploadConfig sysUploadConfig = new SysUploadConfig();
		BeanUtils.copyProperties(param, sysUploadConfig);
		sysUploadConfig.setCreateTime(now);
		sysUploadConfig.setUpdateTime(now);
		sysUploadConfig.setIsDeleted(YesNoEnum.NO);
		return sysUploadConfigMapper.insertSelective(sysUploadConfig);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysUploadConfigParam param) {
		Date now = new Date();
		SysUploadConfig sysUploadConfig = new SysUploadConfig();
		BeanUtils.copyProperties(param, sysUploadConfig);
		sysUploadConfig.setUpdateTime(now);
		return sysUploadConfigMapper.updateByPrimaryKeySelective(sysUploadConfig);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysUploadConfig upUser = new SysUploadConfig();
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysUploadConfig.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysUploadConfigMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysUploadConfigMapper.deleteByCondition(condition);
	}

	@Override
	public SysUploadConfig get(Long id) {
		return sysUploadConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysUploadConfig> list(SysUploadConfigPageParam param) {
		Page<SysUploadConfig> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysUploadConfig sysUploadConfig = new SysUploadConfig();
			sysUploadConfigMapper.select(sysUploadConfig);
		} else {
			sysUploadConfigMapper.selectByCondition(ConditionUtil.buildCondition(SysUploadConfig.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
