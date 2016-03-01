package com.tianque.plugin.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.AcceptFormDao;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.vo.AcceptFormVo;

@Repository("acceptFormDao")
public class AcceptFormDaoImpl extends AbstractBaseDao<AcceptForm> implements AcceptFormDao {

	@Override
	public AcceptForm addAcceptForm(AcceptForm acceptForm) {
		Long id  = (Long) getSqlMapClientTemplate().insert("acceptForm.addAcceptForm", acceptForm);
		return getSimpleAcceptFormById(id);
	}

	@Override
	public AcceptForm updateAcceptForm(AcceptForm acceptForm) {
		getSqlMapClientTemplate().update("acceptForm.updateAcceptForm", acceptForm);
		return acceptForm;
	}

	@Override
	public PageInfo<AcceptForm> findAcceptForms(AcceptFormVo acceptFormVo,
			Integer page, Integer rows) {
		int total = (Integer) getSqlMapClientTemplate().queryForObject("acceptForm.countFindAcceptForms", acceptFormVo);
		PageInfo<AcceptForm> pageInfo = new PageInfo<AcceptForm>();
		pageInfo.setTotalRowSize(total);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(getSqlMapClientTemplate().queryForList("acceptForm.findAcceptForms", acceptFormVo, (page - 1) * rows, rows));
		return pageInfo;
	}

	@Override
	public AcceptForm getSimpleAcceptFormById(Long id) {
		return (AcceptForm) getSqlMapClientTemplate().queryForObject("acceptForm.getSimpleAcceptFormById", id);
	}

	@Override
	public List<AcceptForm> findSimpleAcceptFormByStepId(Long id) {
		return (List) getSqlMapClientTemplate().queryForList(
				"acceptForm.getSimpleAcceptFormByStepId", id);
	}

}
