package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.mldong.common.logger.IRequestLogStore;
import com.mldong.common.logger.LoggerModel;
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
import com.mldong.modules.sys.dto.SysRequestLogParam;
import com.mldong.modules.sys.dto.SysRequestLogPageParam;
import com.mldong.modules.sys.entity.SysRequestLog;
import com.mldong.modules.sys.mapper.SysRequestLogMapper;
import com.mldong.modules.sys.service.SysRequestLogService;
/**
 * <p>业务接口实现层</p>
 * <p>请求日志</p>
 *
 * @since 2020-09-06 07:33:38
 */
@Service
public class SysRequestLogServiceImpl implements SysRequestLogService, IRequestLogStore {
	@Autowired
	private SysRequestLogMapper sysRequestLogMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysRequestLogParam param) {
		Date now = new Date();
		SysRequestLog sysRequestLog = new SysRequestLog();
		BeanUtils.copyProperties(param, sysRequestLog);
		sysRequestLog.setCreateTime(now);
		sysRequestLog.setUpdateTime(now);
		sysRequestLog.setIsDeleted(YesNoEnum.NO);
		return sysRequestLogMapper.insertSelective(sysRequestLog);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysRequestLogParam param) {
		Date now = new Date();
		SysRequestLog sysRequestLog = new SysRequestLog();
		BeanUtils.copyProperties(param, sysRequestLog);
		sysRequestLog.setUpdateTime(now);
		return sysRequestLogMapper.updateByPrimaryKeySelective(sysRequestLog);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysRequestLog upSysRequestLog = new SysRequestLog();
		upSysRequestLog.setUpdateTime(now);
		Condition condition = new Condition(SysRequestLog.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysRequestLogMapper.updateByConditionSelective(upSysRequestLog, condition);
		// 逻辑删除
		return sysRequestLogMapper.deleteByCondition(condition);
	}

	@Override
	public SysRequestLog get(Long id) {
		return sysRequestLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysRequestLog> list(SysRequestLogPageParam param) {
		Page<SysRequestLog> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysRequestLog sysRequestLog = new SysRequestLog();
			sysRequestLogMapper.select(sysRequestLog);
		} else {
			sysRequestLogMapper.selectByCondition(ConditionUtil.buildCondition(SysRequestLog.class, whereParams));		}
		return CommonPage.toPage(page);
	}

	@Override
	public int saveRequestLog(LoggerModel model) {
		Date now = new Date();
		SysRequestLog log = new SysRequestLog();
		BeanUtils.copyProperties(model, log, "startTime","endTime");
		log.setCreateTime(now);
		log.setUpdateTime(now);
		log.setStartTime(new Date(model.getStartTime()));
		log.setEndTime(new Date(model.getEndTime()));
		log.setIsDeleted(YesNoEnum.NO);
		if(model.getUri().contains("save")) {
			log.setRequestType(SysRequestLog.RequestTypeEnum.SAVE);
		} else if(model.getUri().contains("update")) {
			log.setRequestType(SysRequestLog.RequestTypeEnum.UPDATE);
		} else if(model.getUri().contains("remove")) {
			log.setRequestType(SysRequestLog.RequestTypeEnum.REMOVE);
		} else if(model.getUri().contains("export")) {
			log.setRequestType(SysRequestLog.RequestTypeEnum.EXPORT);
		} else if(model.getUri().contains("import")) {
			log.setRequestType(SysRequestLog.RequestTypeEnum.IMPORT);
		} else if(model.getUri().contains("get") || model.getUri().contains("list")) {
			// 忽略get和list
		} else  {
			log.setRequestType(SysRequestLog.RequestTypeEnum.OTHER);
		}
		return sysRequestLogMapper.insertSelective(log);
	}
}
