package com.tianque.account.api.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsComprehensiveDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.service.LedgerPoorPeopleComprehensiveService;
import com.tianque.plugin.account.service.LedgerSteadyWorkComprehensiveService;
import com.tianque.plugin.account.service.ThreeRecordsComprehensiveService;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

@Component
public class ThreeRecordsComprehensiveDubboServiceImpl implements
		ThreeRecordsComprehensiveDubboService {
	
	@Autowired
	protected ThreeRecordsComprehensiveService comprehensiveService;
	@Autowired
	protected LedgerPoorPeopleComprehensiveService ledgerPoorPeopleComprehensiveService;
	@Autowired
	protected LedgerSteadyWorkComprehensiveService ledgerSteadyWorkComprehensiveService;
	
	public Map<Integer, BigDecimal> getPeopleDetail(SearchComprehensiveVo searchVo, Organization org){
		return comprehensiveService.getPeopleDetail(searchVo, org);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, LedgerConstants.OPERATE_TYPE_SPONSOR);
	}
	
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNoticeDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, LedgerConstants.OPERATE_TYPE_NOTICE);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSupportDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, LedgerConstants.OPERATE_TYPE_SUPPORT);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsDone(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsPeriodDone(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubStanceDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsSubStanceDone(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsFeedBack(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsAssgin(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsSubmit(searchVo, orgIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		return comprehensiveService.findJurisdictionsCreateAndDone(searchVo, orgIds, page, rows);
	}
	
	@Override
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows){
		return comprehensiveService.exportCreateAndDone(searchVo, orgIds, page, rows);
	}
	
	@Override
	public Integer countfindJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows){
		return comprehensiveService.countfindJurisdictionsCreateAndDone(searchVo, orgIds, page, rows);
	}
	
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return comprehensiveService.exportCataLog(searchVo, orgIds, page, rows);
	}
	
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return comprehensiveService.countCataLog(searchVo, orgIds, page, rows);
	}
	
	
	
	
	
	
	@Override
	public Map<Integer, BigDecimal> getPoorPeopleDetail(SearchComprehensiveVo searchVo, Organization org){
		return ledgerPoorPeopleComprehensiveService.getPoorPeopleDetail(searchVo, org);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorNeedDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType, LedgerConstants.OPERATE_TYPE_SPONSOR);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorNoticeDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType, LedgerConstants.OPERATE_TYPE_NOTICE);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorSupportDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType, LedgerConstants.OPERATE_TYPE_SUPPORT);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorFeedBack(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsFeedBack(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorSubStanceDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsSubStanceDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorPeriodDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsPeriodDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorAssgin(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsAssgin(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorSubmit(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsSubmit(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPoorCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType) {
		return ledgerPoorPeopleComprehensiveService.findJurisdictionsCreateAndDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess, sourceType);
	}
	
	@Override
	public Integer countPoorPeopleCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows){
		return ledgerPoorPeopleComprehensiveService.countPoorPeopleCreateAndDone(searchVo, orgIds, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsViewObject> exportPoorPeopleCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerPoorPeopleComprehensiveService.exportPoorPeopleCreateAndDone(searchVo, orgIds, page, rows);
	}
	
	@Override
	public PageInfo<ThreeRecordsCatalogVo> exportPoorPeopleCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerPoorPeopleComprehensiveService.exportCataLog(searchVo, orgIds, page, rows);
	}
	
	@Override
	public Integer countPoorPeopleCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerPoorPeopleComprehensiveService.countCataLog(searchVo, orgIds, page, rows);
	}
	
	
	
	
	
	
	
	@Override
	public Map<Integer, BigDecimal> getSteadyWorkDetail(SearchComprehensiveVo searchVo, Organization org){
		return ledgerSteadyWorkComprehensiveService.getSteadyWorkDetail(searchVo, org);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkNeedDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, LedgerConstants.OPERATE_TYPE_SPONSOR);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkNoticeDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, LedgerConstants.OPERATE_TYPE_NOTICE);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkSupportDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsNeedDo(searchVo, orgIds, page, rows, functionalorgType, viewProcess, LedgerConstants.OPERATE_TYPE_SUPPORT);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkFeedBack(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsFeedBack(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkSubStanceDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsSubstanceDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkPeriodDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsPeriodDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkAssgin(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsAssgin(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkSubmit(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsSubmit(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSteadyWorkCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess) {
		return ledgerSteadyWorkComprehensiveService.findJurisdictionsCreateAndDone(searchVo, orgIds, page, rows, functionalorgType, viewProcess);
	}
	@Override
	public Integer countSteadyWorkCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerSteadyWorkComprehensiveService.countPoorPeopleCreateAndDone(searchVo, orgIds, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsViewObject> exportSteadyWorkCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerSteadyWorkComprehensiveService.exportPoorPeopleCreateAndDone(searchVo, orgIds, page, rows);
	}
	
	@Override
	public PageInfo<ThreeRecordsCatalogVo> exportSteadyWorkCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerSteadyWorkComprehensiveService.exportCataLog(searchVo, orgIds, page, rows);
	}
	
	@Override
	public Integer countSteadyWorkCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		return ledgerSteadyWorkComprehensiveService.countCataLog(searchVo, orgIds, page, rows);
	}
}
