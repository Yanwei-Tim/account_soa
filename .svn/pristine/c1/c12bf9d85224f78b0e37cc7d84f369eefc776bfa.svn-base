package com.tianque.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.property.GridInternalProperty;

/**
 * 系统属性元信息， 展示系统维护哪些属性 如：政治面貌，教育程度，血型等
 */
@SuppressWarnings("serial")
public class PropertyDomain extends BaseDomain {
	/**
	 * 属性领域名称
	 */
	private String domainName;
	/**
	 * 系统敏感，相应属性是否具有系统内置的属性支持
	 */
	private boolean systemSensitive;
	/**
	 * 只有设置系统敏感性，该属性才起作用 表示对相关属性的约束 使用Json序列化GridInternalProperty的List
	 */
	private String systemRestrict;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public boolean isSystemSensitive() {
		return systemSensitive;
	}

	public void setSystemSensitive(boolean systemSensitive) {
		this.systemSensitive = systemSensitive;
	}

	public String getSystemRestrict() {
		return systemRestrict;
	}

	public void setSystemRestrict(String systemRestrict) {
		this.systemRestrict = systemRestrict;
	}

	public List<GridInternalProperty> getInternaleProperties() {
		if (this.systemRestrict == null
				|| "".equals(this.systemRestrict.trim())) {
			return null;
		}
		List<GridInternalProperty> result = new ArrayList<GridInternalProperty>();
		JSONArray jsonArray = JSONArray.fromObject(this.systemRestrict);
		for (int i = 0; i < jsonArray.size(); i++) {
			Object o = jsonArray.get(i);
			JSONObject jsonObject = JSONObject.fromObject(o);
			GridInternalProperty property = (GridInternalProperty) JSONObject
					.toBean(jsonObject, GridInternalProperty.class);
			result.add(property);
		}
		return result;
	}

	public void setInternaleProperties(List<GridInternalProperty> properties) {
		if (properties == null || properties.size() == 0) {
			this.systemRestrict = null;
			return;
		}
		JSONArray jsonArray = JSONArray.fromObject(properties);
		this.systemRestrict = jsonArray.toString();
	}
}
