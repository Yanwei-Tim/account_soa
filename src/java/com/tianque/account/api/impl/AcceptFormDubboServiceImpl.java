package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.AcceptFormDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.AcceptFormService;
import com.tianque.plugin.account.vo.AcceptFormVo;

@Component
public class AcceptFormDubboServiceImpl implements AcceptFormDubboService {
	
	@Autowired
	private AcceptFormService acceptFormService;

	@Override
	public PageInfo<AcceptForm> findAcceptForms(AcceptFormVo acceptFormVo,
			Integer page, Integer rows) {
		return acceptFormService.findAcceptForms(acceptFormVo, page, rows);
	}

	@Override
	public AcceptForm addAcceptForm(AcceptForm acceptForm) {
		return acceptFormService.addAcceptForm(acceptForm);
	}

	@Override
	public AcceptForm updateAcceptForm(AcceptForm acceptForm) {
		return acceptFormService.updateAcceptForm(acceptForm);
	}

	@Override
	public AcceptForm getSimpleAcceptFormById(Long id) {
		return acceptFormService.getSimpleAcceptFormById(id);
	}

	@Override
	public AcceptForm saveAcceptFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, AcceptForm acceptForm) {
		return acceptFormService.saveAcceptFormAndCompletePorcess(operation, tellOrgIds, files, acceptForm);
	}

	@Override
	public AcceptForm createTemporaryAcceptForm(
			Long keyId, Long ledgerType) {
		return acceptFormService.createTemporaryAcceptForm(keyId, ledgerType);
	}

	@Override
	public AcceptForm getSimpleAcceptFormByStepId(Long id) {
		return acceptFormService.getSimpleAcceptFormByStepId(id);
	}

}
