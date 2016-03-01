package com.tianque.plugin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.SearchSteadyWorkByLevelDao;
import com.tianque.plugin.account.service.SearchSteadyWorkByLevelService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("searchSteadyWorkByLevelService")
public class SearchSteadyWorkByLevelServiceImpl implements
		SearchSteadyWorkByLevelService {
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	protected SearchSteadyWorkByLevelDao searchSteadyWorkByLevelDao;

	private void setOrderField(SearchLedgerSteadyWorkVo searchVo, String sidx,
			String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
	}

	private Organization setOrgCode(SearchLedgerSteadyWorkVo searchVo) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
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
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findJurisdictionsAssgin(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCompleted(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		try {
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
					.findJurisdictionsCompleted(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办结事件查询出错", e);
		}
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		try {
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
					.findJurisdictionsDone(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		try {
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
					.findJurisdictionsNeedDo(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findJurisdictionsSubmit(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findCompletedIssueList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findCompletedIssueList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findJurisdictionsFeedBack(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFollow(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findJurisdictionsFollow(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findPeriodCompletedList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findPeriodCompletedList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {

		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<LedgerSteadyWorkVo> pageInfo = searchSteadyWorkByLevelDao
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows);
		return pageInfo;

	}
}
