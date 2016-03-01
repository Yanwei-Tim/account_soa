package com.tianque.account.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerPoorPeopleDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

@Component
public class LedgerPoorPeopleDubboServiceImpl implements
		LedgerPoorPeopleDubboService {

	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;

	@Override
	public LedgerPoorPeople addLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople) {
		return ledgerPoorPeopleService.addLedgerPoorPeople(ledgerPoorPeople);
	}

	@Override
	public ThreeRecordsIssueViewObject assign(Long stepId,
			ThreeRecordsIssueLogNew log, Long targeOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		return ledgerPoorPeopleService.assign(stepId, log, targeOrg, tells,
				attachFiles, notices);
	}

	@Override
	public ThreeRecordsIssueViewObject back(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		return ledgerPoorPeopleService.back(stepId, log, attachFiles);
	}

	@Override
	public ThreeRecordsIssueViewObject comment(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		return ledgerPoorPeopleService.comment(stepId, log, attachFiles);
	}

	@Override
	public ThreeRecordsIssueViewObject complete(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		return ledgerPoorPeopleService.complete(stepId, log, attachFiles);
	}

	@Override
	public ThreeRecordsIssueViewObject concept(Long stepId,
			ThreeRecordsIssueLogNew log) {
		return ledgerPoorPeopleService.concept(stepId, log);
	}

	@Override
	public ThreeRecordsIssueViewObject declare(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files) {
		return ledgerPoorPeopleService.declare(stepId, log, target, tellorgs,
				files);
	}

	@Override
	public void deleteLedgerPoorPeopleById(Long id) {
		ledgerPoorPeopleService.deleteLedgerPoorPeopleById(id);
	}

	@Override
	public LedgerPoorPeople findByIdCardNo(String idCardNo) {
		return ledgerPoorPeopleService.findByIdCardNo(idCardNo);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsAssgin(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsDone(orgId, page, rows,
				sidx, sord, issueType, orgLevel, leaderView, functionalOrgType,
				viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsFeedBack(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsFollow(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsNeedDo(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalorgType, viewProcess, sourceType,
				LedgerConstants.OPERATE_TYPE_SPONSOR, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsPeriodDone(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsSubmit(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubstanceDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsSubstanceDone(
				seachValue, orgId, page, rows, sidx, sord, issueType, orgLevel,
				leaderView, functionalOrgType, viewProcess, sourceType, year,
				month);
	}

	@Override
	public LedgerPoorPeople getFullLedgerPoorPeopleById(Long id) {
		return ledgerPoorPeopleService.getFullLedgerPoorPeopleById(id);
	}

	@Override
	public LedgerPoorPeople getLedgerPoorPeopleById(Long id) {
		return ledgerPoorPeopleService.getLedgerPoorPeopleById(id);
	}

	@Override
	public ThreeRecordsIssueViewObject peroidComplete(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		return ledgerPoorPeopleService.peroidComplete(stepId, log, files);
	}

	@Override
	public ThreeRecordsIssueViewObject programComplete(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		return ledgerPoorPeopleService.programComplete(stepId, log, files);
	}

	@Override
	public ThreeRecordsIssueViewObject submit(Long stepId,
			ThreeRecordsIssueLogNew log, Long submitTarget, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		return ledgerPoorPeopleService.submit(stepId, log, submitTarget, tells,
				attachFiles);
	}

	@Override
	public ThreeRecordsIssueViewObject support(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		return ledgerPoorPeopleService.support(stepId, log, files);
	}

	@Override
	public ThreeRecordsIssueViewObject tmpComment(Long keyId,
			ThreeRecordsIssueLogNew operation,
			List<ThreeRecordsIssueAttachFile> files) {
		return ledgerPoorPeopleService.tmpComment(keyId, operation, files);
	}

	@Override
	public ThreeRecordsIssueViewObject turn(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files, Long[] notices) {
		return ledgerPoorPeopleService.turn(stepId, log, target, tellorgs,
				files, notices);
	}

	@Override
	public void updateLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople) {
		ledgerPoorPeopleService.updateLedgerPoorPeople(ledgerPoorPeople);
	}

	@Override
	public void updateLedgerPoorPeopleStatus(LedgerPoorPeople ledgerPoorPeople) {
		ledgerPoorPeopleService.updateLedgerPoorPeopleStatus(ledgerPoorPeople);

	}

	@Override
	public LedgerPoorPeople createTemporaryLedgerPoorPeople(Long orgId) {
		return ledgerPoorPeopleService.createTemporaryLedgerPoorPeople(orgId);
	}

	@Override
	public LedgerPoorPeople saveLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople) {
		return ledgerPoorPeopleService.saveLedgerPoorPeople(ledgerPoorPeople);
	}

	@Override
	public void registerProcess(LedgerPoorPeople ledgerPoorPeople) {
		ledgerPoorPeopleService.registerProcess(ledgerPoorPeople);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSupportDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsNeedDo(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalorgType, viewProcess, sourceType,
				LedgerConstants.OPERATE_TYPE_SUPPORT, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNoticeDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsNeedDo(seachValue,
				orgId, page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalorgType, viewProcess, sourceType,
				LedgerConstants.OPERATE_TYPE_NOTICE, year, month);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month) {
		return ledgerPoorPeopleService.findJurisdictionsCreateAndDone(
				seachValue, orgId, page, rows, sidx, sord, issueType, orgLevel,
				leaderView, functionalOrgType, viewProcess, sourceType, year,
				month);
	}

	@Override
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode) {
		return ledgerPoorPeopleService.countLedgerByOldHistoryId(oldHistoryId,
				orgCode);
	}

	@Override
	public Map<String, Integer> getIssueCount(Long orgId, Long issueType,
			Long orgLevel, String leaderView, Long sourceType,
			Long functionalOrgType, Integer year, Integer month) {
		return ledgerPoorPeopleService.getIssueCount(orgId, issueType,
				orgLevel, leaderView, sourceType, functionalOrgType, year, month);
	}
}
