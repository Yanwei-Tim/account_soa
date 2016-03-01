package com.tianque.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.ThreadVariable;

abstract public class AbstractBaseService {
	public final static Logger logger = LoggerFactory.getLogger(AbstractBaseService.class);

	public Object getCurrentUser() {
		return ThreadVariable.getSession();
	}

}
