package com.mldong.generator.engine;

import java.util.Map;

import com.mldong.generator.config.model.TemplateConfigModel;
/**
 * 模板引擎接口
 * @author mldong
 *
 */
public interface TemplateEngine {
	/**
	 * 解析字符串
	 * @param model
	 * @param stringTemplate
	 * @return
	 */
	public String processToString(Map<String, Object> model,
			String stringTemplate);
	/**
	 * 解析文件
	 * @param model
	 * @param templateConfigModel
	 */
	public void processToFile(Map<String, Object> model,
			TemplateConfigModel templateConfigModel);
}
