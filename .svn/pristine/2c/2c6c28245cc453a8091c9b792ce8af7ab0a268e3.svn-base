package com.tianque.account.api.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsKeyGeneratorDubboService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;

@Component
public class ThreeRecordsKeyGeneratorDubboServiceImpl implements
		ThreeRecordsKeyGeneratorDubboService {

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;

	@Override
	public String getNextKey(String prefix, Long orgId) {
		return threeRecordsKeyGeneratorService.getNextKey(prefix, orgId);
	}

	@Override
	public String getNextKey(String prefix, Long orgId, Date createDate) {
		return threeRecordsKeyGeneratorService.getNextKey(prefix, orgId,
				createDate);
	}

	@Override
	public String getNextFormKey(String prefix,String formType) {
		return threeRecordsKeyGeneratorService.getNextFormKey(prefix,formType);
	}

	@Override
	public String getNextFormKey(String prefix, String formType, Date createDate) {
		return threeRecordsKeyGeneratorService.getNextFormKey(prefix, formType, createDate);
	}

}
