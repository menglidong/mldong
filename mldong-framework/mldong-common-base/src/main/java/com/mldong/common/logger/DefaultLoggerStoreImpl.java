package com.mldong.common.logger;

import com.mldong.common.config.GlobalProperties;
import com.mldong.common.tool.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 这里先简单的日志输出
 * @author mldong
 *
 */
@Component
@Order(-1)
public class DefaultLoggerStoreImpl implements ILoggerStore {
	@Autowired
	private GlobalProperties properties;
	private final static Logger LOGGER = LoggerFactory.getLogger(DefaultLoggerStoreImpl.class);
	@Override
	public int save(LoggerModel model) {
		if(properties.isOpenSensitive()) {
			properties.getSensitiveKeys().forEach(key -> {
				if(StringTool.isNotEmpty(model.getBody())) {
					model.setBody(model.getBody().replaceAll("(" + key + "=)([^&]+)", "$1*****"));
					model.setBody(model.getBody().replaceAll("(\""+key+"\":\\s*\")([^\"]+)", "$1*****"));
				}
				if(StringTool.isNotEmpty(model.getQueryString())) {
					model.setQueryString(model.getQueryString().replaceAll("(" + key + "=)([^&]+)", "$1*****"));
				}
			});
		}
		LOGGER.info("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
				model.getTrackId(),
				model.getUri(),
				model.getQueryString(),
				model.getMethod(),
				model.getDescription(),
				model.getIp(),
				model.getBody(),
				model.getToken(),
				model.getUserId(),
				model.getUserName(),
				model.getExt()!=null?model.getExt().toString():"",
				model.getReturnData(),
				model.getStartTime(),
				model.getEndTime());
		return 1;
	}

}
