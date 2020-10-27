package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysNoticeParam;
import com.mldong.modules.sys.dto.SysNoticePageParam;
import com.mldong.modules.sys.entity.SysNotice;
/**
 * <p>业务接口层</p>
 * <p>通知公告</p>
 *
 * @since 2020-10-27 05:10:53
 */
public interface SysNoticeService {
	/**
	 * 添加通知公告
	 * @param param
	 * @return
	 */
	public int save(SysNoticeParam param);
	/**
	 * 更新通知公告
	 * @param param
	 * @return
	 */
	public int update(SysNoticeParam param);
	/**
	 * 删除通知公告
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询通知公告
	 * @param id
	 * @return
	 */
	public SysNotice get(Long id);
	/**
	 * 分页查询通知公告列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysNotice> list(SysNoticePageParam param);

}
