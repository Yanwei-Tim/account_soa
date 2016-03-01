package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.logger.Logger;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.logger.Logable;


public abstract class LogableService extends AbstractBaseService implements Logable,Logger,ModuleConstants{
	@Autowired
	private SystemLogService systemLogService;

	@Override
	public void log(int logLevel, String moduleName, String operation,Integer operationType,
			String operateContent) {
		systemLogService.log(logLevel, moduleName,operationType, operation, operateContent);
	}

}
