package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.ThreeRecordsIssueLogDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.ThreeRecordsIssueLogService;
import com.tianque.plugin.account.validate.ThreeRecordsIssueOperationLogValidator;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("threeRecordsIssueLogServiceImpl")
@Transactional
public class ThreeRecordsIssueLogServiceImpl implements
		ThreeRecordsIssueLogService {
	@Autowired
	private ThreeRecordsIssueLogDao threeRecordsIssueLogDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	@Qualifier("threeRecordsDefaultIssueLogValidator")
	private ThreeRecordsIssueOperationLogValidator logValidator;

	@Override
	public ThreeRecordsIssueLogNew addLog(ThreeRecordsIssueLogNew log) {
		ValidateResult result = logValidator.validate(log, null);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			if(log.getDealOrg().getOrgInternalCode() == null){
				log.setDealOrg(organizationDubboService.getFullOrgById(log.getDealOrg().getId()));
			}
			return threeRecordsIssueLogDao.addLog(log);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsIssueLogServiceImpl的addLog方法出现异常，原因：",
					"新增操作措施信息出现错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueLogNew updateLog(ThreeRecordsIssueLogNew log) {
		ValidateResult result = logValidator.validate(log, null);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			return threeRecordsIssueLogDao.updateLog(log);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsIssueLogServiceImpl的updateLog方法出现异常，原因：",
					"更新操作措施信息出现错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueLogNew getLogById(Long id) {
		return threeRecordsIssueLogDao.getIssueLogById(id);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> loadOperationLogsByIssueId(Long id,
			Long ledgerType) {
		return threeRecordsIssueLogDao.loadIssueOperationLogsByIssueId(id,
				ledgerType);
	}

	@Override
	public void deleteIssueLogByLedgerIdAndLedgerType(Long ledgerId,
			int ledgerType) {
		try {
			threeRecordsIssueLogDao.deleteIssueLogByIssueId(ledgerId,
					ledgerType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsIssueLogServiceImpl的deleteIssueLogByLedgerIdAndLedgerType方法出现异常，原因：",
					"删除操作措施信息出现错误", e);
		}

	}

	@Override
	public List<ThreeRecordsIssueLogNew> getLogsByStepId(Long id) {
		return threeRecordsIssueLogDao.getIssueLogsByStepId(id);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> findDealLogByMapAndStepGroup(
			ThreeRecordsIssueMap issueMap, ThreeRecordsIssueStepGroup group) {
		return threeRecordsIssueLogDao
				.findIssueDealLogByIssueMapAndIssueStepGroup(issueMap, group);
	}

}
