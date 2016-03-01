package com.tianque.plugin.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.listener.ThreeRecordsIssueChangeListener;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.state.impl.ThreeRecordsDealingState;

@Service("threeRecordsDefaultIssueWorkFlowEngine")
@Transactional
public class ThreeRecordsDefaultIssueWorkFlowEngineImpl extends
		ThreeRecordsAbstractIssueWorkFlowEngineImpl {

	@Autowired
	@Qualifier("threeRecordsDefaultIssueListeners")
	private ArrayList<ThreeRecordsIssueChangeListener> listeners;

	@Override
	protected ThreeRecordsIssueStep createEntryIssueStep(BaseWorking baseWork,
			Long sourceKindId) {
		ThreeRecordsIssueStep step = new ThreeRecordsIssueStep();
		step.setState(ThreeRecordsDealingState.class.getName());
		step.setStateCode(ThreeRecordsIssueState.DEALING_CODE);
		step.setSource(baseWork.getCreateOrg());
		step.setEntryDate(baseWork.getCreateDate());
		step.setLastDealDate(step.getEntryDate());
		step.setTarget(baseWork.getCreateOrg());
		step.setLedgerId(baseWork.getId());
		step.setLedgerType(baseWork.getLedgerType());
		return step;
	}

	@Override
	protected List<ThreeRecordsIssueChangeListener> getIssueChangeListeners() {
		return listeners;
	}

}
