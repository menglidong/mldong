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
import com.mldong.modules.sys.dto.SysNoticeParam;
import com.mldong.modules.sys.dto.SysNoticePageParam;
import com.mldong.modules.sys.entity.SysNotice;
import com.mldong.modules.sys.mapper.SysNoticeMapper;
import com.mldong.modules.sys.service.SysNoticeService;
/**
 * <p>业务接口实现层</p>
 * <p>通知公告</p>
 *
 * @since 2020-10-27 05:10:53
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService{
	@Autowired
	private SysNoticeMapper sysNoticeMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysNoticeParam param) {
		Date now = new Date();
		SysNotice sysNotice = new SysNotice();
		BeanUtils.copyProperties(param, sysNotice);
		sysNotice.setCreateTime(now);
		sysNotice.setUpdateTime(now);
		sysNotice.setIsDeleted(YesNoEnum.NO);
		return sysNoticeMapper.insertSelective(sysNotice);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysNoticeParam param) {
		Date now = new Date();
		SysNotice sysNotice = new SysNotice();
		BeanUtils.copyProperties(param, sysNotice);
		sysNotice.setUpdateTime(now);
		return sysNoticeMapper.updateByPrimaryKeySelective(sysNotice);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysNotice upSysNotice = new SysNotice();
		upSysNotice.setUpdateTime(now);
		Condition condition = new Condition(SysNotice.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysNoticeMapper.updateByConditionSelective(upSysNotice, condition);
		// 逻辑删除
		return sysNoticeMapper.deleteByCondition(condition);
	}

	@Override
	public SysNotice get(Long id) {
		return sysNoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysNotice> list(SysNoticePageParam param) {
		Page<SysNotice> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysNotice sysNotice = new SysNotice();
			sysNoticeMapper.select(sysNotice);
		} else {
			sysNoticeMapper.selectByCondition(ConditionUtil.buildCondition(SysNotice.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
