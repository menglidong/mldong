package com.mldong.generator.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mldong.common.enums.ErrorEnum;
import com.mldong.common.exception.GeneratorException;
import com.mldong.common.util.StringUtil;
import com.mldong.generator.config.model.TemplateConfigModel;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 模板引擎解析-freemark实现
 * 
 * @author mldong
 *
 */
public class FreeMarkerImpl implements TemplateEngine {
	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerImpl.class);
	private static final String DEFAULT_ENCODING = "UTF-8";

	private Configuration config;

	private String classPath;

	public FreeMarkerImpl(String classPath) {
		this.classPath = classPath;
		initConfiguration();
	}

	public void initConfiguration() {
		try {
			try {
	            config = new Configuration(Configuration.VERSION_2_3_28);
	            config.setDirectoryForTemplateLoading(new File(classPath));
	            config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

	            config.setSetting("classic_compatible", "true");
	            config.setSetting("whitespace_stripping", "true");
	            config.setSetting("template_update_delay", "1");
	            config.setSetting("locale", "zh_CN");
	            config.setSetting("default_encoding", DEFAULT_ENCODING);
	            config.setSetting("url_escaping_charset", DEFAULT_ENCODING);
	            config.setSetting("datetime_format", "yyyy-MM-dd hh:mm:ss");
	            config.setSetting("date_format", "yyyy-MM-dd");
	            config.setSetting("time_format", "HH:mm:ss");
	            config.setSetting("number_format", "0.######;");
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public String processToString(Map<String, Object> model,
			String stringTemplate) {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
			cfg.setTemplateLoader(new StringTemplateLoader(stringTemplate));
			cfg.setDefaultEncoding(DEFAULT_ENCODING);

			Template template = cfg.getTemplate("");
			StringWriter writer = new StringWriter();
			template.process(model, writer);
			return writer.toString();
		} catch (Exception e) {
			 e.printStackTrace();
			throw new GeneratorException(ErrorEnum.templateEngineProcess);
		}
	}

	@Override
	public void processToFile(Map<String, Object> model,
			TemplateConfigModel templateConfigModel) {
		try {
			Template template = config.getTemplate(
					templateConfigModel.getTemplateFile(),
					templateConfigModel.getEncoding());
			String targetPath = StringUtil
					.packagePathToFilePath(processToString(model,
							templateConfigModel.getTargetPath()));
			String targetFileName = processToString(model,
					templateConfigModel.getTargetFileName());
			File file = new File(targetPath + File.separator + targetFileName);
			// 文件存在且可覆盖 or 文件不存在==>代码生成
			boolean isFileExists = file.exists();
			if ((isFileExists && templateConfigModel.isCovered())
					|| !isFileExists) {
				File directory = new File(targetPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				Writer out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file),
						templateConfigModel.getEncoding()));
				template.process(model, out);
				out.flush();
				out.close();
				if(isFileExists) {
					LOGGER.info("目标文件已重新生成覆盖:{}",file.getPath());
				} else {
					LOGGER.info("目标文件新生成:{}",file.getPath());
				}
			} else {
				LOGGER.info("目标文件已存在，如需要重新生成，请先删除目标文件:{}",file.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneratorException(ErrorEnum.templateEngineProcess);
		}
	}

}
