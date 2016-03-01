package com.tianque.core.struts.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @功能:异常拦截
 * @说明:
 * @作者:王熙斌
 * @创建时间：2012-5-30 下午04:27:40
 **/
public class ExceptionInterceptor extends AbstractInterceptor {

	private final static Logger logger = LoggerFactory
			.getLogger(ExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		try {
			return ai.invoke();
		} catch (Exception e) {
			logger.error("拦截到异常：" + e);
			logger.error(getStackTrace(e));
			throw e;
		} catch (Throwable e) {
			logger.error("拦截到异常：" + e.getCause());
			throw new Exception(e.getCause());
		}

	}

	private String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
}
