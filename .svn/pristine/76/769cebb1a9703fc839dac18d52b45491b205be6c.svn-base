package com.tianque.service;

import java.util.List;

import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.mobile.vo.MobileIssueType;

public interface IssueTypeService {
	public List<MobileIssueType> findMobileEnabledIssueTypesByParentName(String parentName);
	

	/*
	 * 根据传入父类名称获得服务办事类别
	 */
	public List<IssueType> findIssueTypesByDomainId(Long domainId);
	
	public List<IssueType> findIssueTypesByParentName(String parentName);

	public List<IssueType> findEnabledIssueTypesByParentName(Long orgId,String parentName);

	public IssueType addIssueType(IssueType issueType);
	
	public IssueType getIssueTypeById(Long domainId,Long id);

	

	public IssueType updateIssueType(IssueType issueType);

	public boolean deleteIssueTypeById(IssueType issueType);

	public void moveIssueTypeToFirst(Long domainId,Long id,int indexId);

	public void moveIssueTypeToEnd(Long domainId,Long id,int indexId);

	public void moveIssueTypeToPreviousOrNext(Long domainId,Long id,int indexId, Long referIssueTypeId);

	public IssueType getIssueTypeByIssueTypeName(String issueTypeName,Long domainId);
	
	public void addRelatePlaces(Long issueId,String placeType,Long placeId,String placename);
	
	public void deleteRelatePlaces(Long issueId);
	
	public void addRelatePersons(Long issueId,String personType,Long personId,String personName);
	
	public void deleteRelatePersons(Long issueId);
	
	public List<EmphasesVo> findRelatePersonByName(Long issueId);
	
	public List<EmphasesVo> findRelatePlacesByName(Long issueId);

	public IssueType getIssueTypeByIssueTypeNameAndOrgId(String issueTypeName,
			Long domainId, Long orgId);

	public List<IssueType> findIssueTypesByDomainIdAndOrgId(Long domainId,
			Long orgId);
	
	public void updateIsEnabledById(Long id, boolean enabled);

	public List<IssueType> findAllIssueTypesByParentName(Long orgId,	String typeDomainName);

	
	List<IssueTypeDomain> findIssueTypeDomainsByModule(String module);

	IssueTypeDomain getIssueTypeDomainById(Long id);

	IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName);
}
