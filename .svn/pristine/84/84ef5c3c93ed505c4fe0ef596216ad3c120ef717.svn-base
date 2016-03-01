package com.tianque.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GlobalValue;

public class AppInitialization {

	private static Logger logger = LoggerFactory
			.getLogger(AppInitialization.class);

	public static void main(String[] args) throws Exception {
		try {
			GlobalValue.isInitApp = true;
			if (args.length > 0 && args[0].toLowerCase().equals("development")) {
				developmentMode();
			} else if (args.length > 0
					&& args[0].toLowerCase().equals("production")) {
				productionMode();
			}
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private static void productionMode() throws Exception {
		new ProductionEnvironmentBuilder().builderTestEnv();
	}

	private static void developmentMode() throws Exception {
		new DevelopmentEnvironmentBuilder().builderTestEnv();
	}

}
