package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.TurnFormDao;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.vo.TurnFormVo;

@Repository("turnFormDao")
public class TurnFormDaoImpl extends AbstractBaseDao implements TurnFormDao {

	@Override
	public TurnForm addTurnForm(TurnForm turnForm) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"turnForm.addTurnForm", turnForm);

		return getSimpleTurnFormById(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<TurnForm> findTurnForms(TurnFormVo turnFormVo,
			Integer page, Integer rows) {
		int totalRecord = (Integer) getSqlMapClientTemplate().queryForObject(
				"turnForm.countFindTurnForms", turnFormVo);
		PageInfo<TurnForm> result = new PageInfo<TurnForm>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"turnForm.findTurnForms", turnFormVo, (page - 1) * rows, rows));

		return result;
	}

	@Override
	public TurnForm updateTurnForm(TurnForm turnForm) {
		getSqlMapClientTemplate().update("turnForm.updateTurnForm", turnForm);
		return turnForm;
	}

	@Override
	public TurnForm getSimpleTurnFormById(Long id) {
		return (TurnForm) getSqlMapClientTemplate().queryForObject(
				"turnForm.getSimpleTurnFormById", id);
	}

	@Override
	public TurnForm getSimpleTurnFormByStepId(Long id) {
		return (TurnForm) getSqlMapClientTemplate().queryForObject(
				"turnForm.getSimpleTurnFormByStepId", id);
	}

}
