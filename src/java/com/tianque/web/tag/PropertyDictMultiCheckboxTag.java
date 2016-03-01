package com.tianque.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;
import com.tianque.web.tag.component.PropertyDictMultiCheckboxComponent;

public class PropertyDictMultiCheckboxTag extends ComponentTagSupport {
	
	private String domainName;
	
	private String className;
	
//	private String valueExpress;
	
	private int column=-1;
	
	private String textStyle;
	
	private boolean readOnly=false; 

	private String name;

	private List listVal;
	
	public List getListVal() {
		return listVal;
	}
	public void setListVal(List listVal) {
		this.listVal = listVal;
	}
	
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

//	public String getValueExpress() {
//		return valueExpress;
//	}
//
//	public void setValueExpress(String valueExpress) {
//		this.valueExpress = valueExpress;
//	}

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

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		PropertyDictMultiCheckboxComponent component=new PropertyDictMultiCheckboxComponent(stack);
		component.setColumn(column);
		component.setDomainName(domainName);
		component.setReadOnly(readOnly);
		component.setTextStyle(textStyle);
		component.setListVal(listVal);
		component.setName(name);
		component.setClassName(className);
//		this.setPageContext(pageContext)
//		component.set
		return component;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}
