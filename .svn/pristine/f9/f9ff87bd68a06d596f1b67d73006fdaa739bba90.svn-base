package com.tianque.account.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReplyForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.vo.ReplyFormVo;

public interface ReplyFormDubboService {
	/**
	 * 新增回复单
	 * 
	 * @param replyForm
	 * @return
	 */
	public ReplyForm addReplyForm(ReplyForm replyForm);

	/**
	 * 更新回复单
	 * 
	 * @param replyForm
	 * @return
	 */
	public ReplyForm updateReplyForm(ReplyForm replyForm);

	/**
	 * 查询回复单
	 * 
	 * @param replyFormVo
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<ReplyForm> findReplyForms(ReplyFormVo replyFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取回复单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public ReplyForm getSimpleReplyFormById(Long id);

	/*
	 * 根据台账id和类型获取回复单
	 * 
	 * @param id
	 * 
	 * @param type
	 * 
	 * @return
	 */
	public List<ReplyForm> getSimpleReplyFormByLedgerIdAndType(Long id, int type);

	/**
	 * 新增回复单
	 * 
	 * @param replyForm
	 * @param attachFiles
	 * @return
	 */
	public ReplyForm addReplyForm(ReplyForm replyForm, List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 根据台账编号 类型获取回复单及附件
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<ReplyForm> getReplyFormAndFilesByLedgerIdAndType(Long id,
			int type);

	/**
	 * 根据回复单编号获取回复单和附件
	 * 
	 * @param id
	 * @return
	 */
	public ReplyForm getReplyFormAndFilesByReplyId(Long id);
}
