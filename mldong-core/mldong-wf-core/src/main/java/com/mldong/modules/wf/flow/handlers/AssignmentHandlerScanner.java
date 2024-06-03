package com.mldong.modules.wf.flow.handlers;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import com.mldong.consts.CommonConstant;
import com.mldong.dict.CustomDictService;
import com.mldong.dict.model.DictItemModel;
import com.mldong.dict.model.DictModel;
import com.mldong.modules.wf.engine.AssignmentHandler;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 扫描获取参与者处理类
 * @author mldong
 *
 */
@Component
public class AssignmentHandlerScanner implements ResourceLoaderAware, CustomDictService {
	private ResourceLoader resourceLoader;

	private ResourcePatternResolver resolver = ResourcePatternUtils
			.getResourcePatternResolver(resourceLoader);
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
			resourceLoader);
	/**
	 * 工作流参与者处理类
	 */
	private List<AssignmentHandler> assignmentHandlerList = new ArrayList<>();
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
					if (ArrayUtil.contains(metadataReader.getClassMetadata().getInterfaceNames(), AssignmentHandler.class.getName())) {// 当类型不是抽象类或接口在添加到集合
                    	Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
						AssignmentHandler assignmentHandler = (AssignmentHandler) ReflectUtil.newInstance(clazz);
						assignmentHandlerList.add(assignmentHandler);
						CollectionUtil.sort(assignmentHandlerList, Comparator.comparing(AssignmentHandler::getOrder));
                    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("字典扫描失败");
		}
	}
	private DictModel dictModel;

	@Override
	public DictModel getDictModel() {
		if(dictModel!=null) return dictModel;
		dictModel = new DictModel();
		dictModel.setName("AssignmentHandlerScanner|工作流参与者处理类");
		dictModel.setDictKey("wf_assignment_handler");
		List<DictItemModel> items = new ArrayList<>();
		dictModel.setItems(items);
		assignmentHandlerList.sort(Comparator.comparingInt(AssignmentHandler::getOrder));
		assignmentHandlerList.forEach(item->{
			DictItemModel dictItem = new DictItemModel();
			dictItem.setName(item.getMessage());
			dictItem.setDictItemKey(item.getClass().getName());
			dictItem.setDictItemValue(item.getClass().getName());
			items.add(dictItem);
		});
		return dictModel;
	}

	/**
	 * 获取枚举字典
	 * @return
	 */

	public List<AssignmentHandler> getAssignmentHandlerList() {
		return assignmentHandlerList;
	}

	@Override
	public DictModel getByDictKey(Map<String, Object> args) {
		if ("wf_assignment_handler".equalsIgnoreCase(Convert.toStr(args.get("dictType")))){
			return getDictModel();
		}
		return null;
	}
}
