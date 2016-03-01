package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsIssueProcessDubboService;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.ThreeRecordsIssueProcessService;

@Component
public class ThreeRecordsIssueProcessDubboServiceImpl implements
		ThreeRecordsIssueProcessDubboService {
	@Autowired
	protected ThreeRecordsIssueProcessService threeRecordsIssueProcessService;

	@Override
	public ThreeRecordsIssueStepGroup addIssueStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup) {
		return threeRecordsIssueProcessService
				.addIssueStepGroup(issueStepGroup);
	}

	@Override
	public ThreeRecordsIssueStep addLedgerStep(ThreeRecordsIssueStep issueStep) {
		return threeRecordsIssueProcessService.addLedgerStep(issueStep);
	}

	@Override
	public void deleteLedgerStepGroupsByIdAndType(Long ledgerId, int ledgerType) {
		threeRecordsIssueProcessService.deleteLedgerStepGroupsByIdAndType(
				ledgerId, ledgerType);

	}

	@Override
	public void deleteLedgerStepsByIdAndType(Long ledgerId, int ledgerType) {
		threeRecordsIssueProcessService.deleteLedgerStepsByIdAndType(ledgerId,
				ledgerType);

	}

	@Override
	public ThreeRecordsIssueMap getIssueMapByStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup) {
		return threeRecordsIssueProcessService
				.getIssueMapByStepGroup(issueStepGroup);
	}

	@Override
	public List<ThreeRecordsIssueStepGroup> getIssueStepGroupByIssueId(
			Long issueId, int ledgerType) {
		return threeRecordsIssueProcessService.getIssueStepGroupByIssueId(
				issueId, ledgerType);
	}

	@Override
	public ThreeRecordsIssueStep getSimpleIssueStepById(Long id) {
		return threeRecordsIssueProcessService.getSimpleIssueStepById(id);
	}

	@Override
	public ThreeRecordsIssueStepGroup getSimpleIssueStepGroupById(Long id) {
		return threeRecordsIssueProcessService.getSimpleIssueStepGroupById(id);
	}

	@Override
	public boolean updateGroupId(ThreeRecordsIssueStep step) {
		return threeRecordsIssueProcessService.updateGroupId(step);
	}

	@Override
	public ThreeRecordsIssueStep updateIssueStepExceptOrg(
			ThreeRecordsIssueStep step) {
		return threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
	}

	@Override
	public boolean updateOutLog(ThreeRecordsIssueStepGroup issueStepGroup) {
		return threeRecordsIssueProcessService.updateOutLog(issueStepGroup);
	}

	@Override
	public void updateStateAndCode(Long ledgerId, int ledgerType) {
		threeRecordsIssueProcessService
				.updateStateAndCode(ledgerId, ledgerType);
	}

}
