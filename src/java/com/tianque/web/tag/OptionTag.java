package com.tianque.web.tag;

import java.util.Arrays;
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
public class OptionTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(OptionTag.class);
	private static final long serialVersionUID = 5805328009491675988L;

	private static final String EMPTY_OPTION_TEMPLATE = "<option value='' id=''>请选择</option>";
	private static final String SELECTED_OPTION_TEMPLATE = "<option value='%s' id='%s' internalId='%s' selected>%s</option>";
	private static final String OPTION_TEMPLATE = "<option value='%s' id='%s' internalId='%s'>%s</option>";
	/**
	 * key 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'key'的值取出来， 放到<option
	 * value="PropertyDict.get'key'">中.
	 */
	private String key = "id";
	/**
	 * label 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'label'的值取出来，
	 * 放到<option >PropertyDict.get'label'</option>中.
	 */
	private String label = "displayName";
	/**
	 * 对应PropertyDomain表中的domainName.
	 */
	private String name = "";
	/**
	 * 判断是否允许有空的选项存在.
	 */
	private boolean notNull = false;
	/**
	 * 默认选中的哪一个选项.
	 */
	private String defaultValue = "";
	private boolean showInternalId = false;

	/**
	 * 需要的选项InternalId
	 */
	private String exceptInternalIds = "";

	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		PropertyDictDubboService propertyDictDubboService = getPropertyDictService();

		String propertyType = getValueByExpression(name);
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
		String[] array = exceptInternalIds.split(",");
		if (!"".equals(exceptInternalIds)) {
			for (int i = 0; i < array.length; i++) {
				array[i] = getValueByExpression(array[i]);
			}
		}
		List<String> internalIdList = Arrays.asList(array);
		StringBuffer sb = new StringBuffer(50 * propertyDicts.size());
		// 放置一个空选项
		if (!notNull)
			sb.append(EMPTY_OPTION_TEMPLATE);
		try {
			// 根据分析得到需要选中选项的值
			for (PropertyDict propertyDict : propertyDicts) {
				if (!internalIdList.contains(propertyDict.getInternalId() + "")) {
					if (defaultValue.equals(propertyDict.getId().toString())) {
						if (showInternalId) {
							sb.append(String.format(SELECTED_OPTION_TEMPLATE,
									propertyDict.getId(), propertyDict.getId(),
									propertyDict.getInternalId(),
									propertyDict.getDisplayName()));
						} else {
							sb.append(String.format(SELECTED_OPTION_TEMPLATE,
									propertyDict.getId(), propertyDict.getId(),
									"", propertyDict.getDisplayName()));
						}
					} else {
						if (showInternalId) {
							sb.append(String.format(OPTION_TEMPLATE,
									propertyDict.getId(), propertyDict.getId(),
									propertyDict.getInternalId(),
									propertyDict.getDisplayName()));
						} else {
							sb.append(String.format(OPTION_TEMPLATE,
									propertyDict.getId(), propertyDict.getId(),
									"", propertyDict.getDisplayName()));
						}
					}
				}
			}
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public boolean isShowInternalId() {
		return showInternalId;
	}

	public void setShowInternalId(boolean showInternalId) {
		this.showInternalId = showInternalId;
	}

	public String getExceptInternalIds() {
		return exceptInternalIds;
	}

	public void setExceptInternalIds(String exceptInternalIds) {
		this.exceptInternalIds = exceptInternalIds;
	}

}
