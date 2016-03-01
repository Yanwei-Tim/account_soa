package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsSearchIssueStepDubboService;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.service.ThreeRecordsSearchIssueStepService;

@Component
public class ThreeRecordsSearchIssueStepDubboServiceImpl implements
		ThreeRecordsSearchIssueStepDubboService {
	@Autowired
	private ThreeRecordsSearchIssueStepService threeRecordsSearchIssueStepService;

	@Override
	public boolean hasPermission(Long ledgerId, int ledgerType, Long stepId,
			String userOrgCode) {
		return threeRecordsSearchIssueStepService.hasPermission(ledgerId,
				ledgerType, stepId, userOrgCode);
	}

	@Override
	public List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId) {
		return threeRecordsSearchIssueStepService
				.searchAllIssueStepsByStepId(stepId);
	}

	@Override
	public List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType) {
		return threeRecordsSearchIssueStepService
				.searchIssueStepsByIssueId(issueId, ledgerType);
	}

}
