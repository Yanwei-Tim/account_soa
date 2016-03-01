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
 * 下拉框的自定义标签v1.4
 * 
 * @author lianjing.bao
 */
/**
 * demo: '<select name="domain.gender.id" id="gender" class="form-txt">
 * <pop:OptionTag name="性别" defaultValue="${domain.gender.id}"/> </select>'
 */
public class PropertyDictSelectTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(PropertyDictSelectTag.class);
	private static final String EMPTY_OPTION_TEMPLATE = "<option value=''></option>";
	private static final String SELECTED_OPTION_TEMPLATE = "<option value='%s' selected>%s</option>";
	private static final String OPTION_TEMPLATE = "<option value='%s'>%s</option>";

	private boolean havePlease = false;
	private String id = "";
	private String cls = "";
	private String afterChange = "";
	/**
	 * label 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'label'的值取出来，
	 * 放到<option >PropertyDict.get'label'</option>中.
	 */
	private String label = "displayName";
	/**
	 * 对应PropertyDomain表中的domainName.
	 */
	private String domainName = "";

	private String name = "";
	/**
	 * 判断是否允许有空的选项存在.
	 */
	private boolean notNull = false;
	/**
	 * 默认选中的哪一个选项.
	 */
	private String defaultValue = "";

	public int doStartTag() throws JspException {
		if (domainName == null || domainName.length() == 0) {
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		PropertyDictDubboService propertyDictDubboService = getPropertyDictService();

		String propertyType = getPropertyTypeByExpression();
		List<PropertyDict> PropertyDict = propertyDictDubboService
				.findPropertyDictByDomainName(propertyType);

		toHTML(PropertyDict);
		return Tag.EVAL_PAGE;
	}

	/**
	 * 将所有的Code转换成Option
	 * 
	 * @param codes
	 */
	public void toHTML(List<PropertyDict> propertyDicts) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer(50 * propertyDicts.size());
		// 放置一个空选项
		if ("".equals(cls)) {
			cls = "form-select";
		}
		sb.append("<select name='" + name + "' id='" + id + "' class='" + cls
				+ "'>");
		if (!notNull)
			sb.append(EMPTY_OPTION_TEMPLATE);
		try {
			// 根据分析得到需要选中选项的值
			for (PropertyDict propertyDict : propertyDicts) {
				if (defaultValue.equals(propertyDict.getId().toString())) {
					sb.append(String.format(SELECTED_OPTION_TEMPLATE,
							propertyDict.getId(), propertyDict.getDisplayName()
									+ "," + propertyDict.getFullPinyin() + ","
									+ propertyDict.getSimplePinyin()));
				} else {
					sb.append(String.format(OPTION_TEMPLATE,
							propertyDict.getId(), propertyDict.getDisplayName()
									+ "," + propertyDict.getFullPinyin() + ","
									+ propertyDict.getSimplePinyin()));
				}
			}
			sb.append("</select>");
			sb.append("<script>");
			sb.append("$(document).ready(function(){");
			sb.append("$('#" + id + "').combobox(");
			if (!"".equals(afterChange)) {
				sb.append(
						"{'afterChange':function(id){if(" + afterChange
								+ "!=undefined){" + afterChange
								+ "(id);}},isHavePlease:").append(havePlease)
						.append("}");
			}
			sb.append(");");
			sb.append("});");
			sb.append("</script>");
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private PropertyDictDubboService getPropertyDictService() {
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
	private String getPropertyTypeByExpression() {
		String result = domainName;
		try {
			OgnlContext context = new OgnlContext();
			Object parseExpression = Ognl.parseExpression(domainName);
			Object value = Ognl.getValue(parseExpression, context);
			if (value != null)
				result = value.toString();
		} catch (OgnlException e) {
			logger.error("异常信息", e);
		}
		return result;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getAfterChange() {
		return afterChange;
	}

	public void setAfterChange(String afterChange) {
		this.afterChange = afterChange;
	}

	public boolean isHavePlease() {
		return havePlease;
	}

	public void setHavePlease(boolean havePlease) {
		this.havePlease = havePlease;
	}

}
