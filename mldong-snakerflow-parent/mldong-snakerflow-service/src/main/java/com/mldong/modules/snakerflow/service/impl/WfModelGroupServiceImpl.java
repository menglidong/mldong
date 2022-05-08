package com.mldong.modules.snakerflow.service.impl;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.snakerflow.dto.WfModelGroupPageParam;
import com.mldong.modules.snakerflow.dto.WfModelGroupParam;
import com.mldong.modules.snakerflow.entity.WfModelGroup;
import com.mldong.modules.snakerflow.mapper.WfModelGroupMapper;
import com.mldong.modules.snakerflow.service.WfModelGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * <p>业务接口实现层</p>
 * <p>模型分组</p>
 *
 * @since 2022-05-08 09:12:53
 */
@Service
public class WfModelGroupServiceImpl implements WfModelGroupService{
	@Autowired
	private WfModelGroupMapper wfModelGroupMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(WfModelGroupParam param) {
		Date now = new Date();
		WfModelGroup wfModelGroup = new WfModelGroup();
		BeanUtils.copyProperties(param, wfModelGroup);
		wfModelGroup.setCreateTime(now);
		wfModelGroup.setUpdateTime(now);
		wfModelGroup.setUserId(RequestHolder.getUserId());
		return wfModelGroupMapper.insertSelective(wfModelGroup);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(WfModelGroupParam param) {
		WfModelGroup record = wfModelGroupMapper.selectByPrimaryKey(param.getId());
		AssertTool.notNull(record,"模型分组不存在");
		if(!record.getUserId().equals(RequestHolder.getUserId())) {
			AssertTool.throwBiz(GlobalErrEnum.GL99990403,"无数据操作权限");
		}
		Date now = new Date();
		WfModelGroup wfModelGroup = new WfModelGroup();
		BeanUtils.copyProperties(param, wfModelGroup);
		wfModelGroup.setUpdateTime(now);
		return wfModelGroupMapper.updateByPrimaryKeySelective(wfModelGroup);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		WfModelGroup upWfModelGroup = new WfModelGroup();
		upWfModelGroup.setUpdateTime(now);
		Condition condition = new Condition(WfModelGroup.class);
		condition.createCriteria().andIn("id", ids)
		.andEqualTo("userId", RequestHolder.getUserId());
		return wfModelGroupMapper.deleteByCondition(condition);
	}

	@Override
	public WfModelGroup get(Long id) {
		WfModelGroup wfModelGroup = wfModelGroupMapper.selectByPrimaryKey(id);
		if(wfModelGroup!=null && !wfModelGroup.getUserId().equals(RequestHolder.getUserId())) {
			return null;
		}
		return wfModelGroup;
	}

	@Override
	public CommonPage<WfModelGroup> list(WfModelGroupPageParam param) {
		Page<WfModelGroup> page =param.buildPage(true);
		param.addEqualsToPre("userId", RequestHolder.getUserId());
		List<WhereParam> whereParams = param.getWhereParams();
		wfModelGroupMapper.selectByCondition(ConditionUtil.buildCondition(WfModelGroup.class, whereParams));
		return CommonPage.toPage(page);
	}
}