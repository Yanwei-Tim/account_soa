package com.tianque.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.property.GridInternalProperty;

@SuppressWarnings("serial")
public class IssueTypeDomain  extends BaseDomain{
	private String domainName;
	
	private boolean systemSensitive;
	
	private String systemRestrict;
	
	private String module;
	
	private boolean personalized;

	public boolean isPersonalized() {
		return personalized;
	}

	public void setPersonalized(boolean personalized) {
		this.personalized = personalized;
	}

	public boolean isSystemSensitive() {
		return systemSensitive;
	}

	public void setSystemSensitive(boolean systemSensitive) {
		this.systemSensitive = systemSensitive;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<GridInternalProperty> getInternaleProperties(){
		if(this.systemRestrict == null || "".equals(this.systemRestrict.trim())){
			return null;
		}
		List<GridInternalProperty> result = new ArrayList<GridInternalProperty>();
		JSONArray jsonArray = JSONArray.fromObject(this.systemRestrict);
		for (int i = 0; i < jsonArray.size(); i++) {
			   Object o = jsonArray.get(i);
			   JSONObject jsonObject = JSONObject.fromObject(o);
			   GridInternalProperty property = (GridInternalProperty) JSONObject.toBean(jsonObject, GridInternalProperty.class);
			   result.add(property);
		}
		return result;
	}
	
	public void setInternaleProperties(List<GridInternalProperty> properties){
		if(properties==null || properties.size()==0){
			this.systemRestrict = null;
			return ;
		}
		JSONArray jsonArray  = JSONArray.fromObject(properties);
		this.systemRestrict = jsonArray.toString();
	}
}
