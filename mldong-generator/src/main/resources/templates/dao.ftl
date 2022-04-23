package ${basePackage}.modules.${moduleName}.dao;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>持久层-可自定义层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
@Repository
public interface ${table.className}Dao extends BaseMapper<${table.className}> {

}