package com.tianque.core.datatransfer.dataconvert;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Component("dataConvertionHelper")
@Scope("prototype")
public class DataConvertionHelper {
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	// private Map<Long ,Organization> cacheOrgs=new HashMap<Long
	// ,Organization>();

	public PropertyDict convertToPropertyDict(String propertyDomainName,
			String dictDisplayName) {
		if (!StringUtil.isStringAvaliable(dictDisplayName)) {
			return new PropertyDict();
		}
		return propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				propertyDomainName, dictDisplayName);
	}

	public List<PropertyDict> convertToPropertyDicts(String propertyDomainName,
			int[] internalIds) {
		if (internalIds == null || internalIds.length == 0) {
			return null;
		}
		return propertyDictDubboService.findPropertyDictByDomainNameAndInternalIds(
				propertyDomainName, internalIds);
	}

	public Date convertToDate(String dateText) {
		if (!StringUtil.isStringAvaliable(dateText)) {
			return null;
		}
		Date result = null;
		result = CalendarUtil.parseDate(dateText, "yyyy/MM/dd");
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "yyyy-MM-dd");
		}
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "MM/dd/yy");
		}
		return result;
	}

	public Organization convertToOrganization(Organization uploadOrganization,
			String orgName) {
		if (null == orgName || orgName.isEmpty()) {
			return uploadOrganization;
		}
		List<Organization> results = null;
		if (orgName != null)
			orgName = orgName.trim();
		// if (cacheOrgs.containsKey(key))
		int value = orgName.indexOf("@");
		if (value == -1) {
			results = organizationDubboService.findOrganizationsByOrgNameAndParentId(null,
					orgName, uploadOrganization.getId());
		}
		if (value != -1) {
			String countryOrgName = orgName.substring(0, value);
			String gridOrgName = orgName.substring(value + 1);
			results = organizationDubboService.findOrganizationsByOrgNameAndParentId(null,
					countryOrgName, uploadOrganization.getId());
			if (results == null || results.size() != 1) {
				return null;
			}
			results = organizationDubboService.findOrganizationsByOrgNameAndParentId(null,
					gridOrgName, results.get(0).getId());
		}

		return results != null && results.size() == 1 ? results.get(0) : null;
	}

	public boolean convertToBoolean(String booleanText) {
		if (!StringUtil.isStringAvaliable(booleanText)) {
			return Boolean.FALSE;
		}
		return "是".equals(booleanText.trim()) ? true : false;
	}

	public Double convertToDouble(String doubleText) {
		if (StringUtil.isStringAvaliable(doubleText)) {
			return Double.valueOf(doubleText);
		} else {
			return null;
		}
	}

	public double convertToDoubleValue(String doubleText) {
		Double result = convertToDouble(doubleText);
		return result == null ? 0 : result.doubleValue();
	}

	public Long convertToLong(String longText) {
		if (StringUtil.isStringAvaliable(longText)) {
			return Long.valueOf(longText);
		} else {
			return null;
		}
	}

}
