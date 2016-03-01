package com.tianque.core.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GisProperties {
	private static Logger logger = LoggerFactory.getLogger(GisProperties.class);
	private static Properties properties = null;

	private static Properties getGridProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(GisProperties.class.getClassLoader().getResourceAsStream("gis.properties"));
			} catch (IOException e) {
				logger.error("加载gis.properties出错！");
			}
		}
		return properties;
	}

	public static final String GIS_INDEX_MAP = getGridProperties().getProperty("gis.indexmap");
	
	public static final String  GIS_BOUND_BUILD_DATA=getGridProperties().getProperty("gis.boundBuildData");
}
