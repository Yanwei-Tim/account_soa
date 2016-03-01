package com.tianque.core.struts.interceptor;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.ThreadVariable;

public class WhiteListInterceptor extends AbstractInterceptor {

	private static Logger logger = LoggerFactory
			.getLogger(WhiteListInterceptor.class);

	private static Properties properties = null;

	static {
		if (null == properties) {
			properties = new Properties();
			// URL url = WhiteListInterceptor.class.getClass().getClassLoader()
			// .getSystemResource("whiteList.properties");
			// System.out.println(url.getPath());
			try {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				InputStream is = cl.getResourceAsStream("whiteList.properties");
				properties.load(is);
			} catch (Exception e) {
				logger.error("加载whiteList.properties出错！" + e.toString());
				e.printStackTrace();
			}
		}
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		try {
			if (ThreadVariable.getOrganization() == null
					|| ThreadVariable.getUser() == null) {
				return ai.invoke();
			}

			CacheService cacheService = getCacheService();

			String value = getValueFromProperties(ai);
			if (null == value) {
				return ai.invoke();
			}

			String key = new StringBuffer(ai.getProxy().getNamespace()).append(
					"/").append(ai.getProxy().getActionName())
					.append(".action").append("+").append(
							ThreadVariable.getUser().getId()).toString();

			Long lastOperateDate = (Long) cacheService.get(key);
			if (null != lastOperateDate
					&& (Calendar.getInstance().getTimeInMillis() - lastOperateDate
							.longValue()) < Long.valueOf(value).longValue()) {
				return null;
			}

			cacheService.set(key, 60000, Calendar.getInstance()
					.getTimeInMillis());

			return ai.invoke();
		} catch (Exception e) {
			logger.error("WhiteListInterceptor出错", e);
			return null;
		}
	}

	private CacheService getCacheService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());
		CacheService cacheService = (CacheService) applicationContext
				.getBean("cacheService");
		return cacheService;
	}

	private String getValueFromProperties(ActionInvocation ai) {
		return properties.getProperty(new StringBuffer(ai.getProxy()
				.getNamespace()).append("/").append(
				ai.getProxy().getActionName()).append(".action").append(",")
				.append(ThreadVariable.getOrganization().getOrgLevel()
						.getInternalId()).toString());
	}

}
