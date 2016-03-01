package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.ReplyFormDao;
import com.tianque.plugin.account.domain.ReplyForm;
import com.tianque.plugin.account.vo.ReplyFormVo;

@Repository("replyFormDao")
public class ReplyFormDaoImpl extends AbstractBaseDao implements ReplyFormDao {

	@Override
	public ReplyForm addReplyForm(ReplyForm replyForm) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"replyForm.addReplyForm", replyForm);

		return getSimpleReplyFormById(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ReplyForm> findReplyForms(ReplyFormVo replyFormVo,
			Integer page, Integer rows) {
		int totalRecord = (Integer) getSqlMapClientTemplate().queryForObject(
				"replyForm.countFindReplyForms", replyFormVo);
		PageInfo<ReplyForm> result = new PageInfo<ReplyForm>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"replyForm.findReplyForms", replyFormVo, (page - 1) * rows,
				rows));

		return result;
	}

	@Override
	public ReplyForm updateReplyForm(ReplyForm replyForm) {
		getSqlMapClientTemplate()
				.update("replyForm.updateReplyForm", replyForm);
		return replyForm;
	}

	@Override
	public ReplyForm getSimpleReplyFormById(Long id) {
		return (ReplyForm) getSqlMapClientTemplate().queryForObject(
				"replyForm.getSimpleReplyFormById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReplyForm> getSimpleReplyFormByLedgerIdAndType(Long id, int type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", id);
		map.put("ledgerType", type);
		return getSqlMapClientTemplate().queryForList(
				"replyForm.getSimpleReplyFormByLedgerIdAndType", map);
	}

}
