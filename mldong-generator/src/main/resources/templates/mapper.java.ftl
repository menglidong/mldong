package ${package.Mapper};

import ${package.Entity}.${entity};
import ${package.Entity?replace("entity","vo")}.${entity}VO;
import ${superMapperClassPackage};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>
import java.util.List;
/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    List<${entity}VO> selectCustom(IPage<${entity}VO> page, @Param(Constants.WRAPPER) Wrapper<${entity}> wrapper);
    ${entity}VO findById(@Param("id") Long id);
}
</#if>
