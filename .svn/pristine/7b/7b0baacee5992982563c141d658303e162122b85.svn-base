package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.domain.PropertyDict;
import com.tianque.mobile.vo.MobilePropertyDict;
import com.tianque.sysadmin.service.impl.ReferType;


public interface PropertyDictDubboService {
	/**
	 * @description 根据ID查询系统属性字典.
	 * @param id
	 * @return.
	 */
	public List<PropertyDict> findPropertyDictByIds(Long[] id);

	/**
	 * 新增系统属性字典
	 * 
	 * @param propertyDict
	 *            系统属性字典对象
	 * @return 系统属性字典对象
	 */
	public PropertyDict addPropertyDict(PropertyDict propertyDict);

	/**
	 * 修改系统属性字典
	 * 
	 * @param propertyDict
	 *            系统属性字典对象
	 * @return 系统属性字典对象
	 */
	public PropertyDict updatePropertyDict(PropertyDict propertyDict);

	/**
	 * 根据域属性ID获取系统属性字典列表
	 * 
	 * @param Id
	 *            域属性ID
	 * @return
	 */
	public List<PropertyDict> findPropertyDictByPropertyDomainId(Long domainId);

	public List<PropertyDict> findPropertyDictByPropertyDomainId(Long domainId,
			String sidx, String sord);

	/**
	 * 根据系统属性字典ID查询系统属性字典
	 * 
	 * @param id
	 *            系统属性字典ID
	 * @param domainName
	 *            为适应缓存需求，添加参数domainName
	 */
	public PropertyDict getPropertyDictByDomainNameAndDictId(String domainName,
			Long id);

	/**
	 * 修改系统属性字典序号
	 * 
	 * @param displaySeq
	 */
	public void movePropertyDict(Long id, Long propertyDomainId,
			ReferType position, Long referPropertyDictId);

	/**
	 * 根据域属性名称查询系统属性字典
	 * 
	 * @param domainName
	 *            域属性名称
	 */
	public List<PropertyDict> findPropertyDictByDomainName(String domainName);

	public List<PropertyDict> findPropertyDictByDisplayNameAndDomainPropertyId(
			Long propertyDomainId, String displayName, Long id);

	public List<PropertyDict> findPropertyDictByDomainNameAndInternalId(
			String domainName, Integer internalId);

	/**
	 * 删除系统属性
	 * 
	 * @param id
	 *            系统属性id
	 * @return int
	 */
	public int deletePropertyDictById(Long id);

	public List<PropertyDict> findPropertyDictByDomainNameAndInternalIds(
			String domainName, int[] internalIds);

	public PropertyDict findPropertyDictByDomainNameAndDictDisplayName(
			String propertyDomainName, String dictDisplayName);

	public PropertyDict getPropertyDictById(Long id);

	public PropertyDict getPropertyDictByOrgId(Long id);

	public List<MobilePropertyDict> findMobilePropertyDictByDomainName(
			String domainName);

	public PropertyDict getPropertyDictByDomainName(String useInLevel);

	public PropertyDict getPropertyDictName(Long gender);
	
	public List<PropertyDict> findPropertyDictByDomainNameAndDisplayseqs(
			String domainName, int[] displayseqs);
	
	/**
	 * 根据拼音和属性ID查询字典项信息
	 * 
	 * @param pinyin
	 * @param domainId
	 * @return
	 */
	public List<PropertyDict> getPropertyDictByPinYinAndDomainid(String pinyin,
			Long domainId);

	/**
	 * 根据属性ID和系统内置ID查询字典项信息
	 * 
	 * @param pinyin
	 * @param domainId
	 * @return
	 */
	public List<PropertyDict> getPropertyDictByDomainidAndInternalid(
			Long domainId, Integer internalid);
	
	/**
	 * 二级联动获得包含一级的信息的二级信息
	 * 
	 * @param dictType
	 *            一级的大类id
	 * @param dictTypeSub
	 *            二级的大类id
	 * @return
	 */
	public List<PropertyDict> findFullPropertyDictByDomainId(Long dictTypeId,
			Long dictTypeSubId);
}
