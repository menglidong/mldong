package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.modules.sys.enums.SysErrEnum;
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
import com.mldong.modules.sys.dto.SysDeptParam;
import com.mldong.modules.sys.dto.SysDeptPageParam;
import com.mldong.modules.sys.entity.SysDept;
import com.mldong.modules.sys.mapper.SysDeptMapper;
import com.mldong.modules.sys.service.SysDeptService;
/**
 * <p>业务接口实现层</p>
 * <p>部门</p>
 *
 * @since 2020-10-21 09:08:16
 */
@Service
public class SysDeptServiceImpl implements SysDeptService{
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysDeptParam param) {
		if(null == param.getParentId()) {
			param.setParentId(0L);
		}
		SysDept info = sysDeptMapper.selectByPrimaryKey(param.getParentId());
		// 如果父节点不为正常状态,则不允许新增子节点
		if (YesNoEnum.NO.equals(param.getIsEnabled())) {
			// 禁用
			throw new BizException(SysErrEnum.SYS80000008);
		}
		Date now = new Date();
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(param, sysDept);
		sysDept.setCreateTime(now);
		sysDept.setUpdateTime(now);
		sysDept.setIsDeleted(YesNoEnum.NO);
		return sysDeptMapper.insertSelective(sysDept);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysDeptParam param) {
		Date now = new Date();
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(param, sysDept);
		sysDept.setUpdateTime(now);
		return sysDeptMapper.updateByPrimaryKeySelective(sysDept);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysDept upSysDept = new SysDept();
		upSysDept.setUpdateTime(now);
		Condition condition = new Condition(SysDept.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysDeptMapper.updateByConditionSelective(upSysDept, condition);
		// 逻辑删除
		return sysDeptMapper.deleteByCondition(condition);
	}

	@Override
	public SysDept get(Long id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysDept> list(SysDeptPageParam param) {
		Page<SysDept> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysDept sysDept = new SysDept();
			sysDeptMapper.select(sysDept);
		} else {
			sysDeptMapper.selectByCondition(ConditionUtil.buildCondition(SysDept.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
