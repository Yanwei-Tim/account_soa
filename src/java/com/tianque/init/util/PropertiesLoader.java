package com.tianque.init.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	private final static Logger logger = LoggerFactory
			.getLogger(PropertiesLoader.class);
	private static Properties properties = new Properties();

	public static String get(String key) {
		if (properties.isEmpty()) {
			try {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				InputStream is = cl.getResourceAsStream("jdbc.properties");
				properties.load(is);
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}

		}
		return properties.getProperty(key);
	}
}
