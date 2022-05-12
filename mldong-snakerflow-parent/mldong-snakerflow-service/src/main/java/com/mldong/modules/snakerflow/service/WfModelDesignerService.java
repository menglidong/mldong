package com.mldong.modules.snakerflow.service;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.snakerflow.dto.WfModelDesignerDefineParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerPageParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerParam;
import com.mldong.modules.snakerflow.entity.WfModelDesigner;
import com.mldong.modules.snakerflow.vo.WfModelDesignerVO;

import java.util.List;

/**
 * <p>业务接口层</p>
 * <p>模型设计</p>
 *
 * @since 2022-05-08 09:12:53
 */
public interface WfModelDesignerService {
	/**
	 * 添加模型设计
	 * @param param
	 * @return
	 */
	public int save(WfModelDesignerParam param);
	/**
	 * 更新模型设计
	 * @param param
	 * @return
	 */
	public int update(WfModelDesignerParam param);
	/**
	 * 删除模型设计
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询模型设计
	 * @param id
	 * @return
	 */
	public WfModelDesigner get(Long id);

	/**
	 * 查询模型-包含xml
	 * @param id
	 * @return
	 */
	public WfModelDesignerVO getWithExt(Long id);
	/**
	 * 分页查询模型设计列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<WfModelDesigner> list(WfModelDesignerPageParam param);

	/**
	 * 保存流程定义
	 * @param param
	 * @return
	 */
	public int saveDefine(WfModelDesignerDefineParam param);
}