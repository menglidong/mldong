package ${basePackage}.modules.${moduleName}.mapper;
import java.util.List;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.common.base.BaseMapper;
/**
 * <p>持久层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {
	/**
	 * 删除${table.remark?replace("表","")}-不可恢复
	 * @param ids
	 * @return
	 */
	public int deleteBatchByIds(List<Long> ids);
	/**
	 * 删除${table.remark?replace("表","")}-可恢复
	 * @param ids
	 * @return
	 */
	public int removeBatchByIds(List<Long> ids);
	/**
	 * 恢复删除${table.remark?replace("表","")}
	 * @param ids
	 * @return
	 */
	public int restoreBatchByIds(List<Long> ids);
}