package com.mldong.modules.sys.mapper;
import java.util.List;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.common.base.BaseMapper;
/**
 * <p>持久层</p>
 * <p>角色</p>
 *
 * @since 2020-06-05 10:06:12
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 删除角色-不可恢复
	 * @param ids
	 * @return
	 */
	public int deleteBatchByIds(List<Long> ids);
	/**
	 * 删除角色-可恢复
	 * @param ids
	 * @return
	 */
	public int removeBatchByIds(List<Long> ids);
	/**
	 * 恢复删除角色
	 * @param ids
	 * @return
	 */
	public int restoreBatchByIds(List<Long> ids);
}