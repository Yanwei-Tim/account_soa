package com.tianque.core.struts.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimeCostInterceptor extends AbstractInterceptor{
	public final static Logger logger = LoggerFactory.getLogger(TimeCostInterceptor.class);
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		long startTime=System.currentTimeMillis();
		String result=invocation.invoke();
		long endTime=System.currentTimeMillis();
		logger.debug("耗时："+(endTime-startTime));
		return result;
	}

}
