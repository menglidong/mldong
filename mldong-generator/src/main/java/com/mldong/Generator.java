package com.mldong;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mldong.generator.config.GeneratorConfig;
import com.mldong.generator.config.model.TableConfigModel;
import com.mldong.generator.config.model.TemplateConfigModel;
import com.mldong.generator.core.DataBase;
import com.mldong.generator.core.impl.MysqlDataBase;
import com.mldong.generator.core.model.Table;
import com.mldong.generator.engine.FreeMarkerImpl;
import com.mldong.generator.engine.TemplateEngine;

/**
 * 代码生成主函数
 * @author mldong
 *
 */
public class Generator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);
	public static void main(String[] args) throws FileNotFoundException {
		String path = Generator.class.getResource("/").getPath();
        String configPath = path+"config.yml";
        String dataTypePath = path+ "dataType.yml";
        String templateDir = path + "/templates";
		GeneratorConfig config = new GeneratorConfig(configPath, dataTypePath);
		// 加载配置
	    config.loadConfig();
		DataBase dataBase = new MysqlDataBase(config);
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Gson gson = new Gson();
		TemplateEngine templateEngine = new FreeMarkerImpl(templateDir);
		// 要生成的表
		List<TableConfigModel> tableConfigModelList = Arrays.asList(config.getConfigModel().getTables());
		// 模板集合
		List<TemplateConfigModel> templateConfigModelList = Arrays.asList(config.getConfigModel().getTemplates());
		tableConfigModelList.forEach(tableConfigModel -> {
			// 获取表
			List<Table> tableList = dataBase.getTables(tableConfigModel.getTableName());
			tableList.forEach(table -> {
				LOGGER.info("元数据:{}",gson.toJson(table));
				Map<String, Object> model = new HashMap<String, Object>();
				String targetProject = config.getConfigModel().getTargetProject();
				model.put("targetProject", targetProject);
				model.put("basePackage", config.getConfigModel().getBasePackage());
				model.put("moduleName", config.getConfigModel().getModuleName());
				model.put("moduleDesc", config.getConfigModel().getModuleDesc());
				model.put("table", table);
				templateConfigModelList.forEach(templateConfigModel -> {
					// 选中的才能生成代码
					if(templateConfigModel.isSelected()) {
						if(!templateConfigModel.getTargetPath().contains(targetProject)){
							templateConfigModel.setTargetPath(targetProject+File.separator+templateConfigModel.getTargetPath());
						}
						templateEngine.processToFile(model, templateConfigModel);
					}
				});
			});
		});
	}
}
