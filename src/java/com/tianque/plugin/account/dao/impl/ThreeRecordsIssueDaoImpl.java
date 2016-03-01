package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueAttachFileType;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.dao.ThreeRecordsIssueDao;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.LedgerAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.DealYearOrMonthUtil;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

@Repository("threeRecordsIssueDao")
public class ThreeRecordsIssueDaoImpl extends AbstractBaseDao implements
		ThreeRecordsIssueDao {

	private PageInfo<ThreeRecordsIssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<ThreeRecordsIssueViewObject> result = new PageInfo<ThreeRecordsIssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	/**
	 * 统计已办的台账数量
	 * 
	 * @param params
	 * @return
	 */
	public int getMyDoneCount(Map params) {
		return getJurisdictionsDone(params);
	}

	private int getJurisdictionsFollow(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsFollow", map);
	}

	@Override
	public int getJurisdictionsFeedBack(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsFeedBack", map);
	}

	@Override
	public int getJurisdictionsDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsDone", map);
	}

	@Override
	public int getJurisdictionsPeriodDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsPeriodDone", map);
	}

	@Override
	public int getJurisdictionsSubstanceDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsSubstanceDone", map);
	}

	@Override
	public int getJurisdictionsSubmit(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsSubmit", map);
	}

	/**
	 * 查询待办的数量
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public int getJurisdictionsNeedDoCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsNeedDo", map);
	}

	@Override
	public int getJurisdictionsAssginCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsAssgin", map);
	}

	private LedgerAttachFile constractAttachFile(Long objId,
			ThreeRecordsIssueAttachFile file) {
		LedgerAttachFile attachFile = new LedgerAttachFile();
		attachFile.setModuleKey(LedgerConstants.MODULE_PLATFORMACCOUNT_TYPE);
		attachFile.setModuleObjectId(objId.toString());
		attachFile.setName(file.getFileName());
		attachFile.setPhysicsFullFileName(file.getFileActualUrl());
		return attachFile;
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> loadIssueAttachFilesByIssueAndLog(
			Long ledgerId, Long logId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", ledgerId);
		if (null != logId) {
			params.put("issueLogId", logId);
		}
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.loadIssueAttachFilesByIssueAndLog", params);
	}

	@Override
	public BaseWorking getSimpleBaseWorkByStepId(Long stepId) {
		return (BaseWorking) getSqlMapClientTemplate().queryForObject(
				"threeRecords.getSimpleBaseWorkByStepId", stepId);
	}

	@Override
	public ThreeRecordsIssueAttachFile getIssueAttachFileById(Long id) {
		return (ThreeRecordsIssueAttachFile) getSqlMapClientTemplate()
				.queryForObject("threeRecords.getIssueAttachFileById", id);
	}

	public List<ThreeRecordsIssueAttachFile> getIssueAttachFileByIssueId(
			Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.getIssueAttachFileByIssueId", issueId);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (org != null && org.getOrgInternalCode() != null) {
			map.put("orgCode", org.getOrgInternalCode());
		}
		if (org != null && org.getId() != null) {
			map.put("orgId", org.getId());
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsFollow(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsFollow", map, (page - 1) * rows,
				rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (org != null && org.getOrgInternalCode() != null) {
			map.put("orgCode", org.getOrgInternalCode());
		}
		if (org != null && org.getId() != null) {
			map.put("orgId", org.getId());
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("verification", ThreeRecordsIssueState.VERIFICATION);// 台账表中已验证状态，值为300
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
		map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);// 台账流程表中阶段办结的状态，值为600
		map.put("issueCompleteCode", ThreeRecordsIssueState.SUBSTANCE_CODE);// 台账流程表中已实质办结的状态，值为700
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsFeedBack(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsFeedBack", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		map.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
		map.put("completeStatus", ThreeRecordsIssueState.COMPLETE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsDone", map, (page - 1) * rows,
				rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCreateAndDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsCreateAndDone", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubStanceDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("seachValue", seachValue);
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubstanceDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsSubstanceDone", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("seachValue", seachValue);
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.PERIOD_CODE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsPeriodDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsPeriodDone", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer isSupported, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("isSupported", isSupported);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsNeedDo", map, (page - 1) * rows,
				rows));

		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("assgin", ThreeRecordsIssueSourceState.assign);
		map.put("issueTag", ThreeRecordsIssueTag.ASSIGN_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsAssgin", map, (page - 1) * rows,
				rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (seachValue != null) {
			map.put("seachValue", seachValue);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("submit", ThreeRecordsIssueSourceState.submit);
		map.put("issueTag", ThreeRecordsIssueTag.SUBMIT_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"threeRecords.findJurisdictionsSubmit", map, (page - 1) * rows,
				rows));
		return result;
	}

	@Override
	public List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", issueId);
		map.put("ledgerType", ledgerType);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.searchIssueStepsByIssueId", map);
	}

	@Override
	public List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId) {
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.searchAllIssueStepsByStepId", stepId);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> loadIssueAttachFilesByLedgerIdAndLedgerType(
			Long ledgerId, int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", ledgerId);
		params.put("ledgerType", type);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.loadLedgerAndLogAttachFilesByLedgerIdAndType",
				params);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> loadLedgerAttachFilesByLedgerIdAndType(
			Long ledgerId, int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", ledgerId);
		params.put("ledgerType", type);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.loadLedgerAttachFilesByLedgerIdAndType", params);
	}

	@Override
	public Long addIssueAttachFile(ThreeRecordsIssueAttachFile file) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", file.getLedgerId());
		params.put("ledgerType", file.getLedgerType());
		if (file.getReplyForm() != null) {
			params.put("fileType",
					ThreeRecordsIssueAttachFileType.REPLY_FORM_FILE);
			params.put("replyFormId", file.getReplyForm().getId());
		} else if (file.getIssueLog() == null) {
			params.put("fileType", ThreeRecordsIssueAttachFileType.FILE);
		} else {
			params.put("fileType", ThreeRecordsIssueAttachFileType.LOG_FILE);
			params.put("issueLogId", file.getIssueLog().getId());
		}
		return (Long) getSqlMapClientTemplate().insert(
				"threeRecords.addIssueAttachFiles", params);
	}

	@Override
	public void addAttachFile(ThreeRecordsIssueAttachFile file, Long id) {
		getSqlMapClientTemplate().insert("threeRecords.addAttachFile",
				constractAttachFile(id, file));
	}
	
	@Override
	public void addAttachFile(LedgerAttachFile file) {
		getSqlMapClientTemplate().insert("threeRecords.addAttachFile", file);
	}
	
	@Override
	public List<ThreeRecordsIssueAttachFile> getAccounthasattachfilesByLedgerIdAndLedgerType(int ledgerType, Long ledgerId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", ledgerId);
		params.put("ledgerType", ledgerType);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.getAccounthasattachfilesByLedgerIdAndLedgerType", params);
	}
	@Override
	public List<ThreeRecordsIssueAttachFile> getAccounthasattachfilesByLogId(Long logId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logId", logId);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.getAccounthasattachfilesByLogId", params);
	}
	@Override
	public List<LedgerAttachFile> getLedgerattachfilesByModuleObjectId(String moudleObjectId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("moduleObjectId", moudleObjectId);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.getLedgerattachfilesByModuleObjectId", params);
	}

	@Override
	public void removeAllAttachFilesFromBaseFile(Long issueId, int ledgerType) {
		Map map = new HashMap();
		map.put("ledgerId", issueId);
		map.put("ledgerType", ledgerType);
		getSqlMapClientTemplate().delete(
				"threeRecords.removeAllIssueAttachFilesFromBaseFile", map);
	}

	@Override
	public void removeAllAttachFiles(Long issueId, int ledgerType) {
		Map map = new HashMap();
		map.put("ledgerId", issueId);
		map.put("ledgerType", ledgerType);
		getSqlMapClientTemplate().delete(
				"threeRecords.removeAllIssueAttachFiles", map);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> findReplyFormFiles(Long ledgerId,
			int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ledgerId", ledgerId);
		params.put("ledgerType", type);
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.findReplyFormFiles", params);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> findReplyFormFilesByReplyId(
			Long replyFormId) {
		return getSqlMapClientTemplate().queryForList(
				"threeRecords.findReplyFormFilesByReplyId", replyFormId);
	}

	@Override
	public int getJurisdictionsCreateAndDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"threeRecords.countJurisdictionsCreateAndDone", map);
	}

}
