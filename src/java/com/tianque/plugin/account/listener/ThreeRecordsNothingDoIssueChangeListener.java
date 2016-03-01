package com.tianque.plugin.account.listener;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

/**
 * 台账监听器的默认实现
 */
public abstract class ThreeRecordsNothingDoIssueChangeListener extends
		AbstractBaseService implements ThreeRecordsIssueChangeListener {

	protected String getCurrentLoginedUserName() {
		return ThreadVariable.getSession().getUserRealName();
	}

	protected Organization getCurrentLoginedOrganization() {
		return ThreadVariable.getOrganization();
	}

	protected boolean currentOrgChanged(ThreeRecordsIssueChangeEvent event) {
		return ThreeRecordsIssueOperate.SUBMIT.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.ASSIGN.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.REPORT_TO
						.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.GIVETO.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.BACK.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.TURN.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.DECLARE.equals(event.getOperate())
				|| ThreeRecordsIssueOperate.SUPPORT.equals(event.getOperate());
	}

	@Override
	public void onChanged(BaseWorking issue, ThreeRecordsIssueStepGroup steps,
			ThreeRecordsIssueChangeEvent event) {
	}

	@Override
	public void onComplete(BaseWorking issue, ThreeRecordsIssueStep step,
			ThreeRecordsIssueChangeEvent event) {
	}

	@Override
	public void onEntry(BaseWorking issue, ThreeRecordsIssueStep step) {
	}

	@Override
	public void beforeRemove(BaseWorking issue) {
	}

}
