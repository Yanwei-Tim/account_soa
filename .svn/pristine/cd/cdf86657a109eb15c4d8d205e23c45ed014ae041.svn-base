package com.tianque.core.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.globalSetting.service.GlobalSettingService;

public class GetGlobalSettingValueTag extends TagSupport {
	
	private static Logger logger = LoggerFactory.getLogger(GetGlobalSettingValueTag.class);
	
	private GlobalSettingService globalSettingService;
	
	private String key;
	
	public int doStartTag() throws JspException {
		try {
			if(null==globalSettingService)
				this.pageContext.getOut().print(getGlobalSettingService().getGlobalValue(getValueByExpression(key)));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}
	
	private GlobalSettingService getGlobalSettingService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());

		GlobalSettingService globalSettingService = (GlobalSettingService) applicationContext
				.getBean("globalSettingService");
		return globalSettingService;
	}

	private String getValueByExpression(String name) {
		String result = name;
		try {
			OgnlContext context = new OgnlContext();
			Object parseExpression = Ognl.parseExpression(name);
			Object value=Ognl.getValue(parseExpression, context);
			if(value!=null)
				result=value.toString();
		} catch (OgnlException e) {
			logger.error("异常信息", e);
		}
		return result;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
