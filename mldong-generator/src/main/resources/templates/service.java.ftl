package ${package.Service};

<#if !r>
import ${g.basePackage}.base.CommonPage;
import ${package.Other}.${entity}PageParam;
import ${package.Other}.${entity}Param;
import ${package.Entity?replace("entity","vo")}.${entity}VO;
</#if>
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
<#if isTree>
import java.util.List;
</#if>
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
  <#if !r>
    /**
    * 添加${table.comment!}
    * @param param
    * @return
    */
    boolean save(${entity}Param param);

    /**
    * 更新${table.comment!}
    * @param param
    * @return
    */
    boolean update(${entity}Param param);

    /**
    * 自定义分页查询${table.comment!}
    * @param param
    * @return
    */
    CommonPage<${entity}VO> page(${entity}PageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ${entity}VO findById(Long id);
  </#if>
  <#if isTree>
    /**
    * 获取${table.comment!}树
    * @param param
    * @return
    */
    List<${entity}VO> tree(${entity}PageParam param);
  </#if>
}
</#if>
