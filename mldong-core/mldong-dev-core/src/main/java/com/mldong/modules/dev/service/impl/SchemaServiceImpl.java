package com.mldong.modules.dev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.exception.ServiceException;
import com.mldong.modules.dev.dto.SchemaPageParam;
import com.mldong.modules.dev.dto.SchemaParam;
import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.entity.SchemaField;
import com.mldong.modules.dev.entity.SchemaGroup;
import com.mldong.modules.dev.enums.SchemaFieldDataTypeEnum;
import com.mldong.modules.dev.enums.SchemaFormTypeEnum;
import com.mldong.modules.dev.enums.SchemaTableTypeEnum;
import com.mldong.modules.dev.mapper.SchemaGroupMapper;
import com.mldong.modules.dev.mapper.SchemaMapper;
import com.mldong.modules.dev.service.SchemaFieldService;
import com.mldong.modules.dev.service.SchemaService;
import com.mldong.modules.dev.vo.SchemaFieldVO;
import com.mldong.modules.dev.vo.SchemaGroupVO;
import com.mldong.modules.dev.vo.SchemaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
/**
 * <p>
 * 数据模型 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Service
@RequiredArgsConstructor
public class SchemaServiceImpl extends ServiceImpl<SchemaMapper, Schema> implements SchemaService {
    private final Environment environment;
    private final SchemaFieldService schemaFieldService;
    private final SchemaGroupMapper schemaGroupMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SchemaParam param) {
        param.setId(null);
        Schema schema = new Schema();
        BeanUtil.copyProperties(param, schema);
        schema.setVariable(JSONUtil.toJsonStr(param.getExt()));
        return super.save(schema);
    }

    @Override
    public boolean update(SchemaParam param) {
        Schema schema = new Schema();
        BeanUtil.copyProperties(param, schema);
        schema.setVariable(JSONUtil.toJsonStr(param.getExt()));
        return super.updateById(schema);
    }

    @Override
    public CommonPage<SchemaVO> page(SchemaPageParam param) {
        IPage<SchemaVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<SchemaVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public SchemaVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<Dict> dbTable(String keywords) {
        List<TableInfo> tableInfoList = getTableInfoList(new ArrayList<>(), keywords);
        List<Dict> dictList = new ArrayList<>();
        tableInfoList.forEach(tableInfo -> {
            boolean disabled = baseMapper.selectCount(
                    Wrappers.lambdaQuery(Schema.class)
                    .eq(Schema::getTableName, tableInfo.getName())
            ) >0;
            dictList.add(Dict.create().set("name",tableInfo.getName()).set("comment",tableInfo.getComment()).set("disabled", disabled));
        });
        return dictList;
    }

    @Override
    public void importTable(Long schemaGroupId, List<String> tableNames) {
        List<TableInfo> tableInfoList = getTableInfoList(tableNames, null);
        if(CollUtil.isEmpty(tableInfoList)) return;
        List<Schema> hisSchemas = this.list(Wrappers.lambdaQuery(Schema.class).
                in(Schema::getTableName, tableNames));
        /*
         * 清除历史模型
         */
        if (CollUtil.isNotEmpty(hisSchemas)) {
            List<Long> hisSchemasIds = hisSchemas.stream().map(Schema::getId).collect(Collectors.toList());
            schemaFieldService.remove(Wrappers.lambdaQuery(SchemaField.class).in(SchemaField::getSchemaId, hisSchemasIds));
            this.removeByIds(hisSchemasIds);
        }
        Map<String,Long> cacheMap = new HashMap<>();
        for (TableInfo tableInfo : tableInfoList) {
            List<SchemaField> schemaFields = new ArrayList<>();
            Schema schema = new Schema();
            if(schemaGroupId!=null) {
                schema.setSchemaGroupId(schemaGroupId);
            } else {
                schema.setSchemaGroupId(getSchemaGroupId(tableInfo.getName(), cacheMap));
            }
            schema.setId(IdWorker.getId());
            schema.setTableName(tableInfo.getName());
            schema.setTableType(SchemaTableTypeEnum.SINGLE_TABLE.getCode());//默认
            schema.setFormType(SchemaFormTypeEnum.POP_UP_WINDOW.getCode());//默认
            schema.setIsTree(YesNoEnum.NO.getCode());//默认
            schema.setRemark(Convert.toStr(tableInfo.getComment(),schema.getTableName()));
            List<TableField> tableFields = tableInfo.getFields();
            AtomicLong sort = new AtomicLong(0L);
            List<SchemaField> subSchemaFields = new ArrayList<>();
            tableFields.stream().filter(tableField -> {
                return !CollectionUtil.newArrayList("is_deleted").contains(tableField.getName());
            }).forEach(tableField -> {
                SchemaField schemaField = new SchemaField();
                schemaField.setSchemaId(schema.getId());
                schemaField.setFieldName(tableField.getName());
                schemaField.setDataType(SchemaFieldDataTypeEnum.forValue(tableField.getColumnType().getType()).getCode().toString());
                schemaField.setIsPrimary(tableField.isKeyIdentityFlag()?YesNoEnum.YES.getCode():YesNoEnum.NO.getCode());
                schemaField.setNullable(tableField.getMetaInfo().isNullable()?YesNoEnum.YES.getCode():YesNoEnum.NO.getCode());
                schemaField.setSort(sort.getAndIncrement());
                schemaField.setFieldSize(tableField.getMetaInfo().getLength());
                schemaField.setComponent("Input");//默认
                schemaField.setRemark(Convert.toStr(tableField.getComment(),schemaField.getFieldName()));
                schemaField.setDefaultValue(tableField.getMetaInfo().getDefaultValue());
                Map<String, Object> variable = new HashMap<>();
                schemaField.setVariable(JSONUtil.toJsonStr(variable));
                subSchemaFields.add(schemaField);
            });
            schemaFields.addAll(subSchemaFields);
            //设置默认的列表字段集合
            schema.setListKeys(subSchemaFields.stream().map(SchemaField::getFieldName).collect(Collectors.joining(",")));
            Map<String, Object> variable = new HashMap<>();
            // 默认有列表、新增、删除、修改、详情权限，与后端接口相对应，后续可根据后端实现情况追加默认值
            variable.put("defaultAuthTypeList", CollectionUtil.newArrayList("list", "save", "remove", "update", "detail"));
            schema.setVariable(JSONUtil.toJsonStr(variable));
            // 插入数据模型
            this.save(schema);
            // 插入数据模型字段
            schemaFieldService.saveBatch(subSchemaFields);
        }
    }

    @Override
    public SchemaVO getByTableName(String tableName) {
        Schema schema = baseMapper.selectOne(Wrappers.lambdaQuery(Schema.class).eq(Schema::getTableName,tableName));
        if(schema == null) {
            ServiceException.throwBiz(9999, "数据模型不存在");
        }
        SchemaVO vo = BeanUtil.toBean(schema,SchemaVO.class);
        List<SchemaField> schemaFieldList = schemaFieldService.list(
                Wrappers.lambdaQuery(SchemaField.class).eq(SchemaField::getSchemaId,schema.getId())
                .orderByAsc(SchemaField::getSort)
                .orderByAsc(SchemaField::getId)
        );
        List<String> listKeys = new ArrayList<>();
        if(StrUtil.isNotEmpty(schema.getListKeys())) {
            listKeys.addAll(CollectionUtil.newArrayList(schema.getListKeys().split(",")));
        }
        List<String> searchFormKeys = new ArrayList<>();
        if(StrUtil.isNotEmpty(schema.getSearchFormKeys())) {
            searchFormKeys.addAll(CollectionUtil.newArrayList(schema.getSearchFormKeys().split(",")));
        }
        // 设置列，并对列表字段、搜索字段进行排序
        vo.setColumns(schemaFieldList.stream().map(item->{
            SchemaFieldVO schemaFieldVO = BeanUtil.toBean(item,SchemaFieldVO.class);
            schemaFieldVO.setListSort(listKeys.indexOf(item.getFieldName()));
            schemaFieldVO.setSearchSort(searchFormKeys.indexOf(item.getFieldName()));
            return schemaFieldVO;
        }).collect(Collectors.toList()));
        // 组装分组数据
        SchemaGroup schemaGroup = null;
        if (ObjectUtil.isNotNull(vo.getSchemaGroupId())) {
            schemaGroup = schemaGroupMapper.selectById(vo.getSchemaGroupId());
        }
        SchemaGroupVO schemaGroupVO = new SchemaGroupVO();
        if (ObjectUtil.isNull(schemaGroup)) {
            // 找不到，使用自身构建分组
            schemaGroupVO.setId(schema.getId() + 1);
            schemaGroupVO.setName(schema.getRemark());
            schemaGroupVO.setCode(vo.getTableCamelName());
            SchemaVO schemaVO = new SchemaVO();
            BeanUtil.copyProperties(schema, schemaVO);
            schemaGroupVO.setSchemaList(CollectionUtil.newArrayList(schemaVO));
        } else {
            // 存在分组
            BeanUtil.copyProperties(schemaGroup, schemaGroupVO);
            LambdaQueryWrapper<Schema> schemaLambdaQueryWrapper = Wrappers.lambdaQuery();
            schemaLambdaQueryWrapper.eq(Schema::getSchemaGroupId, schemaGroup.getId());
            schemaLambdaQueryWrapper.orderByAsc(Schema::getSort);
            List<Schema> schemaList = baseMapper.selectList(schemaLambdaQueryWrapper);
            List<SchemaVO> schemaVOList = BeanUtil.copyToList(schemaList, SchemaVO.class);
            schemaGroupVO.setSchemaList(schemaVOList);
        }
        vo.setSchemaGroup(schemaGroupVO);
        return vo;
    }

    /**
     * 获取数据库表
     * @param includeTableNames
     * @param likeTable
     * @return
     */
    private List<TableInfo> getTableInfoList(List<String> includeTableNames,String likeTable) {
        String dbUrl = environment.getProperty("spring.datasource.url") ;
        String dbUsername = environment.getProperty("spring.datasource.username") ;
        String dbPassword = environment.getProperty("spring.datasource.password") ;
        PackageConfig packageConfig = new PackageConfig.Builder().build();
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(dbUrl,dbUsername,dbPassword).build();
        StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
        if(StrUtil.isNotEmpty(likeTable)){
            strategyConfigBuilder.likeTable(new LikeTable(likeTable));
        }
        if(CollectionUtil.isNotEmpty(includeTableNames)) {
            strategyConfigBuilder.addInclude(includeTableNames);
        }
        StrategyConfig strategyConfig = strategyConfigBuilder.build();
        TemplateConfig templateConfig = new TemplateConfig.Builder().build();
        GlobalConfig globalConfig = new GlobalConfig.Builder().build();
        InjectionConfig injectionConfig = new InjectionConfig.Builder().build();
        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig, dataSourceConfig, strategyConfig, templateConfig, globalConfig,injectionConfig);
        List<TableInfo> tableInfoList = configBuilder.getTableInfoList();
        return tableInfoList;
    }

    /**
     * 根据表名称获取数据模型分组id
     * @param tableName
     * @param cacheMap
     * @return
     */
    private Long getSchemaGroupId(String tableName,Map<String,Long> cacheMap) {
        String moduleName = tableName.substring(0,tableName.indexOf("_"));
        Long schemaGroupId = cacheMap.get(moduleName);
        if(schemaGroupId!=null) return schemaGroupId;
        SchemaGroup schemaGroup = schemaGroupMapper.selectOne(Wrappers.lambdaQuery(SchemaGroup.class).eq(SchemaGroup::getCode,moduleName));
        if(schemaGroup!=null) {
            cacheMap.put(moduleName,schemaGroupId);
            return schemaGroup.getId();
        }
        return null;
    }
}
