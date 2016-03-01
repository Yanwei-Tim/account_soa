package com.tianque.init.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.init.Initialization;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

public class OrganizationInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private OrganizationDubboRemoteService organizationDubboService;
	private PropertyDictDubboService propertyDictDubboService;

	public OrganizationInitialization(OrganizationDubboRemoteService organizationDubboService,
			PropertyDictDubboService propertyDictDubboService) {
		this.organizationDubboService = organizationDubboService;
		this.propertyDictDubboService = propertyDictDubboService;
	}

	@Override
	public void init() {

		Organization organization = addOrganization("中国;1;000000", null, 1L,
				OrganizationLevel.COUNTRY, "01");
		Organization org = addOrganization("浙江省;1;12345678", organization
				.getId(), 1L, OrganizationLevel.PROVINCE, "02");
		addOrganization(
				"网格一;1;12345678",
				addOrganization(
						"一村;1;12345678",
						addOrganization(
								"一镇;1;12345678",
								addOrganization(
										"一区;1;12345678",
										addOrganization("宁波;1;12345678",
												org.getId(), 1L,
												OrganizationLevel.CITY, "07")
												.getId(), 1L,
										OrganizationLevel.DISTRICT, "06").getId(),
								1L, OrganizationLevel.TOWN, "05").getId(), 1L,
						OrganizationLevel.VILLAGE, "04").getId(), 1L,
				OrganizationLevel.GRID, "03");

		logger.info("默认责任区创建完成!");
	}

	/**
	 * str: orgName;orgType;contactWay;
	 * 
	 * @param str
	 * @param parentId
	 * @return
	 */
	private Organization addOrganization(String str, Long parentId, Long seq,
			int orgLevelInternalId, String depNo) {
		String[] orgValues = str.split(";");
		Organization organization = new Organization();
		organization.setOrgName(orgValues[0]);
		organization.setContactWay(orgValues[2]);
		organization.setCreateUser("超级管理员");
		List<PropertyDict> orgTypes = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION);
		organization.setOrgType(orgTypes.get(0));
		List<PropertyDict> orgLevels = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, orgLevelInternalId);
		organization.setOrgLevel(orgLevels.get(0));
		if (parentId != null) {
			Organization parentOrg = new Organization();
			parentOrg.setId(parentId);
			organization.setParentOrg(parentOrg);
		}
		organization.setDepartmentNo(depNo);
		organization = organizationDubboService.addOrganization(organization);

		return organization;
	}

}