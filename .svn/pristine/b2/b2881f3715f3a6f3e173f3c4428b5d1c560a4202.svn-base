package com.tianque.web.tag.component;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.tianque.domain.PropertyDict;
import com.tianque.userAuth.api.PropertyDictDubboService;

public class PropertyDictMultiCheckboxComponent extends Component {
	private static Logger logger = LoggerFactory
			.getLogger(PropertyDictMultiCheckboxComponent.class);

	private String domainName;

	//	private String valueExpress;

	private List listVal;

	private int column = -1;

	private String textStyle;

	private boolean readOnly = false;

	private String name;
	private String className;

	private List<Long> propertyDictIds = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public List getListVal() {
		return listVal;
	}

	public void setListVal(List listVal) {
		this.listVal = listVal;
	}

	public PropertyDictMultiCheckboxComponent(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			writer.write("<ul class='propertyMulSelect'>");
			List<PropertyDict> dicts = loadPropertyDictByName();
			String widthString = columnWidthPercentString();
			for (int index = 0; index < dicts.size(); index++) {
				PropertyDict dict = dicts.get(index);
				writer.write("<li style='width:" + widthString
						+ "'><input id='" + name + "[" + index + "]' class=\""
						+ className + "\" name='" + name + "' type='checkbox' "
						+ readOnlyString() + checkedStatusString(dict)
						+ "value=" + dict.getId() + " />"
						+ dict.getDisplayName() + "</li>");
			}
			writer.write("</ul>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String checkedStatusString(PropertyDict dict) {
		return containValue(dict) ? " checked " : " ";
	}

	private String readOnlyString() {
		return readOnly ? " disabled " : " ";
	}

	private boolean containValue(PropertyDict dict) {
		convertValue2LongListIfNecc();
		return propertyDictIds.contains(dict.getId());
	}

	private void convertValue2LongListIfNecc() {
		if (propertyDictIds != null)
			return;
		propertyDictIds = new ArrayList<Long>();
		if (listVal != null) {
			for (int index = 0; index < listVal.size(); index++) {
				Object val = listVal.get(index);
				if (Long.class.isAssignableFrom(val.getClass())
						|| Integer.class.isAssignableFrom(val.getClass())) {
					propertyDictIds.add((Long) val);
				} else if (PropertyDict.class.isAssignableFrom(val.getClass())) {
					propertyDictIds.add(((PropertyDict) val).getId());
				}
			}
		}
	}

	private String columnWidthPercentString() {
		return column == -1 ? "" : String.valueOf(100 / column) + "%";
	}

	private List<PropertyDict> loadPropertyDictByName() {
		String actualDomainName = getActualPropertyDomainName();
		return getPropertyDictService().findPropertyDictByDomainName(
				actualDomainName);
	}

	private PropertyDictDubboService getPropertyDictService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());

		PropertyDictDubboService propertyDictDubboService = (PropertyDictDubboService) applicationContext
				.getBean("propertyDictDubboService");
		return propertyDictDubboService;
	}

	private String getActualPropertyDomainName() {
		String result = domainName;
		if (domainIsOgnlConstantExpress()) {
			try {
				Object value = Ognl.getValue(domainName, null);
				if (value != null)
					result = value.toString();
			} catch (OgnlException e) {
				logger.error("异常信息", e);
			}
		}
		return result;
	}

	private boolean domainIsOgnlConstantExpress() {
		return domainName.startsWith("@");
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
