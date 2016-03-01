package com.tianque.plugin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.dao.SearchPoorPeopleByLevelDao;
import com.tianque.plugin.account.service.SearchPoorPeopleByLevelService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchLedgerPoorPeopleVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("searchPoorPeopleByLevelService")
public class SearchPoorPeopleByLevelServiceImpl implements
		SearchPoorPeopleByLevelService {
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	protected SearchPoorPeopleByLevelDao searchPoorPeopleByLevelDao;

	private void setOrderField(SearchLedgerPoorPeopleVo searchVo, String sidx,
			String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
	}

	private Organization setOrgCode(SearchLedgerPoorPeopleVo searchVo) {
		Organization org = organizationDubboService.getSimpleOrgById(searchVo
				.getTargeOrgId());
		if (org != null && org.getOrgInternalCode() != null) {
			org = ThreeRecordsIssueOperationUtil.setQueryOrg(org);
			searchVo.setTargeOrgInternalCode(org.getOrgInternalCode());
			searchVo.setOrgId(org.getId());
			searchVo.setOrgCode(org.getOrgInternalCode());
			searchVo.setOrgLevel(org.getOrgLevel().getId());
			searchVo.setTargetOrgs(organizationDubboService
					.findOrganizationsByParentId(org.getId()));
		}
		return org;

	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsAssgin(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCompleted(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsCompleted(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsDone(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsNeedDo(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsSubmit(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findCompletedIssueList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findCompletedIssueList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsFeedBack(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsFollow(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findPeriodCompletedList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findPeriodCompletedList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return ThreeRecordsIssueOperationUtil.createEmptyIssueVoPageInfo(
					rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerPoorPeopleVo> pageInfo = searchPoorPeopleByLevelDao
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows);
		return pageInfo;
	}

}
