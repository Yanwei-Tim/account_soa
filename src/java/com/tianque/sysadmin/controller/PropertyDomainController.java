package com.tianque.sysadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.PropertyDomain;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Transactional
@Scope("prototype")
@Controller("propertyDomainController")
public class PropertyDomainController extends BaseAction {
	@Autowired
	private PropertyDomainDubboService propertyDomainDubboService;

	private List<PropertyDomain> propertiesDomain;

	public List<PropertyDomain> getPropertiesDomain() {
		return propertiesDomain;
	}

	public void setPropertiesDomain(List<PropertyDomain> propertiesDomain) {
		this.propertiesDomain = propertiesDomain;
	}

	/**
	 * 查询域属性列表
	 * 
	 * @return SUCCESS
	 */
	public String findPropertyDomain() {
		propertiesDomain = propertyDomainDubboService.findPropertyDomain();
		return SUCCESS;
	}

}
