package com.tianque.init;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.GridInternalProperty;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

public abstract class AbstractSystemPropertiesInitialization implements
		Initialization {
	protected PropertyDomainDubboService propertyDomainDubboService;

	protected PropertyDictDubboService propertyDictDubboService;
	public PropertyDomain propertyDomain = new PropertyDomain();

	public AbstractSystemPropertiesInitialization(
			PropertyDomainDubboService propertyDomainDubboService,
			PropertyDictDubboService propertyDictDubboService) {
		this.propertyDictDubboService = propertyDictDubboService;
		this.propertyDomainDubboService = propertyDomainDubboService;
	}

	protected PropertyDict addPropertyDict(String displayName, int internalId,
			int displaySeq) {
		PropertyDict propertyDict = new PropertyDict();
		propertyDict.setDisplayName(displayName);
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(propertyDict.getDisplayName());
		propertyDict.setFullPinyin((String) pinyin.get("fullPinyin"));
		propertyDict.setSimplePinyin((String) pinyin.get("simplePinyin"));
		propertyDict.setCreateDate(Calendar.getInstance().getTime());
		propertyDict.setPropertyDomain(propertyDomain);
		propertyDict.setInternalId(internalId);
		propertyDict.setDisplaySeq(displaySeq);

		propertyDict.setCreateUser("admin");
		return propertyDictDubboService.addPropertyDict(propertyDict);
	}

	protected PropertyDomain addPropertyDomain(String domainName,
			boolean systemSensitive, List<GridInternalProperty> properties) {
		propertyDomain.setDomainName(domainName);
		propertyDomain.setSystemSensitive(systemSensitive);
		propertyDomain.setInternaleProperties(properties);
		return propertyDomainDubboService.addPropertyDomain(propertyDomain);
	}
}
