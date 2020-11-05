package ${basePackage}.modules.${moduleName}.mapper;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>持久层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
@Repository
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {

}