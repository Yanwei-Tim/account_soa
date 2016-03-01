package com.tianque.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

public class OrganizationServiceHelper {

	public static void checkAddOrganization(Organization organization,
			OrganizationDubboRemoteService organizationDubboService,
			PropertyDictDubboService propertyDictDubboService) {

		if (organization.getOrgName() == null) {
			throw new BusinessValidationException("责任区名称不能为空");
		}
		if (organization.getOrgType() == null
				|| organization.getOrgType().getId() == null) {
			throw new BusinessValidationException("责任区类型不能为空");
		}

		if (organization.getOrgLevel() == null
				|| organization.getOrgLevel().getId() == null) {
			throw new BusinessValidationException("责任区级别不能为空");
		}

		/** 如果是职能部门，部门类别不能为空 */
		if (organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			if (organization.getFunctionalOrgType() == null) {
				throw new BusinessValidationException("职能部门的部门类别不能为空");
			}
		}
		if (organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			// 有父部门的部门的部门编号不能为空
			if (!StringUtil.isStringAvaliable(organization.getDepartmentNo())) {

				throw new BusinessValidationException("责任区编号不能为空");
			}
			if (organizationDubboService
					.findOrganizationsByDepartmentNoAndTypeAndLevel(
							organization).size() > 0) {

				throw new BusinessValidationException("责任区编号已经存在");
			}
			if (organizationDubboService.findOrganizationsByOrgNameAndParentId(
					null, organization.getOrgName(),
					organization.getParentOrg().getId()).size() > 0) {
				throw new BusinessValidationException("责任区名称已经存在");
			}

			Organization parentOrg = organizationDubboService
					.getFullOrgById(organization.getParentOrg().getId());

			if (parentOrg.getOrgLevel().getInternalId() == OrganizationLevel.GRID) {

				throw new BusinessValidationException("不能在片组片格级别的责任区中添加子责任区");
			}
			PropertyDict thisOrgType = propertyDictDubboService
					.getPropertyDictById(organization.getOrgType().getId());

			if (parentOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& thisOrgType.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {

				throw new BusinessValidationException("职能责任区下不能添加责任区");
			}
			// 职能部门下不能添加职能部门
			if (parentOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& thisOrgType.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {

				throw new BusinessValidationException("职能部门下不能添加职能部门！");
			}
			// 社区下不能添加职能部门
			if (parentOrg.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE
					&& thisOrgType.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {

				throw new BusinessValidationException("社区下不能添加职能部门！");
			}
			if (thisOrgType.getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& !validateOrgCodeForFunctional(organization
							.getDepartmentNo())) {
				throw new BusinessValidationException("职能部门部门编号只能输入1位数字加2位字母！");
			}
			PropertyDict thisOrgLevel = propertyDictDubboService
					.getPropertyDictById(organization.getOrgLevel().getId());

			if (OrganizationLevel.getLowerLevel(parentOrg.getOrgLevel()
					.getInternalId()) != thisOrgLevel.getInternalId()
					&& thisOrgLevel.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
				throw new BusinessValidationException(parentOrg.getOrgLevel()
						.getDisplayName()
						+ "级别的责任区只能添加低于"
						+ parentOrg.getOrgLevel().getDisplayName() + "一级的子责任区");
			}
		}
	}

	public static void checkOrgWhenUpdate(
			OrganizationDubboRemoteService organizationDao,
			PropertyDictDubboService propertyDictDubboService,
			Organization organization) {
		if (organization.getOrgName() == null
				|| "".equals(organization.getOrgName())) {
			throw new BusinessValidationException("责任区名称不能为空");
		}
		PropertyDict organizationType = propertyDictDubboService
				.getPropertyDictById(organization.getOrgType().getId());
		PropertyDict organizationLevel = propertyDictDubboService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if (organizationType.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& organizationLevel.getInternalId() == OrganizationLevel.DISTRICT
				&& (null == organization.getDepartmentNo() || ""
						.equals(organization.getDepartmentNo()))) {
			throw new BusinessValidationException("责任区编号不能为空");
		}
		List<Organization> organizations = organizationDao
				.findOrganizationsByParentId(organization.getId());
		PropertyDict orgType = propertyDictDubboService
				.getPropertyDictById(organization.getOrgType().getId());
		if (orgType.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& organizations.size() > 0) {
			throw new BusinessValidationException("此责任区下有子责任区，不能修改成职能类型");
		}
	}

	public boolean validateRegionOrFun(String orgInternalCode) {
		String regex = "[a-zA-Z]+";
		Matcher matcher = Pattern.compile(regex).matcher(orgInternalCode);
		return matcher.find();
	}

	public static boolean validateOrgCodeForFunctional(String orgInternalCode) {
		String regex = "[0-9]{1}[a-zA-Z]{2}$";
		Matcher matcher = Pattern.compile(regex).matcher(orgInternalCode);
		return matcher.find();
	}

	public void checkOrgLevelAndDepartmentNo(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		if (null == organization.getDepartmentNo()) {
			throw new BusinessValidationException("部门编号为空!");
		}

		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.PARTYWORK);
		if (!orgTypes.isEmpty() && null != orgTypes.get(0).getId()
				&& null != organization.getOrgType()
				&& null != organization.getOrgType().getId()) {
			if (orgTypes.get(0).getInternalId() == OrganizationType.PARTYWORK) {
				PropertyDict propertyDicts = (PropertyDict) propertyDictDubboService
						.getPropertyDictByOrgId(organization.getParentOrg()
								.getId());
				if (null != propertyDicts
						&& propertyDicts.getInternalId() != OrganizationType.ADMINISTRATIVE_REGION) {
					throw new BusinessValidationException("部门类型为行政区域才能建党工委");
				}
			}
		} else {
			if (!validateRegionOrFun(organization.getDepartmentNo())) {
				validateOrgTypeRegion(organization, propertyDictDubboService);
			} else {
				validateOrgTypeFunc(organization, propertyDictDubboService);
			}
		}
	}

	public void checkDepartmentNoAndOrgLevel(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		if (null == organization.getOrgType()) {
			throw new BusinessValidationException("部门类型为空!");
		}
		PropertyDict doamin = propertyDictDubboService
				.getPropertyDictById(organization.getOrgType().getId());
		if (validateRegionOrFun(organization.getDepartmentNo())
				&& doamin.getDisplayName().equals("职能部门")) {

			validateOrgTypeFuncWhenAdd(organization, propertyDictDubboService);
		}
		if (!validateRegionOrFun(organization.getDepartmentNo())
				&& doamin.getDisplayName().equals("行政区域")) {
			validateOrgTypeRegionWhenAdd(organization, propertyDictDubboService);
		}

	}

	private PropertyDict getOrgType(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		return propertyDictDubboService.getPropertyDictById(organization
				.getOrgType().getId());
	}

	private void validateOrgTypeFunc(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG);
		if (!orgTypes
				.get(0)
				.getId()
				.equals(getOrgType(organization, propertyDictDubboService)
						.getId())) {
			throw new BusinessValidationException("传递的部门编号并非职能机构");
		}
	}

	private void validateOrgTypeFuncWhenAdd(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG);
		if (!orgTypes
				.get(0)
				.getId()
				.equals(getOrgType(organization, propertyDictDubboService)
						.getId())) {
			throw new BusinessValidationException("部门类型与编号不匹配");
		}
	}

	private void validateOrgTypeRegionWhenAdd(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION);
		if (!orgTypes
				.get(0)
				.getId()
				.equals(getOrgType(organization, propertyDictDubboService)
						.getId())) {
			throw new BusinessValidationException("部门类型与编号不匹配");
		}
	}

	private void validateOrgTypeRegion(Organization organization,
			PropertyDictDubboService propertyDictDubboService) {
		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION);
		if (!orgTypes
				.get(0)
				.getId()
				.equals(getOrgType(organization, propertyDictDubboService)
						.getId())) {
			throw new BusinessValidationException("传递的部门编号并非行政区域");
		}
	}

	public static Organization getRootOrg(
			OrganizationDubboRemoteService organizationDubboService) {
		return organizationDubboService.findOrganizationsByParentId(null)
				.get(0);
	}

}
