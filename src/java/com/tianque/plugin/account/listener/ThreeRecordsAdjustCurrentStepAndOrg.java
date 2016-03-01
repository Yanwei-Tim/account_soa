package com.tianque.plugin.account.listener;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.PeopleAspirationService;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;

/**
 * 调整台账当前处理步骤和org
 */
@Repository("threeRecordsAdjustCurrentStepAndOrg")
public class ThreeRecordsAdjustCurrentStepAndOrg extends
		ThreeRecordsNothingDoIssueChangeListener {

	@Autowired
	private PeopleAspirationService peopleAspirationService;
	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;

	@Override
	public void onEntry(BaseWorking issue, ThreeRecordsIssueStep step) {
		issue.setCurrentStep(step);
		issue.setStatus(ThreeRecordsIssueState.DEALING);
		updateIssue(issue, LedgerConstants.LEDGER_FEEDBACK_NO, false);
	}

	@Override
	public void onComplete(BaseWorking issue, ThreeRecordsIssueStep step,
			ThreeRecordsIssueChangeEvent event) {
		issue.setCurrentStep(step);
		issue.setStatus(ThreeRecordsIssueState.COMPLETE);
		updateIssue(issue, LedgerConstants.LEDGER_FEEDBACK_YES, true);
	}

	@Override
	public void onChanged(BaseWorking issue, ThreeRecordsIssueStepGroup steps,
			ThreeRecordsIssueChangeEvent event) {
		if (currentOrgChanged(event)
				|| needChangeLastOrg(event)
				|| ThreeRecordsIssueOperate.PROGRAM_COMPLETE.equals(event
						.getOperate())
				|| ThreeRecordsIssueOperate.PERIOD_COMPLETE.equals(event
						.getOperate())
				|| ThreeRecordsIssueOperate.COMPLETE.equals(event.getOperate())) {
			issue.setCurrentStep(steps.getKeyStep());
			updateIssue(issue, LedgerConstants.LEDGER_FEEDBACK_YES,
					!ThreeRecordsIssueOperate.CONCEPT
							.equals(event.getOperate()));

		} else if (ThreeRecordsIssueOperate.TMPCOMMENT.equals(event
				.getOperate())
				&& steps.getKeyStep().getIsSupported() == LedgerConstants.OPERATE_TYPE_SUPPORT) {
			updateIssue(issue, LedgerConstants.LEDGER_FEEDBACK_YES, true);
		}
	}

	private boolean needChangeLastOrg(ThreeRecordsIssueChangeEvent event) {
		return ThreeRecordsIssueOperate.CONCEPT.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.COMMENT.equals(event.getOperate());
	}

	private void updateIssue(BaseWorking issue, int canFeedback,
			boolean saveFeedBackStatus) {
		switch (issue.getLedgerType()) {
		case LedgerConstants.POORPEOPLE:
			updateLedgerPoorPeople(issue, canFeedback, saveFeedBackStatus);
			break;
		case LedgerConstants.STEADYWORK:
			updateLedgerSteadyWork(issue, canFeedback, saveFeedBackStatus);
			break;
		default:
			updateLedgerPeopleAspirations(issue, canFeedback,
					saveFeedBackStatus);
			break;
		}
	}

	private void updateLedgerPeopleAspirations(BaseWorking issue,
			int canFeedback, boolean saveFeedBackStatus) {
		LedgerPeopleAspirations peopleAspirations = new LedgerPeopleAspirations();
		BeanUtils.copyProperties(issue, peopleAspirations);
		if (saveFeedBackStatus) {
			peopleAspirations.setIsCanFeedBack(canFeedback);
		}
		peopleAspirationService
				.updateLedgerPeopleAspirationStatus(peopleAspirations);
	}

	private void updateLedgerPoorPeople(BaseWorking issue, int canFeedback,
			boolean saveFeedBackStatus) {
		LedgerPoorPeople ledgerPoorPeople = new LedgerPoorPeople();
		BeanUtils.copyProperties(issue, ledgerPoorPeople);
		if (saveFeedBackStatus) {
			ledgerPoorPeople.setIsCanFeedBack(canFeedback);
		}
		ledgerPoorPeopleService.updateLedgerPoorPeopleStatus(ledgerPoorPeople);
	}

	private void updateLedgerSteadyWork(BaseWorking issue, int canFeedback,
			boolean saveFeedBackStatus) {
		LedgerSteadyWork ledgerSteadyWork = new LedgerSteadyWork();
		BeanUtils.copyProperties(issue, ledgerSteadyWork);
		if (saveFeedBackStatus) {
			ledgerSteadyWork.setIsCanFeedBack(canFeedback);
		}
		ledgerSteadyWorkService.updateLedgerSteadyWorkStatus(ledgerSteadyWork);
	}

}
