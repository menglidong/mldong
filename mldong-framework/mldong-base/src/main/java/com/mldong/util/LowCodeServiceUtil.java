package com.mldong.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.*;
import com.mldong.consts.CommonConstant;
import com.mldong.excel.IMyExcelExportServer;
import com.mldong.exception.ServiceException;
import com.mldong.web.QueryParamHolder;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 低代服务工具类
 *
 * @author mldong
 * @date 2023/2/22
 */
public class LowCodeServiceUtil {
    private LowCodeServiceUtil() {
    }

    /**
     * 处理一对多表入库
     *
     * @param mainId                       主表id
     * @param refMainIdColumnName          关联的id在子表中的列名
     * @param subList                      子表数据
     * @param baseMapper                   操作子表的mapper
     * @param isUpdate                     是否为更新操作
     * @param mDict                        额外参数
     * @param lowCodeOneToManyExpandHandle 增强处理类
     * @param <T>                          ids:String,data:List<T>,……
     * @return
     */
    public static <T> Dict oneToManySaveOrUpdate(Long mainId, String refMainIdColumnName, List<T> subList, BaseMapper<T> baseMapper, boolean isUpdate, Dict mDict, LowCodeOneToManyExpandHandle lowCodeOneToManyExpandHandle) {
        Dict dict = Dict.create();
        if (subList == null) {
            QueryWrapper wrapper = new QueryWrapper<>();
            wrapper.eq(StrUtil.toUnderlineCase(refMainIdColumnName), mainId);
            List<T> data = baseMapper.selectList(wrapper);
            String ids = data.stream().map(item -> {
                return ReflectUtil.getFieldValue(item, "id").toString();
            }).collect(Collectors.joining(","));
            dict.put("ids", ids);
            dict.put("data", data);
            if (lowCodeOneToManyExpandHandle != null) {
                dict.putAll(lowCodeOneToManyExpandHandle.handle(mainId, refMainIdColumnName, null, baseMapper, isUpdate, mDict));
            }
            return dict;
        }
        if (subList.isEmpty()) {
            // 清空
            QueryWrapper delWrapper = new QueryWrapper<>();
            delWrapper.eq(StrUtil.toUnderlineCase(refMainIdColumnName), mainId);
            if (lowCodeOneToManyExpandHandle != null) {
                lowCodeOneToManyExpandHandle.delWrapper(mDict, delWrapper);
            }
            baseMapper.delete(delWrapper);
            dict.put("ids", "");
            dict.put("data", new ArrayList<T>());
            if (lowCodeOneToManyExpandHandle != null) {
                dict.putAll(lowCodeOneToManyExpandHandle.handle(mainId, refMainIdColumnName, subList, baseMapper, isUpdate, mDict));
            }
            return dict;
        }
        List<String> ignoreDelIdList = new ArrayList<>();
        if (isUpdate) {
            subList.forEach(item -> {
                Object id = ReflectUtil.getFieldValue(item, "id");
                ReflectUtil.setFieldValue(item, StrUtil.toCamelCase(refMainIdColumnName), mainId);
                T selectId = baseMapper.selectById((Serializable) id);
                if (ObjectUtil.isNull(id)) {
                    baseMapper.insert(item);
                    id = ReflectUtil.getFieldValue(item, "id");
                } else if (ObjectUtil.isEmpty(selectId)) {
                    baseMapper.insert(item);
                    id = ReflectUtil.getFieldValue(item, "id");
                } else {
                    baseMapper.updateById(item);
                }
                ignoreDelIdList.add(id.toString());
            });
        } else {
            subList.forEach(item -> {
                ReflectUtil.setFieldValue(item, StrUtil.toCamelCase(refMainIdColumnName), mainId);
                baseMapper.insert(item);
                Object id = ReflectUtil.getFieldValue(item, "id");
                ignoreDelIdList.add(id.toString());
            });
        }
        // 删除冗余数据
        QueryWrapper delWrapper = new QueryWrapper<>();
        delWrapper.eq(StrUtil.toUnderlineCase(refMainIdColumnName), mainId);
        delWrapper.notIn("id", ignoreDelIdList);
        if (lowCodeOneToManyExpandHandle != null) {
            lowCodeOneToManyExpandHandle.delWrapper(mDict, delWrapper);
        }
        baseMapper.delete(delWrapper);
        dict.put("ids", CollectionUtil.join(ignoreDelIdList, ","));
        dict.put("data", subList);
        if (lowCodeOneToManyExpandHandle != null) {
            dict.putAll(lowCodeOneToManyExpandHandle.handle(mainId, refMainIdColumnName, subList, baseMapper, isUpdate, mDict));
        }
        return dict;
    }

    /**
     * 处理一对多表入库
     *
     * @param mainId                       主表id
     * @param refMainIdColumnName          关联的id在子表中的列名
     * @param subList                      子表数据
     * @param baseMapper                   操作子表的mapper
     * @param isUpdate                     是否为更新操作
     * @param lowCodeOneToManyExpandHandle 增强处理类
     * @param <T>                          ids:String,data:List<T>,……
     * @return
     */
    public static <T> Dict oneToManySaveOrUpdate(Long mainId, String refMainIdColumnName, List<T> subList, BaseMapper<T> baseMapper, boolean isUpdate, LowCodeOneToManyExpandHandle lowCodeOneToManyExpandHandle) {
        return oneToManySaveOrUpdate(mainId, refMainIdColumnName, subList, baseMapper, isUpdate, null, lowCodeOneToManyExpandHandle);
    }

    /**
     * @param mainId
     * @param refMainIdColumnName
     * @param subList
     * @param baseMapper
     * @param isUpdate
     * @param <T>
     * @return
     */
    public static <T> Dict oneToManySaveOrUpdate(Long mainId, String refMainIdColumnName, List<T> subList, BaseMapper<T> baseMapper, boolean isUpdate) {
        return oneToManySaveOrUpdate(mainId, refMainIdColumnName, subList, baseMapper, isUpdate, null);
    }

    /**
     * 按ID更新，并设置指定字段为空
     *
     * @param baseMapper           执行类
     * @param entity               实体
     * @param setNullFieldNameList 要置空的列
     * @param <T>
     * @return
     */
    public static <T> int updateById(BaseMapper<T> baseMapper, T entity, List<String> setNullFieldNameList) {
        return updateById(baseMapper, entity, setNullFieldNameList, false);
    }

    ;

    /**
     * 按ID更新，并设置指定字段为空
     *
     * @param baseMapper           执行类
     * @param entity               实体
     * @param setNullFieldNameList 要置空的列
     * @param judgeNull            是否判断null值
     * @param <T>
     * @return
     */
    public static <T> int updateById(BaseMapper<T> baseMapper, T entity, List<String> setNullFieldNameList, boolean judgeNull) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper();
        Object id = ReflectUtil.getFieldValue(entity, "id");
        updateWrapper.eq("id", id);
        setNullFieldNameList.forEach((fieldName) -> {
            String camelCaseFieldName = StrUtil.toCamelCase(fieldName);
            if (ReflectUtil.hasField(entity.getClass(), camelCaseFieldName)) {
                if (judgeNull) {
                    // 是否判断null
                    if (ObjectUtil.isNull(ReflectUtil.getFieldValue(entity, camelCaseFieldName))) {
                        ReflectUtil.setFieldValue(entity, camelCaseFieldName, null);
                        updateWrapper.set(StrUtil.toUnderlineCase(fieldName), null);
                    }
                } else {
                    ReflectUtil.setFieldValue(entity, camelCaseFieldName, null);
                    updateWrapper.set(StrUtil.toUnderlineCase(fieldName), null);
                }
            }
        });
        return baseMapper.update(entity, updateWrapper);
    }

    /**
     * 一对多处理类增强接口
     *
     * @param <T>
     */
    public interface LowCodeOneToManyExpandHandle<T> {
        Dict handle(Long mainId, String refMainIdColumnName, List<T> subList, BaseMapper<T> baseMapper, boolean isUpdate, Dict dict);

        /**
         * 删除条件处理
         *
         * @param dict
         * @param delWrapper
         * @return
         */
        default QueryWrapper delWrapper(Dict dict, QueryWrapper delWrapper) {
            return delWrapper;
        }
    }

    /**
     * 处理子表-新增
     * @param clazz
     */
    public static <T> void callOneToManySave(Class<?> clazz, T t) {
        Map<String,IOneToManyHandler> handlerMap = SpringUtil.getBeansOfType(IOneToManyHandler.class);
        handlerMap.forEach((k,v)->{
            if(ObjectUtil.equals(clazz,v.getServiceClass())) {
                v.saveOrUpdate(t, false);
            }
        });
    }

    /**
     * 处理子表-新增
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManySave(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.saveOrUpdate(t, false);
            });
        }
    }

    /**
     * 处理子表-修改
     * @param clazz
     */
    public static <T> void callOneToManyUpdate(Class<?> clazz, T t) {
        Map<String,IOneToManyHandler> handlerMap = SpringUtil.getBeansOfType(IOneToManyHandler.class);
        handlerMap.forEach((k,v)->{
            if(ObjectUtil.equals(clazz,v.getServiceClass())) {
                v.saveOrUpdate(t, true);
            }
        });
    }
    /**
     * 处理子表-修改
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManyUpdate(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.saveOrUpdate(t, true);
            });
        }
    }
    /**
     * 处理子表-查询
     * @param clazz
     */
    public static <T> void callOneToManyWrap(Class<?> clazz,T t) {
        Map<String,IOneToManyHandler> handlerMap = SpringUtil.getBeansOfType(IOneToManyHandler.class);
        handlerMap.forEach((k,v)->{
            if(ObjectUtil.equals(clazz,v.getServiceClass())) {
                v.wrap(t);
            }
        });
    }
    /**
     * 处理子表-查询
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManyWrap(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.wrap(t);
            });
        }
    }
    /**
     * 处理子表列表-查询
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManyWrapList(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.wrapList(t);
            });
        }
    }
    /**
     * 处理子表详情-查询
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManyWrapDetail(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.wrapDetail(t);
            });
        }
    }
    /**
     * 处理子表导出-查询
     * @param t
     * @param oneToManyHandler 处理类
     * @param <T>
     */
    public static <T> void callOneToManyWrapExport(T t,IOneToManyHandler ...oneToManyHandler){
        if(ObjectUtil.isNotEmpty(oneToManyHandler)) {
            Arrays.stream(oneToManyHandler).forEach(item->{
                item.wrapExport(t);
            });
        }
    }
    /**
     * 下拉选择
     * @param baseMapper
     * @param param
     * @param labelKey
     * @param valueKey
     * @param <T>
     * @return
     */
    public static <T> List<LabelValueVO> select(BaseMapper<T> baseMapper, PageParam<T> param, String labelKey, String valueKey) {
        return select(baseMapper,param,labelKey,valueKey, null);
    }
    /**
     * 下拉选择
     * @param baseMapper
     * @param param
     * @param labelKey
     * @param valueKey
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> List<LabelValueVO> select(BaseMapper<T> baseMapper, PageParam<T> param, String labelKey, String valueKey, MConsumer<QueryWrapper<T>> consumer) {
        QueryWrapper<T> queryWrapper = param.buildQueryWrapper();
        if(consumer!=null && consumer.has()) {
            queryWrapper.and(consumer);
        }
        List<LabelValueVO> voList = new ArrayList<>();
        List<T> records = new ArrayList<>();
        String extFieldNames = param.getExtFieldNames();
        if(ObjectUtil.equal(param.getIncludeType(),1)) {
            IPage<T> page = param.buildMpPage();
            baseMapper.selectPage(page,queryWrapper);
            if (CollUtil.isNotEmpty(param.getIncludeIds())) {
                List<T> includeIds = baseMapper.selectBatchIds(param.getIncludeIds());
                if (CollUtil.isNotEmpty(page.getRecords())) {
                    page.getRecords().removeIf(v -> param.getIncludeIds().contains(StrUtil.toString(ReflectUtil.getFieldValue(v,valueKey))));
                    page.getRecords().addAll(includeIds);
                } else {
                    page.setRecords(includeIds);
                }
            }
            records.addAll(page.getRecords());
        } else if(ObjectUtil.equal(param.getIncludeType(),2)){
            if(CollectionUtil.isNotEmpty(param.getIncludeIds())){
                queryWrapper.in(valueKey,param.getIncludeIds());
                List<T> list=baseMapper.selectList(queryWrapper);
                records.addAll(list);
            }
        } else {
            IPage<T> page = param.buildMpPage();
            baseMapper.selectPage(page,queryWrapper);
            records.addAll(page.getRecords());
        }
        records.forEach(item->{
            LabelValueVO vo = LabelValueVO.builder().label(Convert.toStr(ReflectUtil.getFieldValue(item,labelKey)))
                    .value(ReflectUtil.getFieldValue(item,valueKey)).ext(Dict.create()).build();
            if(StrUtil.isNotEmpty(extFieldNames)) {
                // 追加扩展信息
                Arrays.stream(extFieldNames.split(",")).forEach(fieldName->{
                    String camelCaseName = StrUtil.toCamelCase(fieldName);
                    vo.getExt().put(camelCaseName,ReflectUtil.getFieldValue(item,camelCaseName));
                });
            }
            voList.add(vo);

        });
        return voList;
    }
    public interface MConsumer<T> extends Consumer<T>{
        boolean has();
    }

    /**
     * 提交给控制层调用
     * @param module
     * @param tableName
     * @param param
     * @return
     */
    public static List<LabelValueVO> select(String module,String tableName,Dict param) {
        String name = StrUtil.upperFirst(tableName);
        BaseMapper baseMapper = SpringUtil.getBean(tableName+"Mapper");
        String className = StrUtil.format("{}.modules.{}.dto.{}PageParam",
                CommonConstant.DEFAULT_PACKAGE_NAME,module,name);
        Class<?> clazz = ClassUtil.loadClass(className);
        Object object = BeanUtil.toBean(param,clazz);
        String labelKey = param.get("labelKey","name");
        String labelValue = param.get("labelValue","id");
        List<LabelValueVO> res = select(baseMapper,(PageParam)object,labelKey,labelValue);
        return res;
    }

    /**
     * 校验唯一性
     * @param baseMapper
     * @param column 列名
     * @param value 值
     * @param id 当前行id,更新时
     * @param <T>
     * @return
     */
    public static <T> void checkUnique(BaseMapper<T> baseMapper, String column,Object value, Long id,String error) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.toUnderlineCase(column),value);
        if(id!=null) {
            queryWrapper.ne("id",id);
        }
        long count  = baseMapper.selectCount(queryWrapper);
        if(count>0) {
            ServiceException.throwBiz(99999999,error);
        }
    }

    // 缓存高级查询元数据
    private static final Map<String,List<Dict>> QUERY_SCHEMA_MAP = new HashMap<>();
    /**
     * 构建查询元数据
     * @param module
     * @param tableName
     * @return
     */
    public static List<Dict> buildQuerySchema(String module,String tableName) {
        String key = module+tableName;
        List<Dict> res = QUERY_SCHEMA_MAP.get(key);
        if(CollectionUtil.isNotEmpty(res)) return res;
        String name = StrUtil.upperFirst(tableName);
        String className = StrUtil.format("{}.modules.{}.dto.{}PageParam",
                CommonConstant.DEFAULT_PACKAGE_NAME,module,name);
        Class<?> clazz = ClassUtil.loadClass(className);
        Object object = ReflectUtil.newInstance(clazz);
        Map<String,List<Dict>> querySchema = ((PageParam) object).buildQuerySchema();
        List<Dict> finalRes = new ArrayList<>();
        querySchema.forEach((k, v)->{
            Dict dict = Dict.create();
            dict.put("remark",k);
            dict.put("queryFieldList",v);
            finalRes.add(dict);
        });
        QUERY_SCHEMA_MAP.put(key,finalRes);
        return finalRes;
    }

    /**
     * 构造导出下载url
     * @param module
     * @param tableName
     * @return
     */
    public static Dict generateExportUrl(String module,String tableName) {
        RedisTemplate<String,String> redisTemplate = SpringUtil.getBean(
                new TypeReference<RedisTemplate<String,String>>(){}
        );
        String uuid = StrUtil.uuid();
        String baseUrl = QueryParamHolder.me().getStr("baseUrl");
        Dict dict = Dict.create();
        dict.put("url",StrUtil.format("{}/{}/{}/export?token={}", baseUrl,module, tableName, uuid));
        redisTemplate.opsForValue().set("export-url:"+uuid, JSONUtil.toJsonStr(QueryParamHolder.me()),3, TimeUnit.MINUTES);
        return dict;
    }
    // 导出接口
    public static void export(String module,String tableName, String token) {
        String name = StrUtil.upperFirst(tableName);
        RedisTemplate<String,String> redisTemplate = SpringUtil.getBean(
                new TypeReference<RedisTemplate<String,String>>(){}
        );
        String json = redisTemplate.opsForValue().get("export-url:"+token);
        if(ObjectUtil.isEmpty(json)) {
            ServiceException.throwBiz(99999999,"导出链接已失效");
        }
        String className = StrUtil.format("{}.modules.{}.dto.{}PageParam",
                CommonConstant.DEFAULT_PACKAGE_NAME,module,name);
        Class<?> clazz = ClassUtil.loadClass(className);
        Object object = JSONUtil.toBean(json,clazz);
        PageParam param = (PageParam) object;
        String serviceName = StrUtil.format("{}ServiceImpl",tableName);
        QueryParamHolder.set(JSONUtil.toBean(json,Dict.class));
        Object service = SpringUtil.getBean(serviceName);
        IMyExcelExportServer excelExportServer = null;
        if(service instanceof IMyExcelExportServer) {
            // 如果服务类实现了导出查询服务接口，则使用导出查询服务接口导出
            excelExportServer = (IMyExcelExportServer) service;
            PoiUtil.exportExcelBigWithStream(StrUtil.format("{}-{}.xlsx", param.getExcelName(), DateUtil.formatDate(DateUtil.date())), excelExportServer, param);
        } else {
            // 使用反射调用分页接口导出
            PoiUtil.exportExcelBigWithStream(StrUtil.format("{}-{}.xlsx", param.getExcelName(), DateUtil.formatDate(DateUtil.date())), (IMyExcelExportServer<Object, PageParam>) (pageParam, pageNum) -> {
                pageParam.setPageNum(pageNum);
                pageParam.setPageSize(1000);
                pageParam.setIsCount(YesNoEnum.NO);
                if(!ObjectUtil.equals(pageParam.getExportScope(),"1")) {
                    // 1==>导出全部数据；2==>当前筛选结果
                    QueryParamHolder.set(JSONUtil.toBean(json,Dict.class));
                } else {
                    QueryParamHolder.set(Dict.create());
                }
                CommonPage<Object> commonPage = ReflectUtil.invoke(service,"page",pageParam);
                return commonPage.getRows();
            }, param);
        }
    }
}
