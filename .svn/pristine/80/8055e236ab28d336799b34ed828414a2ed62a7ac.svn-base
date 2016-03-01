package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.LedgerFeedBackDao;
import com.tianque.plugin.account.domain.LedgerFeedBack;

@Repository("ledgerFeedBackDao")
public class LedgerFeedBackDaoImp extends AbstractBaseDao implements
		LedgerFeedBackDao {

	@Override
	public boolean addLedgerFeedBack(LedgerFeedBack ledgerFeedBack) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"ledgerFeedBack.addFeedBack", ledgerFeedBack);
		return id != null && id > 0;
	}

	@Override
	public List<LedgerFeedBack> getLedgerFeedByLedgerIdAndType(Long ledgerId,
			int ledgerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", ledgerId);
		map.put("ledgerType", ledgerType);
		return getSqlMapClientTemplate().queryForList(
				"ledgerFeedBack.getFeedBackByledgerIdAndType", map);
	}

	@Override
	public Boolean isCanLedgerFeedBackByStepId(Long stepId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stepId", stepId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerFeedBack.countFeedBack", map) == 0;

	}

}
