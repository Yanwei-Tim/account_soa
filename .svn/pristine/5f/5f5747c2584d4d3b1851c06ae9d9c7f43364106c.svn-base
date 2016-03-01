package com.tianque.init;

import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

public class ExposureRatingPropertyInit extends
		AbstractSystemPropertiesInitialization {

	public ExposureRatingPropertyInit(
			PropertyDomainDubboService propertyDomainDubboService,
			PropertyDictDubboService propertyDictDubboService) {
		super(propertyDomainDubboService, propertyDictDubboService);
	}

	@Override
	public void init() throws Exception {
		initRentalBuildings();
	}

	private void initRentalBuildings() {
		propertyDomain = addPropertyDomain("租赁公房", false, null);
		addPropertyDict("单位自管公房", 0, 1);
		addPropertyDict("政府公管公房", 0, 2);
	}
}
