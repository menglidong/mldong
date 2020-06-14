package com.mldong.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 这里先简单的日志输出
 * @author mldong
 *
 */
@Component
public class DefaultLoggerStoreImpl implements ILoggerStore {
	private final static Logger LOGGER = LoggerFactory.getLogger(DefaultLoggerStoreImpl.class);
	@Override
	public int save(LoggerModel model) {
		LOGGER.info("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
				model.getTrackId(),
				model.getUri(),
				model.getQueryString(),
				model.getMethod(),
				model.getDescription(),
				model.getIp(),
				model.getBody(),
				model.getToken(),
				model.getUserId(),
				model.getReturnData(),
				model.getStartTime(),
				model.getEndTime());
		return 1;
	}

}
