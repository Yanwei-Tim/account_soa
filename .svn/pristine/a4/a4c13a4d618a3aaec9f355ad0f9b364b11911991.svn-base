package com.tianque.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.dao.IssueTypeDao;
import com.tianque.dao.IssueTypeDomainDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.mobile.vo.MobileIssueType;
import com.tianque.service.IssueTypeService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Transactional
@Service("issueTypeService")
public class IssueTypeServiceImpl implements IssueTypeService {

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	private IssueTypeDomainDao issueTypeDomainDao;

	@Autowired
	private IssueTypeDao issueTypeDao;

	@Override
	public List<IssueType> findIssueTypesByDomainId(Long domainId) {
		IssueTypeDomain domain = issueTypeDomainDao
				.getIssueTypeDoaminById(domainId);
		if (domain.isPersonalized()) {
			return getPersonalizedIssueType(domain.getId());
		} else {
			return getNormalIssueType(domain.getId());
		}
	}

	private List<IssueType> getNormalIssueType(Long domainId) {
		return issueTypeDao.findIssueTypesByDomainId(domainId);
	}

	private List<IssueType> getPersonalizedIssueType(Long domainId) {
		List<IssueType> list = issueTypeDao.findIssueTypesByDomainId(domainId);
		for (IssueType issueType : list) {
			issueType.getOrg().setOrgName(
					organizationDubboService.getSimpleOrgById(
							issueType.getOrg().getId()).getOrgName());
		}
		return list;
	}

	@Override
	public IssueType addIssueType(IssueType issueType) {
		setChinesePinyin(issueType);
		issueType.setIndexId(getIndexIdByDomainId(issueType
				.getIssueTypeDomain().getId()));
		issueType = issueTypeDao.addIssueType(issueType);
		if (issueType.getOrg() != null) {
			issueType.getOrg().setOrgName(
					organizationDubboService.getSimpleOrgById(
							issueType.getOrg().getId()).getOrgName());
		}
		return issueType;
	}

	private Integer getIndexIdByDomainId(Long domainId) {
		Integer maxIndexId = issueTypeDao.getIndexIdByDomainId(domainId);
		if (maxIndexId == null) {
			maxIndexId = 0;
		}
		return ++maxIndexId;
	}

	private void setChinesePinyin(IssueType issueType) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(issueType.getIssueTypeName());
		issueType.setFullPinYin((String) pinyin.get("fullPinyin"));
		issueType.setSimplePinYin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public IssueType getIssueTypeById(Long domainId, Long id) {
		IssueType issueType = issueTypeDao.getIssueTypeById(domainId, id);
		if (null == issueType) {
			return null;
		}
		if (issueType.getOrg() != null) {
			issueType.getOrg().setOrgName(
					organizationDubboService.getSimpleOrgById(
							issueType.getOrg().getId()).getOrgName());
		}
		return issueType;
	}

	@Override
	public IssueType updateIssueType(IssueType issueType) {
		setChinesePinyin(issueType);
		return issueTypeDao.updateIssueType(issueType);
	}

	@Override
	public boolean deleteIssueTypeById(IssueType issueType) {
		issueTypeDao.deleteIssueTypeById(issueType.getIssueTypeDomain()
				.getId(), issueType.getId());
		return true; 
	}

	@Override
	public void moveIssueTypeToEnd(Long domainId, Long id, int indexId) {
		issueTypeDao.moveIssueTypeToEnd(domainId, id, indexId);
	}

	@Override
	public void moveIssueTypeToFirst(Long domainId, Long id, int indexId) {
		issueTypeDao.moveIssueTypeToFirst(domainId, id, indexId);
	}

	@Override
	public void moveIssueTypeToPreviousOrNext(Long domainId, Long id,
			int indexId, Long referIssueTypeId) {
		issueTypeDao.moveIssueTypeToPreviousOrNext(domainId, id, indexId,
				referIssueTypeId);
	}

	@Override
	public IssueType getIssueTypeByIssueTypeName(String issueTypeName,
			Long domainId) {
		return issueTypeDao
				.getIssueTypeByIssueTypeName(issueTypeName, domainId);
	}

	@Override
	public List<IssueType> findIssueTypesByParentName(String parentName) {
		IssueTypeDomain issueTypeDomain = issueTypeDomainDao
				.getIssueTypeDoaminByDomainName(parentName);
		return this.findIssueTypesByDomainId(issueTypeDomain.getId());
	}

	@Override
	public void addRelatePersons(Long issueId, String personType,
			Long personId, String personName) {
		issueTypeDao
				.addRelatePersons(issueId, personType, personId, personName);
	}

	@Override
	public void addRelatePlaces(Long issueId, String placeType, Long placeId,
			String placename) {
		issueTypeDao.addRelatePlaces(issueId, placeType, placeId, placename);
	}

	@Override
	public void deleteRelatePersons(Long issueId) {
		issueTypeDao.deleteRelatePersons(issueId);
	}

	@Override
	public void deleteRelatePlaces(Long issueId) {
		issueTypeDao.deleteRelatePlaces(issueId);
	}

	@Override
	public List<EmphasesVo> findRelatePersonByName(Long issueId) {
		return issueTypeDao.findRelatePersonByName(issueId);
	}

	@Override
	public List<EmphasesVo> findRelatePlacesByName(Long issueId) {
		return issueTypeDao.findRelatePlacesByName(issueId);
	}

	@Override
	public IssueType getIssueTypeByIssueTypeNameAndOrgId(String issueTypeName,
			Long domainId, Long orgId) {
		Organization organization = organizationDubboService.getFullOrgById(orgId);
		if (organization.getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
			throw new ServiceException("个性化办事类型只能在区县级创建");
		}

		return issueTypeDao.getIssueTypeByIssueTypeName(issueTypeName,
				domainId, orgId);
	}

	@Override
	public List<IssueType> findIssueTypesByDomainIdAndOrgId(Long domainId,
			Long orgId) {
		return issueTypeDao.findIssueTypesByDomainIdAndOrgId(domainId, orgId);
	}

	@Override
	public void updateIsEnabledById(Long id, boolean enabled) {
		issueTypeDao.updateIsEnabledById(id, enabled);
	}

	@Override
	public List<IssueType> findEnabledIssueTypesByParentName(Long orgId,
			String parentName) {
		IssueTypeDomain issueTypeDomain = issueTypeDomainDao
				.getIssueTypeDoaminByDomainName(parentName);
		if (issueTypeDomain.isPersonalized()) {
			return getEnabledPersonalizedIssueType(orgId,
					issueTypeDomain.getId());
		} else {
			return getEnabledNormalIssueType(issueTypeDomain.getId());
		}
	}

	private List<IssueType> getEnabledPersonalizedIssueType(Long orgId,
			Long domainId) {
		Organization organization = organizationDubboService
				.getDistrictOrganizationId(orgId);
		return issueTypeDao.findEnabledIssueTypesByDomainIdAndOrgId(domainId,
				organization.getId());
	}

	private List<IssueType> getEnabledNormalIssueType(Long domainId) {
		return issueTypeDao.findEnabledIssueTypesByDomainIdAndOrgId(domainId,
				null);
	}

	@Override
	public List<IssueType> findAllIssueTypesByParentName(Long orgId,
			String typeDomainName) {
		IssueTypeDomain issueTypeDomain = issueTypeDomainDao
				.getIssueTypeDoaminByDomainName(typeDomainName);
		if (!issueTypeDomain.isPersonalized() || orgId == null) {
			return issueTypeDao.findIssueTypesByDomainId(issueTypeDomain
					.getId());
		} else {
			Organization organization = organizationDubboService
					.getDistrictOrganizationId(orgId);
			return issueTypeDao.findAllIssueTypesByDomainIdAndOrgid(
					organization.getId(), issueTypeDomain.getId());
		}
	}

	@Override
	public List<IssueTypeDomain> findIssueTypeDomainsByModule(String module) {
		return issueTypeDomainDao.findIssueTypeDomainsByModule(module);
	}

	@Override
	public IssueTypeDomain getIssueTypeDomainById(Long id) {
		return issueTypeDomainDao.getIssueTypeDoaminById(id);
	}

	@Override
	public List<MobileIssueType> findMobileEnabledIssueTypesByParentName(
			String parentName) {
		// TODO Auto-generated method stub
		IssueTypeDomain issueTypeDomain = issueTypeDomainDao
				.getIssueTypeDoaminByDomainName(parentName);
		return issueTypeDao
				.findMobileEnabledIssueTypesByDomainIdAndOrgId(issueTypeDomain
						.getId());
	}

	@Override
	public IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName) {
		return issueTypeDomainDao.getIssueTypeDoaminByDomainName(domainName);
	}

}
