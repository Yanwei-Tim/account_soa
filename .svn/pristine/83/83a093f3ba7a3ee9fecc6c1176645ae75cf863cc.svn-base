package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsIssueLogDubboService;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.ThreeRecordsIssueLogService;

@Component
public class ThreeRecordsIssueLogDubboServiceImpl implements
		ThreeRecordsIssueLogDubboService {
	@Autowired
	private ThreeRecordsIssueLogService threeRecordsIssueLogService;

	@Override
	public ThreeRecordsIssueLogNew addLog(ThreeRecordsIssueLogNew log) {
		return threeRecordsIssueLogService.addLog(log);
	}

	@Override
	public void deleteIssueLogByLedgerIdAndLedgerType(Long ledgerId,
			int ledgerType) {
		threeRecordsIssueLogService.deleteIssueLogByLedgerIdAndLedgerType(
				ledgerId, ledgerType);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> findDealLogByMapAndStepGroup(
			ThreeRecordsIssueMap issueMap, ThreeRecordsIssueStepGroup group) {
		return threeRecordsIssueLogService.findDealLogByMapAndStepGroup(
				issueMap, group);
	}

	@Override
	public ThreeRecordsIssueLogNew getLogById(Long id) {
		return threeRecordsIssueLogService.getLogById(id);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> getLogsByStepId(Long stepid) {
		return threeRecordsIssueLogService.getLogsByStepId(stepid);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> loadOperationLogsByIssueId(
			Long issueid, Long ledgerType) {
		return threeRecordsIssueLogService.loadOperationLogsByIssueId(issueid,
				ledgerType);
	}

	@Override
	public ThreeRecordsIssueLogNew updateLog(ThreeRecordsIssueLogNew log) {
		return threeRecordsIssueLogService.updateLog(log);
	}

}
