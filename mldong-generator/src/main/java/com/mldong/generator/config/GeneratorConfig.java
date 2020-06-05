package com.mldong.generator.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.ho.yaml.Yaml;

import com.mldong.generator.config.model.ConfigModel;
/**
 * 配置文件处理类
 * @author mldong
 *
 */
public class GeneratorConfig {
	/**
	 * 默认的代码生成配置文件名
	 */
	private static final String CONFIG_FILE = "config.yml";
	/**
	 * 默认的数据类型配置文件名
	 */
	private static final String DATA_TYPE_FILE = "dataType.yml";
	/**
	 * 配置模型(config.yml)
	 */
	private ConfigModel configModel;
	/**
	 * 代码生成配置文件
	 */
	private String configPath;
	/**
	 * 数据类型配置文件
	 */
	private String dataTypePath;
	/**
	 * 类型转换(dataType.yml)
	 */
	private Map<String,Map<String,String>> dataType;
	public GeneratorConfig(String configPath,String dataTypePath){
		File configFile = new File(configPath);
		if(configFile.isDirectory()) {
			this.configPath = configPath+CONFIG_FILE;
		} else {
			this.configPath = configPath;
		}
		File dataTypeFile = new File(dataTypePath);
		if(dataTypeFile.isDirectory()) {
			this.dataTypePath = configPath+DATA_TYPE_FILE;
		} else {
			this.dataTypePath = dataTypePath;
		}
	}
	/**
	 * 加载配置文件
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void loadConfig() throws FileNotFoundException {
		File dataTypeFile = new File(dataTypePath);
		this.dataType = Yaml.loadType(dataTypeFile,HashMap.class);
		File configFile = new File(configPath);
        this.configModel = Yaml.loadType(configFile, ConfigModel.class);
	}

	public Map<String, Map<String, String>> getDataType() {
		return dataType;
	}
	public ConfigModel getConfigModel() {
		return configModel;
	}
	
}
