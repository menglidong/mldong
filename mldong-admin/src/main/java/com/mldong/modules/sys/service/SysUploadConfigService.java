package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysUploadConfigParam;
import com.mldong.modules.sys.dto.SysUploadConfigPageParam;
import com.mldong.modules.sys.entity.SysUploadConfig;
/**
 * <p>业务接口层</p>
 * <p>上传配置</p>
 *
 * @since 2020-06-14 10:55:36
 */
public interface SysUploadConfigService {
	/**
	 * 添加上传配置
	 * @param param
	 * @return
	 */
	public int save(SysUploadConfigParam param);
	/**
	 * 更新上传配置
	 * @param param
	 * @return
	 */
	public int update(SysUploadConfigParam param);
	/**
	 * 删除上传配置
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询上传配置
	 * @param id
	 * @return
	 */
	public SysUploadConfig get(Long id);
	/**
	 * 分页查询上传配置列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysUploadConfig> list(SysUploadConfigPageParam param);

}
