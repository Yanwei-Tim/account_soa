package com.tianque.plugin.account.listener;

import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

/***
 * 台账监听器
 */
public interface ThreeRecordsIssueChangeListener {
	/**
	 * 台账进入时
	 * 
	 * @param issue
	 *            台账
	 * @param step
	 *            台账处理步骤
	 */

	void onEntry(BaseWorking issue, ThreeRecordsIssueStep step);

	/**
	 * 台账删除之前
	 * 
	 * @param issue
	 *            台账
	 */
	void beforeRemove(BaseWorking issue);

	/**
	 * 台账状态发生改变时
	 * 
	 * @param issue
	 *            台账
	 * @param steps
	 *            台账处理步骤组
	 * @param event
	 */
	void onChanged(BaseWorking issue, ThreeRecordsIssueStepGroup steps,
			ThreeRecordsIssueChangeEvent event);

	/**
	 * 台账结案时
	 * 
	 * @param issue
	 *            台账
	 * @param step
	 *            台账处理步骤
	 * @param event
	 */
	void onComplete(BaseWorking issue, ThreeRecordsIssueStep step,
			ThreeRecordsIssueChangeEvent event);

}
