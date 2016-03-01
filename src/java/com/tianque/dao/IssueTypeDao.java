package com.tianque.dao;

import java.util.List;

import com.tianque.domain.IssueType;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.mobile.vo.MobileIssueType;

public interface IssueTypeDao {
	public List<MobileIssueType> findMobileEnabledIssueTypesByDomainIdAndOrgId(Long domainId);
	

	/*
	 * 根据父类名称获得服务办事类别
	 */
	public List<IssueType> findIssueTypesByDomainId(Long domainId);

	public IssueType addIssueType(IssueType issueType);

	public IssueType getIssueTypeById(Long domainId, Long id);

	public IssueType updateIssueType(IssueType issueType);

	public void deleteIssueTypeById(Long domainId, Long id);

	public void moveIssueTypeToEnd(Long domainId, Long id, int indexId);

	public void moveIssueTypeToFirst(Long domainId, Long id, int indexId);

	public void moveIssueTypeToPreviousOrNext(Long domainId, Long id,
			int indexId, Long referIssueTypeId);

	public IssueType getIssueTypeByIssueTypeName(String issueTypeName,
			Long domainId);

	public Integer getIndexIdByDomainId(Long domainId);

	public void addRelatePlaces(Long issueId, String placeType, Long placeId,
			String placename);

	public void deleteRelatePlaces(Long issueId);

	public void addRelatePersons(Long issueId, String personType,
			Long personId, String personName);

	public void deleteRelatePersons(Long issueId);

	public List<EmphasesVo> findRelatePersonByName(Long issueId);

	public List<EmphasesVo> findRelatePlacesByName(Long issueId);

	

	public Integer getIsRelatePlaces(Long RelatePlaceId, String RelatePlaceType);

	public Integer getIsRelatePersons(Long RelatePersonsId,
			String RelatePersonsType);

	public IssueType getIssueTypeByIssueTypeName(String issueTypeName,
			Long domainId, Long orgId);

	public List<IssueType> findIssueTypesByDomainIdAndOrgId(
			Long domainId, Long orgId);
	
	public void updateIsEnabledById(Long id, boolean enabled);

	public List<IssueType> findEnabledIssueTypesByDomainIdAndOrgId(Long domainId, Long orgId);

	public List<IssueType> findAllIssueTypesByDomainIdAndOrgid(Long orgId,Long domainId);
	
	public List<Long> getIsRelatePersonIds(List<Long> personsIds,
			String personsType);
	public List<Long> getIsRelatePlaceIds(List<Long> RelatePlaceId,
			String RelatePlaceType);
}
