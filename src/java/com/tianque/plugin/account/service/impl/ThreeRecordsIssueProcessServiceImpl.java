package com.tianque.plugin.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.ThreeRecordsIssueProcessDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.ThreeRecordsIssueProcessService;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.state.impl.ThreeRecordsIssueCompleteState;
import com.tianque.plugin.account.state.impl.ThreeRecordsIssueProgramCompleteState;
import com.tianque.plugin.account.state.impl.ThreeRecordsStepCompleteState;

@Service("ThreeRecordsIssueProcessServiceImpl")
@Transactional
public class ThreeRecordsIssueProcessServiceImpl implements
		ThreeRecordsIssueProcessService {
	@Autowired
	protected ThreeRecordsIssueProcessDao issueProcessDao;

	@Override
	public ThreeRecordsIssueStep addLedgerStep(ThreeRecordsIssueStep step) {
		if (step == null || step.getLedgerId() == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法addLedgerStep，台账编号不能为空");
		}
		try {
			return issueProcessDao.addLedgerStep(step);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤添加失败!", e);
		}
	}

	@Override
	public void deleteLedgerStepsByIdAndType(Long ledgerId, int ledgerType) {
		if (ledgerId == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法deleteLedgerStepsByIdAndType，台账编号不能为空");
		}
		Map map = new HashMap();
		map.put("ledgerId", ledgerId);
		map.put("ledgerType", ledgerType);
		try {
			issueProcessDao.deleteLedgerStepsByIdAndType(map);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤删除失败!", e);
		}
	}

	@Override
	public void deleteLedgerStepGroupsByIdAndType(Long ledgerId, int ledgerType) {
		if (ledgerId == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法deleteLedgerStepGroupsByIdAndType，台账编号不能为空");
		}
		Map map = new HashMap();
		map.put("ledgerId", ledgerId);
		map.put("ledgerType", ledgerType);
		try {
			issueProcessDao.deleteLedgerStepGroupsByIdAndType(map);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤删除失败!", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep getSimpleIssueStepById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("台账编号不能为空");
		}
		return issueProcessDao.getSimpleIssueStepById(id);
	}

	@Override
	public ThreeRecordsIssueStep updateIssueStepExceptOrg(
			ThreeRecordsIssueStep step) {
		if (step == null || step.getId() == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法updateIssueStepExceptOrg，步骤编号不能为空");
		}
		try {
			return issueProcessDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤修改失败!", e);
		}
	}

	@Override
	public boolean updateGroupId(ThreeRecordsIssueStep step) {
		if (step == null || step.getId() == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法updateGroupId，步骤编号不能为空");
		}
		try {
			return issueProcessDao.updateGroupId(step);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤修改失败!", e);
		}

	}

	@Override
	public ThreeRecordsIssueStepGroup addIssueStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup) {
		if (issueStepGroup == null || issueStepGroup.getLedgerId() == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法addIssueStepGroup,台账编号不能为空");
		}
		try {
			return issueProcessDao.addIssueStepGroup(issueStepGroup);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤日志新增失败!", e);
		}

	}

	@Override
	public ThreeRecordsIssueStepGroup getSimpleIssueStepGroupById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("台账编号不能为空");
		}
		return issueProcessDao.getSimpleIssueStepGroupById(id);
	}

	@Override
	public List<ThreeRecordsIssueStepGroup> getIssueStepGroupByIssueId(Long id,
			int ledgerType) {
		if (id == null) {
			throw new BusinessValidationException("台账编号不能为空");
		}
		return issueProcessDao.getIssueStepGroupByIssueId(id, ledgerType);
	}

	@Override
	public boolean updateOutLog(ThreeRecordsIssueStepGroup issueStepGroup) {
		if (issueStepGroup == null || issueStepGroup.getId() == null) {
			throw new BusinessValidationException(
					"类ThreeRecordsIssueProcessServiceImpl方法updateOutLog，步骤组编号不能为空");
		}
		try {
			return issueProcessDao.updateOutLog(issueStepGroup);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤日志修改失败!", e);
		}

	}

	@Override
	public ThreeRecordsIssueMap getIssueMapByStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup) {
		if (issueStepGroup == null || issueStepGroup.getLedgerId() == null) {
			throw new BusinessValidationException("台账编号不能为空");
		}
		return issueProcessDao.getIssueMapByStepGroup(issueStepGroup);
	}

	@Override
	public void updateStateAndCode(Long ledgerId, int ledgerType) {
		if (ledgerId == null) {
			return;
		}
		List<ThreeRecordsIssueStep> steps = issueProcessDao
				.getIssueStepByLedgerIdAndType(ledgerId, ledgerType);
		if (steps == null || steps.size() == 0) {
			return;
		}
		ThreeRecordsIssueStep currentStep = null;
		int length = steps.size();
		for (int i = 0; i < length; i++) {
			currentStep = steps.get(i);
			if (i != 0) {
				currentStep.setBackTo(steps.get(i - 1));
			}
			if (i == length - 1) {
				if (currentStep.getState().equals(
						ThreeRecordsStepCompleteState.class.getName())) {
					currentStep
							.setState(ThreeRecordsIssueProgramCompleteState.class
									.getName());
				}
			} else {
				if (currentStep.getState().equals(
						ThreeRecordsIssueCompleteState.class.getName())) {
					currentStep.setState(ThreeRecordsStepCompleteState.class
							.getName());
					currentStep
							.setStateCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
					currentStep.setDealType(Long
							.valueOf(ThreeRecordsIssueOperate.PROGRAM_COMPLETE
									.getCode()));
				} else {
					// he.sm 2015/09/30 数据导入时，之前的步骤stateCode未改应对，按现在的业务流程应该是最后一个步骤之前的步骤的stateCode都应是500  ADD 
					currentStep.setStateCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
				}
				currentStep.setIsFeedBack(0);// he.sm 2015/09/30 数据导入时，全部都不为回退
			}
		}
		for (ThreeRecordsIssueStep step : steps) {
			// 根据操作的流程走下来的数据，台账创建者的创建步骤的isFeedBack为1
			if(step.getDealType()==null&&step.getSource().getId().equals(step.getTarget().getId())){
				step.setIsFeedBack(1);
			}
			issueProcessDao.updateIssueStepForImport(step);
		}

	}

	@Override
	public List<ThreeRecordsIssueStep> getIssueStepByGroupId(Long groupId) {
		return issueProcessDao.getIssueStepByGroupId(groupId);
	}

	@Override
	public List<ThreeRecordsIssueStep> getStepSupportByLedgerIdAndType(
			Long ledgerId, int ledgerType) {
		return issueProcessDao.getStepSupportByLedgerIdAndType(ledgerId,
				ledgerType);
	}

	@Override
	public void updateStepStateCodeBySupportOrNotice(List<Long> stepIds) {
		if (stepIds == null || stepIds.size() == 0) {
			return;
		}
		try {
			issueProcessDao.updateStepStateCodeBySupportOrNotice(stepIds);
		} catch (Exception e) {
			throw new ServiceValidationException("台账步骤更新协办，抄告失败!", e);
		}
	}

}
