package com.tianque.plugin.account.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReplyForm;
import com.tianque.plugin.account.vo.ReplyFormVo;

public interface ReplyFormDao {

	/**
	 * 新增回复单
	 * 
	 * @param replyForm
	 * */
	public ReplyForm addReplyForm(ReplyForm replyForm);

	/**
	 * 更新回复单
	 * 
	 * @param replyForm
	 * */
	public ReplyForm updateReplyForm(ReplyForm replyForm);

	/**
	 * 查询回复单
	 * 
	 * */
	public PageInfo<ReplyForm> findReplyForms(ReplyFormVo replyFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取回复单
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

}
