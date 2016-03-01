package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;

public interface LedgerPoorPeopleDao {

	public LedgerPoorPeople getLedgerPoorPeopleById(Long id);
	
	public List<LedgerPoorPeople> needTurnLedgerPoorPeople(Map<String, Object> map);

	public Long addLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople);
	
	public LedgerPoorPeople addTurnLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople);

	public void updateLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople);

	public void updateLedgerCurrentStepAndOrg(LedgerPoorPeople ledgerPoorPeople);

	public void deleteLedgerPoorPeopleById(Long id);

	public void deletePoorPeopleByIds(String[] ids);

	public void updateLedgerPoorPeopleStatus(LedgerPoorPeople ledgerPoorPeople);

	public LedgerPoorPeople findByIdCardNo(String idCardNo);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer isSupported, Integer year, Integer month);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(Organization org,
			List<Long> childOrg, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubStanceDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(String seachValue,
			Organization org, List<Long> childOrg, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, Integer year, Integer month);

	public int getJurisdictionsNeedDoCount(Map<String, Object> map);

	public BaseWorking getSimpleBaseWorkByStepId(Long stepId);

	public List<LedgerPoorPeople> getLedgerPoorPeopleByHistoryId(String id);

	/**
	 * 更新困难反馈信息状态
	 * 
	 * @param ledgerId
	 * @param isFeedBack
	 */
	public void updateFeedBackStatus(Long ledgerId, int isFeedBack);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	/**
	 * 统计台账数，数据导入用，判断是否已经导入过
	 * @param oldHistoryId
	 * @return
	 */
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode);

	public int getJurisdictionsDone(Map<String, Object> map);

	public int getJurisdictionsPeriodDone(Map<String, Object> map);

	public int getJurisdictionsSubstanceDone(Map<String, Object> map);

	public int getJurisdictionsCreateAndDone(Map<String, Object> map);

	public int getJurisdictionsFeedBack(Map<String, Object> map);

	public int getJurisdictionsAssginCount(Map<String, Object> map);

	public int getJurisdictionsSubmit(Map<String, Object> map);
}
