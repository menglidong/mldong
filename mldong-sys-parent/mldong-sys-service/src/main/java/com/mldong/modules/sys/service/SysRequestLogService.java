package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysRequestLogParam;
import com.mldong.modules.sys.dto.SysRequestLogPageParam;
import com.mldong.modules.sys.entity.SysRequestLog;
/**
 * <p>业务接口层</p>
 * <p>请求日志</p>
 *
 * @since 2020-09-06 07:33:38
 */
public interface SysRequestLogService  {
	/**
	 * 添加请求日志
	 * @param param
	 * @return
	 */
	public int save(SysRequestLogParam param);
	/**
	 * 更新请求日志
	 * @param param
	 * @return
	 */
	public int update(SysRequestLogParam param);
	/**
	 * 删除请求日志
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询请求日志
	 * @param id
	 * @return
	 */
	public SysRequestLog get(Long id);
	/**
	 * 分页查询请求日志列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysRequestLog> list(SysRequestLogPageParam param);

}
