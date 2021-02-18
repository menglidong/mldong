package com.mldong.modules.sys.service.impl;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.common.tool.JsonTool;
import com.mldong.common.upload.CommonUpload;
import com.mldong.common.upload.model.UploadResult;
import com.mldong.common.upload.model.UploadTokenParam;
import com.mldong.common.upload.model.UploadTokenVo;
import com.mldong.modules.sys.dto.SysUploadBizTypeParam;
import com.mldong.modules.sys.dto.SysUploadRecordPageParam;
import com.mldong.modules.sys.dto.SysUploadRecordParam;
import com.mldong.modules.sys.entity.SysUploadConfig;
import com.mldong.modules.sys.entity.SysUploadRecord;
import com.mldong.modules.sys.mapper.SysUploadConfigMapper;
import com.mldong.modules.sys.mapper.SysUploadRecordMapper;
import com.mldong.modules.sys.service.SysUploadRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <p>业务接口实现层</p>
 * <p>上传记录</p>
 *
 * @since 2020-06-14 10:16:54
 */
@Service
public class SysUploadRecordServiceImpl implements SysUploadRecordService{
	private final static Logger LOGGER = LoggerFactory.getLogger(SysUploadRecordServiceImpl.class);
	@Autowired
	private SysUploadRecordMapper sysUploadRecordMapper;
	@Autowired
	private SysUploadConfigMapper sysUploadConfigMapper;
	@Autowired
	private CommonUpload commonUpload;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysUploadRecordParam param) {
		Date now = new Date();
		SysUploadRecord sysUploadRecord = new SysUploadRecord();
		BeanUtils.copyProperties(param, sysUploadRecord);
		sysUploadRecord.setCreateTime(now);
		sysUploadRecord.setUpdateTime(now);
		sysUploadRecord.setIsDeleted(YesNoEnum.NO);
		return sysUploadRecordMapper.insertSelective(sysUploadRecord);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysUploadRecordParam param) {
		Date now = new Date();
		SysUploadRecord sysUploadRecord = new SysUploadRecord();
		BeanUtils.copyProperties(param, sysUploadRecord);
		sysUploadRecord.setUpdateTime(now);
		return sysUploadRecordMapper.updateByPrimaryKeySelective(sysUploadRecord);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysUploadRecord upUser = new SysUploadRecord();
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysUploadRecord.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysUploadRecordMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysUploadRecordMapper.deleteByCondition(condition);
	}

	@Override
	public SysUploadRecord get(Long id) {
		return sysUploadRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysUploadRecord> list(SysUploadRecordPageParam param) {
		Page<SysUploadRecord> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysUploadRecord sysUploadRecord = new SysUploadRecord();
			sysUploadRecordMapper.select(sysUploadRecord);
		} else {
			sysUploadRecordMapper.selectByCondition(ConditionUtil.buildCondition(SysUploadRecord.class, whereParams));
		}
		if(param.getIncludeIds()!=null && !param.getIncludeIds().isEmpty()) {
			param.getIncludeIds().removeIf(id -> {
				return page.getResult().stream().filter(item -> {
					return item.getId().equals(id);
				}).count() > 0;
			});
			if(!param.getIncludeIds().isEmpty()) {
				Condition condition = new Condition(SysUploadRecord.class);
				condition.createCriteria().andIn("id", param.getIncludeIds());
				page.getResult().addAll(0, sysUploadRecordMapper.selectByCondition(condition));
			}
		}
		return CommonPage.toPage(page);
	}
	@Override
	public UploadTokenVo createUploadToken(SysUploadBizTypeParam param) {
		SysUploadConfig q = new SysUploadConfig();
		q.setBizType(param.getBizType());
		SysUploadConfig uploadConfig = sysUploadConfigMapper.selectOne(q);
		if(null == uploadConfig){
			// 文件上传配置不存在
			throw new BizException(GlobalErrEnum.GL99990008);
		}
		UploadTokenParam tokenParam = new UploadTokenParam();
		BeanUtils.copyProperties(uploadConfig, tokenParam);
		tokenParam.setRecord(YesNoEnum.YES.equals(uploadConfig.getIsRecord()));
		return commonUpload.createUploadToken(tokenParam);
	}
	@Override
	public UploadResult handleCallback(String callbackAuthHeader,
			String callbackBody) {
		LOGGER.debug("【七牛上传回调-callbackAuthHeader】:{}",callbackAuthHeader);
		LOGGER.debug("【七牛上传回调-callbackBody】:{}",callbackBody);
		Map<String,Object> map = JsonTool.jsonToMap(callbackBody);
		Object bizType = map.get("bizType");
		if(bizType == null) {
			// 文件上传配置不存在
			throw new BizException(GlobalErrEnum.GL99990008);
		}
		SysUploadConfig q = new SysUploadConfig();
		q.setBizType(bizType.toString());
		SysUploadConfig uploadConfig = sysUploadConfigMapper.selectOne(q);
		if(null == uploadConfig){
			// 文件上传配置不存在
			throw new BizException(GlobalErrEnum.GL99990008);
		}
		Date now = new Date();
		UploadResult result = commonUpload.handleCallback(callbackAuthHeader, uploadConfig.getCallbackUrl(), callbackBody);
		if(YesNoEnum.YES.equals(uploadConfig.getIsRecord())){
			SysUploadRecord record = new SysUploadRecord();
			record.setBizId(result.getBizId());
			record.setBizType(result.getBizType());
			record.setCreateTime(now);
			record.setFileExt(result.getFileExt());
			record.setFileName(result.getFileName());
			record.setFileSize(result.getFileSize()==null?0L:result.getFileSize());
			record.setIsDeleted(YesNoEnum.NO);
			record.setMimeType(result.getMimeType());
			record.setUpdateTime(now);
			record.setUrl(result.getUrl());
			sysUploadRecordMapper.insertSelective(record);
		}
		return result;
	}

	@Override
	public int saveReturnId(SysUploadRecord uploadRecord) {
		return sysUploadRecordMapper.insertUseGeneratedKeys(uploadRecord);
	}
}
