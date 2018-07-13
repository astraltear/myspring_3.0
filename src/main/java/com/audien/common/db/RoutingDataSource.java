package com.audien.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
	private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);
	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("RoutingDataSource.determineCurrentLookupKey:"+ContextHolder.getDataSourceType());
		return ContextHolder.getDataSourceType();
	}

}
