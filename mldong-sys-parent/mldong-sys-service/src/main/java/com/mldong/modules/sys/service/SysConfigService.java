package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysConfigParam;
import com.mldong.modules.sys.dto.SysConfigPageParam;
import com.mldong.modules.sys.entity.SysConfig;
// START###################
// ###################END
/**
 * <p>业务接口层</p>
 * <p>参数配置</p>
 *
 * @since 2020-11-05 08:22:30
 */
public interface SysConfigService {
	/**
	 * 添加参数配置
	 * @param param
	 * @return
	 */
	public int save(SysConfigParam param);
	/**
	 * 更新参数配置
	 * @param param
	 * @return
	 */
	public int update(SysConfigParam param);
	/**
	 * 删除参数配置
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询参数配置
	 * @param id
	 * @return
	 */
	public SysConfig get(Long id);
	/**
	 * 分页查询参数配置列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysConfig> list(SysConfigPageParam param);
	// START###################
	// ###################END
}