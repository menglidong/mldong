package com.mldong.modules.snakerflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.snakerflow.dto.WfModelDesignerDefineParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerPageParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerParam;
import com.mldong.modules.snakerflow.entity.WfModelDesigner;
import com.mldong.modules.snakerflow.entity.WfModelDesignerHis;
import com.mldong.modules.snakerflow.mapper.WfModelDesignerHisMapper;
import com.mldong.modules.snakerflow.mapper.WfModelDesignerMapper;
import com.mldong.modules.snakerflow.service.WfModelDesignerService;
import com.mldong.modules.snakerflow.vo.WfModelDesignerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * <p>业务接口实现层</p>
 * <p>模型设计</p>
 *
 * @since 2022-05-08 09:12:53
 */
@Service
public class WfModelDesignerServiceImpl implements WfModelDesignerService{
	@Autowired
	private WfModelDesignerMapper wfModelDesignerMapper;
	@Autowired
	private WfModelDesignerHisMapper wfModelDesignerHisMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(WfModelDesignerParam param) {
		Date now = new Date();
		WfModelDesigner wfModelDesigner = new WfModelDesigner();
		BeanUtils.copyProperties(param, wfModelDesigner);
		wfModelDesigner.setCreateTime(now);
		wfModelDesigner.setUpdateTime(now);
		wfModelDesigner.setIsDeleted(YesNoEnum.NO);
		wfModelDesigner.setUserId(RequestHolder.getUserId());
		return wfModelDesignerMapper.insertSelective(wfModelDesigner);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(WfModelDesignerParam param) {
		WfModelDesigner record = wfModelDesignerMapper.selectByPrimaryKey(param.getId());
		AssertTool.notNull(record,"模型定义不存在");
		if(!record.getUserId().equals(RequestHolder.getUserId())) {
			AssertTool.throwBiz(GlobalErrEnum.GL99990403,"无数据操作权限");
		}
		Date now = new Date();
		WfModelDesigner wfModelDesigner = new WfModelDesigner();
		BeanUtils.copyProperties(param, wfModelDesigner);
		wfModelDesigner.setUpdateTime(now);
		return wfModelDesignerMapper.updateByPrimaryKeySelective(wfModelDesigner);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		WfModelDesigner upWfModelDesigner = new WfModelDesigner();
		upWfModelDesigner.setUpdateTime(now);
		Condition condition = new Condition(WfModelDesigner.class);
		condition.createCriteria().andIn("id", ids)
		.andEqualTo("userId", RequestHolder.getUserId());
		// 更新时间
		wfModelDesignerMapper.updateByConditionSelective(upWfModelDesigner, condition);
		// 逻辑删除
		return wfModelDesignerMapper.deleteByCondition(condition);
	}

	@Override
	public WfModelDesigner get(Long id) {
		WfModelDesigner wfModelDesigner = wfModelDesignerMapper.selectByPrimaryKey(id);
		if(wfModelDesigner!=null && !wfModelDesigner.getUserId().equals(RequestHolder.getUserId())) {
			return null;
		}
		return wfModelDesigner;
	}

	@Override
	public WfModelDesignerVO getWithExt(Long id) {
		WfModelDesigner wfModelDesigner = wfModelDesignerMapper.selectByPrimaryKey(id);
		if(wfModelDesigner!=null && !wfModelDesigner.getUserId().equals(RequestHolder.getUserId())) {
			return null;
		}
		WfModelDesignerVO vo = new WfModelDesignerVO();
		BeanUtil.copyProperties(wfModelDesigner, vo);
		vo.setXml(getLatestXml(id));
		return vo;
	}

	/**
	 * 获取最新的xml
	 * @param id
	 * @return
	 */
	private String getLatestXml(Long id) {
		PageHelper.startPage(1,1);
		Condition condition = new Condition(WfModelDesignerHis.class);
		condition.createCriteria().andEqualTo("modelDesignerId", id);
		condition.orderBy("id").desc();
		List<WfModelDesignerHis> list = wfModelDesignerHisMapper.selectByCondition(condition);
		if(!list.isEmpty()) {
			return new String(list.get(0).getContent());
		}
		return "";
	}
	@Override
	public CommonPage<WfModelDesigner> list(WfModelDesignerPageParam param) {
		Page<WfModelDesigner> page =param.buildPage(true);
		if(StrUtil.isEmpty(page.getOrderBy())) {
			page.setOrderBy("update_time desc");
		}
		param.addEqualsToPre("userId", RequestHolder.getUserId());
		List<WhereParam> whereParams = param.getWhereParams();
		wfModelDesignerMapper.selectByCondition(ConditionUtil.buildCondition(WfModelDesigner.class, whereParams));
		return CommonPage.toPage(page);
	}

	@Override
	public int saveDefine(WfModelDesignerDefineParam param) {
		// 相等不需要保存
		String latestXml = getLatestXml(param.getModelDesignerId());
		if(latestXml.equals(param.getContent())) {
			return 1;
		}
		Condition condition = new Condition(WfModelDesigner.class);
		condition.createCriteria().andEqualTo("id", param.getModelDesignerId())
		.andEqualTo("userId",RequestHolder.getUserId());
		int count = wfModelDesignerMapper.selectCountByCondition(condition);
		if(count == 0) {
			AssertTool.throwBiz(GlobalErrEnum.GL99990100,"模型设计ID不存在或无权限访问");
		}
		Date now = new Date();
		// 更新时间
		WfModelDesigner upDate = new WfModelDesigner();
		upDate.setUpdateTime(now);
		upDate.setId(param.getModelDesignerId());
		wfModelDesignerMapper.updateByPrimaryKeySelective(upDate);
		// 插入新版本
		WfModelDesignerHis his = new WfModelDesignerHis();
		his.setModelDesignerId(param.getModelDesignerId());
		his.setCreateTime(now);
		his.setUpdateTime(now);
		his.setContent(param.getContent().getBytes());
		return wfModelDesignerHisMapper.insertSelective(his);
	}
}