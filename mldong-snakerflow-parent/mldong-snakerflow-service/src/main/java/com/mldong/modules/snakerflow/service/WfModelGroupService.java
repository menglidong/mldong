package com.mldong.modules.snakerflow.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.snakerflow.dto.WfModelGroupParam;
import com.mldong.modules.snakerflow.dto.WfModelGroupPageParam;
import com.mldong.modules.snakerflow.entity.WfModelGroup;

/**
 * <p>业务接口层</p>
 * <p>模型分组</p>
 *
 * @since 2022-05-08 09:12:53
 */
public interface WfModelGroupService {
	/**
	 * 添加模型分组
	 * @param param
	 * @return
	 */
	public int save(WfModelGroupParam param);
	/**
	 * 更新模型分组
	 * @param param
	 * @return
	 */
	public int update(WfModelGroupParam param);
	/**
	 * 删除模型分组
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询模型分组
	 * @param id
	 * @return
	 */
	public WfModelGroup get(Long id);
	/**
	 * 分页查询模型分组列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<WfModelGroup> list(WfModelGroupPageParam param);
}