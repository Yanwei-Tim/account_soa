package com.tianque.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * <pop:PropertyDictViewTag name="性别" defaultValue="${domain.gender.id}"/>
 * </select>'
 */
public class PropertyDictViewTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(PropertyDictViewTag.class);
	/**
	 * 对应PropertyDomain表中的domainName.
	 */
	private String name = "";

	/**
	 * 默认值
	 */
	private String defaultValue = "";

	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		PropertyDictDubboService propertyDictDubboService = getPropertyDictDubboService();

		String propertyType = getValueByExpression(name);
		List<PropertyDict> propertyDicts = propertyDictDubboService
				.findPropertyDictByDomainName(propertyType);
		toHTML(propertyDicts);

		return Tag.EVAL_PAGE;
	}

	private void toHTML(List<PropertyDict> propertyDicts) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer(50);
		try {
			// 根据分析得到需要选中选项的值
			for (PropertyDict propertyDict : propertyDicts) {
				if (defaultValue.equals(propertyDict.getId().toString())) {
					sb.append(propertyDict.getDisplayName());
				}
			}
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private PropertyDictDubboService getPropertyDictDubboService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());

		PropertyDictDubboService propertyDictDubboService = (PropertyDictDubboService) applicationContext
				.getBean("propertyDictDubboService");
		return propertyDictDubboService;
	}

	/**
	 * 即保留对原来写类别中文名的支持，又支持OGNL表达式写法.
	 * 
	 * @return
	 */
	private String getValueByExpression(String name) {
		String result = name;
		try {
			OgnlContext context = new OgnlContext();
			Object parseExpression = Ognl.parseExpression(name);
			Object value = Ognl.getValue(parseExpression, context);
			if (value != null)
				result = value.toString();
		} catch (OgnlException e) {
			logger.error("异常信息", e);
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
