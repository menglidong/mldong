package com.mldong.base;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mldong.excel.MyExcelExportEntity;
import com.mldong.web.QueryParamHolder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 分页查询基类
 * @author mldong
 *
 * @param <T>
 */
@Data
public class PageParam<T> {
	/**
	 * 每几页
	 */
	@ApiModelProperty(value="每几页")
	private Integer pageNum;
	/**
	 * 每页大小
	 */
	@ApiModelProperty(value="每页大小")
    private Integer pageSize;
	@ApiModelProperty(value="关键字",hidden = true)
	private String keywords;
	@ApiModelProperty(value = "关键字对应的列名，多个使用英文逗号分割", hidden = true)
	private String searchKeys;
	@ApiModelProperty(value="排序字段")
	private String orderBy;
	@ApiModelProperty(value="排序类型，业务中自行转换成orderBy")
	private Integer orderType;
	@ApiModelProperty(value="是否查询总条数")
	private YesNoEnum isCount;
	@ApiModelProperty(value = "查询模式",hidden = true)
	private Integer includeType;
	@ApiModelProperty(value="或查询-下拉列表编辑模式专用",hidden = true)
	private List<String> includeIds;
	@ApiModelProperty(value="数据范围就是组织机构id集合",hidden = true)
	private List<Long> dataScope;
	@ApiModelProperty(value="扩展属性名，多个英文逗号分割")
	private String extFieldNames;
	final static String REG_EXP = "(^_([a-zA-Z0-9\\s]_?)*$)|(^[a-zA-Z\\.](_?[a-zA-Z0-9\\.\\s])*_?$)";
	@ApiModelProperty(value="过滤类型")
	private Integer filterType;
	@ApiModelProperty(value="过滤类型集合")
	private List<Integer> filterTypes;
	@ApiModelProperty(value = "要排除的查询条件，多个使用英语逗号分割。例：（m_EQ_id,m_LIKE_name）",hidden = true)
	private String excludeWheres;
	@ApiModelProperty(value = "excel名称")
	private String excelName;
	@ApiModelProperty(value = "导出范围")
	private String exportScope;
	@ApiModelProperty(value = "excel列信息")
	private List<MyExcelExportEntity> excelColumns = new ArrayList<>();
	/**
	 * 构建mybatis-plus分页对象
	 * @return
	 */
	public IPage buildMpPage() {
		if(pageNum==null) {
			this.pageNum = 1;
		}
		if(pageSize == null) {
			this.pageSize = 10;
		}
		IPage page = new Page(getPageNum(),getPageSize(), !YesNoEnum.NO.equals(isCount));
		return page;
	}

	/**
	 * 构建查询条件
	 * @return 默认从请求体中获取
	 */
	public QueryWrapper buildQueryWrapper() {
		Dict dict = QueryParamHolder.me();
		return buildQueryWrapper(dict);
	}

	/**
	 * 构建查询条件
	 * @param dict 入参
	 * @return
	 */
	public QueryWrapper buildQueryWrapper(Dict dict) {
		QueryWrapper queryWrapper = Wrappers.query();
		// 获取所有属性m_{type}_{column}
		// 有表别名时m_{alias}_{type}_{column}
		Field fields [] = ReflectUtil.getFields(this.getClass());
		Arrays.stream(fields).forEach(field->{
			// 实体类中的值可覆盖请求正文中的值-即实体类的值优先，方便在服务端调整值后再查询
			dict.put(field.getName(),ReflectUtil.getFieldValue(this, field.getName()));
		});
		String keys [] = dict.keySet().toArray(new String[]{});
		List<String> excludeWhereList = new ArrayList<>();
		if(StrUtil.isNotEmpty(excludeWheres)) {
			excludeWhereList.addAll(CollectionUtil.toList(excludeWheres.split(",")));
		}
		for (int i = 0; i < keys.length ; i++) {
			//Field field = fields[i];
			String fieldName = keys[i];
			if(excludeWhereList.contains(fieldName))continue;
			if(!fieldName.startsWith("m_")) continue;
			String arr [] = fieldName.split("_");
			if(arr.length !=3 && arr.length!=4) continue;
			String type = "";
			String column = "";
			if(arr.length == 3) {
				type = arr[1];
				column = StrUtil.toUnderlineCase(arr[2]);
			} else {
				type = arr[2];
				column = arr[1] + "." + StrUtil.toUnderlineCase(arr[3]);
			}
			// Object fieldValue = ReflectUtil.getFieldValue(this, fieldName);
			Object fieldValue = dict.get(fieldName);
			if(ObjectUtil.isEmpty(fieldValue)) continue;

			switch (type){
				case "EQ":
					queryWrapper.eq(column, fieldValue);
					break;
				case "NE":
					queryWrapper.ne(column, fieldValue);
					break;
				case "GT":
					queryWrapper.gt(column, fieldValue);
					break;
				case "GE":
					queryWrapper.ge(column, fieldValue);
					break;
				case "LT":
					queryWrapper.lt(column, fieldValue);
					break;
				case "LE":
					queryWrapper.le(column, fieldValue);
					break;
				case "LIKE":
					queryWrapper.like(column, fieldValue);
					break;
				case "NLIKE":
					queryWrapper.notLike(column, fieldValue);
					break;
				case "LLIKE":
					queryWrapper.likeLeft(column, fieldValue);
					break;
				case "RLIKE":
					queryWrapper.likeRight(column, fieldValue);
					break;
				case "BT":
					if(fieldValue instanceof List) {
						List btValue = Convert.toList(fieldValue);
						if(btValue.size()==2) {
							Object btValue1 = btValue.get(0);
							Object btValue2 = btValue.get(1);
							queryWrapper.between(column, btValue1, btValue2);
						}
					} else if(fieldValue instanceof String) {
						String btArr [] = ((String) fieldValue).split(",");
						Object btValue1 = btArr[0];
						Object btValue2 = btArr[1];
						queryWrapper.between(column, btValue1, btValue2);
					}
					break;
				case "IN":
					if(fieldValue instanceof List) {
						List inValue = Convert.toList(fieldValue);
						queryWrapper.in(column, inValue);
					} else if(fieldValue instanceof String) {
						String btArr [] = ((String) fieldValue).split(",");
						queryWrapper.in(column, CollectionUtil.toList(btArr));
					}
					break;
				case "NIN":
					if(fieldValue instanceof List) {
						List inValue = Convert.toList(fieldValue);
						queryWrapper.notIn(column, inValue);
					} else if(fieldValue instanceof String) {
						String btArr [] = ((String) fieldValue).split(",");
						queryWrapper.notIn(column, CollectionUtil.toList(btArr));
					}
					break;
			}
		}
		// 关键字处理
		if(StrUtil.isNotEmpty(keywords) && StrUtil.isNotEmpty(searchKeys)) {
			queryWrapper.and(new Consumer<QueryWrapper<?>>() {
				@Override
				public void accept(QueryWrapper<?> tQueryWrapper) {
					Arrays.stream(searchKeys.split(",")).forEach(v -> tQueryWrapper.or().like(CharSequenceUtil.toUnderlineCase(v), keywords));
				}
			});
		}
		// 构建排序
		// id desc
		// id asc
		// id desc,code asc
		// id,code asc
		if(StrUtil.isEmpty(orderBy)) return queryWrapper;
		String arr [] = orderBy.split(",");
		for(String s:arr) {
			String orderByList [] = s.split("\\s+");
			if (orderByList.length==1) {
				String column = StrUtil.toUnderlineCase(orderByList[0]);
				if (column.matches(REG_EXP)) {
					queryWrapper.orderByAsc(column);
				}
			} else {
				String column = StrUtil.toUnderlineCase(orderByList[0]);
				if (column.matches(REG_EXP)) {
					if("desc".equalsIgnoreCase(orderByList[1]) || "descend".equalsIgnoreCase(orderByList[1])) {
						queryWrapper.orderByDesc(column);
					} else {
						queryWrapper.orderByAsc(column);
					}
				}
			}
		}
		return queryWrapper;
	}
	public int getPageSize() {
		// 每页大小大于0，小于10000
		if(pageSize == null) {
			pageSize = 10;
		}
		return pageSize<=0?10:pageSize>10000?10000:pageSize;
	}
	private static Dict QUERY_TYPE_MAP = Dict.of("EQ","等于","LIKE","模糊","IN","包含","BT","区间","NE","不等于","GT","大于","GE"
			,"大于等于","LT","小于","LE","小于等于","LLIKE","左模糊","RLIKE","右模糊","NIN","不包含") ;
	/**
	 * 生成高级搜索查询条件
	 * @return
	 */
	public Map<String,List<Dict>> buildQuerySchema() {
		return Arrays.stream(ReflectUtil.getFields(this.getClass())).filter(item->{
			return item.getName().startsWith("m_");
		}).map(field -> fieldToQuerySchema(field)).collect(Collectors.groupingBy(item->{
			String remark = item.getStr("remark");
			String queryFieldName = item.getStr("queryFieldName");
			String arr [] = queryFieldName.split("_");
			String queryType = "EQ";
			if(arr.length == 3) {
				queryType = arr[1];
			} else {
				queryType = arr[2];
			}
			item.set("remark",QUERY_TYPE_MAP.getStr(queryType));
			return remark;
		}));
	}

	/**
	 * 搜索字段转成元数据
	 * @param field
	 * @return
	 */
	private Dict fieldToQuerySchema(Field field) {
		Dict dict = Dict.create();
		ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
		String remark = apiModelProperty.value();
		dict.put("queryFieldName",field.getName());
		dict.put("remark",remark);
		Dict ext  = Dict.create();
		for (int i = 0; i < apiModelProperty.extensions().length; i++) {
			for (int i1 = 0; i1 < apiModelProperty.extensions()[i].properties().length; i1++) {
				ext.put(apiModelProperty.extensions()[i].properties()[i1].name(),apiModelProperty.extensions()[i].properties()[i1].value());
			}
		}
		dict.put("ext",ext);
		return dict;
	}
}
