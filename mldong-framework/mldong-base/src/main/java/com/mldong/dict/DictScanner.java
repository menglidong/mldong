package com.mldong.dict;


import cn.hutool.core.util.StrUtil;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.dict.model.DictItemModel;
import com.mldong.dict.model.DictModel;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 扫描自定义字典枚举处理类
 * @author mldong
 *
 */
@Component
public class DictScanner implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;

	private ResourcePatternResolver resolver = ResourcePatternUtils
			.getResourcePatternResolver(resourceLoader);
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
			resourceLoader);
	/**
	 * 字典列表，供外部页面查看使用
	 */
	private List<DictModel> dictList = new ArrayList<DictModel>();
	/**
	 * 字典hash,方便 getDictKey获取
	 */
	private Map<String,DictModel> dictMap = new HashMap<>();
	private static final String FULLTEXT_SACN_PACKAGE_PATH = CommonConstant.DEFAULT_PACKAGE_NAME;
	@PostConstruct
	private void init() {
		doScan();
	}
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	private void doScan() {
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				.concat(ClassUtils
						.convertClassNameToResourcePath(
								SystemPropertyUtils
										.resolvePlaceholders(FULLTEXT_SACN_PACKAGE_PATH))
						.concat("/**/*.class"));
		try {
			Resource[] resources = resolver.getResources(packageSearchPath);
			MetadataReader metadataReader = null;
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					metadataReader = metadataReaderFactory
							.getMetadataReader(resource);
					if (metadataReader.getClassMetadata().isFinal()) {// 当类型不是抽象类或接口在添加到集合
                    	Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                    	DictEnum enumCode = clazz.getAnnotation(DictEnum.class);
                    	if(null != enumCode) {
                    		DictModel model = handleDictEnum(clazz, enumCode);
                    		dictList.add(model);
                    		dictMap.put(model.getDictKey(), model);
                    	}
                    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("字典扫描失败");
		}
	}
	/**
	 * 处理字典枚举类
	 * @param clazz
	 * @param enumCode
	 * @return
	 */
	private DictModel handleDictEnum(Class<?> clazz, DictEnum enumCode) {
		DictModel model = new DictModel();
		model.setName(enumCode.name());
		model.setDictKey(enumCode.key());
		List<DictItemModel> items = new ArrayList<>();
		model.setItems(items);
		Arrays.stream(clazz.getEnumConstants()).forEach(item->{
			DictItemModel dictItem = new DictItemModel();
			CodedEnum codedEnum = (CodedEnum)item;
			dictItem.setName(codedEnum.getMessage());
			dictItem.setDictItemKey(codedEnum.toString());
			if(codedEnum.getDataType().equals(String.class)) {
				dictItem.setDictItemValue(StrUtil.toString(codedEnum.getCode()));
			} else {
				dictItem.setDictItemValue(codedEnum.getCode());
			}
			items.add(dictItem);
		});
		return model;
	}
	public List<DictModel> getDictList() {
		return dictList;
	}
	public Map<String, DictModel> getDictMap() {
		return dictMap;
	}

}
