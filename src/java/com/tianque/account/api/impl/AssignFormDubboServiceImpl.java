package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.AssignFormDubboService;
import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.AssignFormService;

@Component
public class AssignFormDubboServiceImpl implements AssignFormDubboService {

	@Autowired
	private AssignFormService assignFormService;

	@Override
	public AssignForm addAssignForm(AssignForm assignForm) {
		return assignFormService.addAssignForm(assignForm);
	}

	@Override
	public AssignForm updateAssignForm(AssignForm assignForm) {
		return assignFormService.updateAssignForm(assignForm);
	}

	@Override
	public AssignForm getSimpleAssignFormById(Long id) {
		return assignFormService.getSimpleAssignFormById(id);
	}

	@Override
	public AssignForm getSimpleAssignFormByStepId(Long id) {
		return assignFormService.getSimpleAssignFormByStepId(id);
	}

	@Override
	public AssignForm createTemporaryAssignForm(
			ThreeRecordsIssueLogNew operation, Long[] tells, Long keyId) {
		return assignFormService.createTemporaryAssignForm(operation, tells,
				keyId);
	}

	@Override
	public AssignForm saveAssignFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			AssignForm assignForm) {
		return assignFormService.saveAssignFormAndCompletePorcess(operation,
				tellOrgIds, notices, files, assignForm);
	}

}
