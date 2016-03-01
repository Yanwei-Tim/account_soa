package com.tianque.plugin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.SearchPeopleAspirationByLevelDao;
import com.tianque.plugin.account.service.SearchPeopleAspirationByLevelService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("searchPeopleAspirationByLevelService")
public class SearchPeopleAspirationByLevelServiceImpl implements
		SearchPeopleAspirationByLevelService {
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	protected SearchPeopleAspirationByLevelDao searchPeopleAspirationByLevelDao;

	private PageInfo<ThreeRecordsIssueViewObject> createEmptyIssueVoPageInfo(
			int pageSize, int pageIndex) {
		PageInfo<ThreeRecordsIssueViewObject> result = new PageInfo<ThreeRecordsIssueViewObject>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private void setOrderField(SearchPeopleAspirationVo searchVo, String sidx,
			String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
	}

	private Organization setOrgCode(SearchPeopleAspirationVo searchVo) {
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
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findJurisdictionsAssgin(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCompleted(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			if (searchVo == null || searchVo.getTargeOrgId() == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
					.findJurisdictionsCompleted(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办结事件查询出错", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		try {
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
					.findJurisdictionsDone(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		try {
			Organization org = setOrgCode(searchVo);
			searchVo.setSeachValue(ThreeRecordsIssueOperationUtil
					.setSeachValue(searchVo.getSeachValue(), org));
			setOrderField(searchVo, sidx, sord);
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
					.findJurisdictionsNeedDo(searchVo, page, rows);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findJurisdictionsSubmit(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findCompletedIssueList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findCompletedIssueList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findJurisdictionsFeedBack(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findJurisdictionsFollow(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findPeriodCompletedList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findPeriodCompletedList(searchVo, page, rows);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDoneList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchVo == null || searchVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchVo);
		searchVo.setSeachValue(ThreeRecordsIssueOperationUtil.setSeachValue(
				searchVo.getSeachValue(), org));
		setOrderField(searchVo, sidx, sord);
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = searchPeopleAspirationByLevelDao
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows);
		return pageInfo;
	}
}
