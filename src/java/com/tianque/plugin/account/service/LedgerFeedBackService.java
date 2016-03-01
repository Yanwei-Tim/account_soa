package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.LedgerFeedBack;

public interface LedgerFeedBackService {

	/**
	 * 验证是否已经存在评论
	 * 
	 * @param stepId
	 * */
	@Deprecated
	public Boolean isCanLedgerFeedBackByStepId(Long stepId);

	/**
	 * 根据类型和ledgerId查询对应的反馈评论
	 * 
	 * @param ledgerId
	 * @param ledgerType
	 * */
	public List<LedgerFeedBack> getLedgerFeedByLedgerIdAndType(Long ledgerId,
			int ledgerType);

	/**
	 * 新增反馈评论
	 * 
	 * @param ledgerFeedBack
	 * */
	public boolean addLedgerFeedBack(LedgerFeedBack ledgerFeedBack);

	/**
	 * 是否可以评论
	 * 
	 * @param ledgerId
	 * @param ledgerType
	 * @return
	 */
	public Boolean isCanLedgerFeedBackByLedgerIdAndType(Long ledgerId,
			int ledgerType);
}
