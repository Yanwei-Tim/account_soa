package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.domain.PropertyDomain;


public interface PropertyDomainDubboService {
	/**
	 * 新增域属性
	 * 
	 * @param propertyDomain
	 *            域属性对象
	 * @return 域属性对象
	 */
	public PropertyDomain addPropertyDomain(PropertyDomain propertyDomain);

	/**
	 * 查询域属性列表
	 * 
	 * @return 域属性对象列表
	 */
	public List<PropertyDomain> findPropertyDomain();

	/**
	 * 根据域属性ID查询域属性
	 * 
	 * @param id
	 *            域属性ID
	 * @return 域属性对象
	 */
	public PropertyDomain getPropertyDomainById(Long id);

	public PropertyDomain getPropertyDomainByDomainName(String domainName);

	public List<PropertyDomain> findPropertyDomainBydomainName(
			String domainName, int pageNum, int pageSize);
}
