package ${package.ServiceImpl};

<#if !r>
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${g.basePackage}.base.CommonPage;
import ${g.basePackage}.base.YesNoEnum;
import ${package.Other}.${entity}PageParam;
import ${package.Other}.${entity}Param;
import ${package.Entity?replace("entity","vo")}.${entity}VO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
</#if>
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
<#if isTree>
import ${g.basePackage}.tree.TreeTool;
</#if>
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    <#if !r>
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(${entity}Param param) {
        param.setId(null);
        ${entity} ${table.entityPath} = new ${entity}();
        BeanUtil.copyProperties(param, ${table.entityPath});
        return super.save(${table.entityPath});
    }

    @Override
    public boolean update(${entity}Param param) {
        ${entity} ${table.entityPath} = new ${entity}();
        BeanUtil.copyProperties(param, ${table.entityPath});
        return super.updateById(${table.entityPath});
    }

    @Override
    public CommonPage<${entity}VO> page(${entity}PageParam param) {
        IPage<${entity}VO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        <#if hasLogicDeleted>
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        </#if>
        List<${entity}VO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ${entity}VO findById(Long id) {
        return baseMapper.findById(id);
    }
    </#if>
    <#if isTree>
    @Override
    public List<${entity}VO> tree(${entity}PageParam param) {
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.orderByAsc("sort");
        List<${entity}> list = this.list(queryWrapper);
        List<${entity}VO> treeData = TreeTool.listToTree(BeanUtil.copyToList(list,${entity}VO.class),0L,${entity}VO.class);
        return treeData;
    }
    </#if>
}
</#if>
