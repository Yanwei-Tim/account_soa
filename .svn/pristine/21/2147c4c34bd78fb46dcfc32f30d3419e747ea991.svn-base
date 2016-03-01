package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.DeclareFormDao;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.vo.DeclareFormVo;

@Repository("declareFormDao")
public class DeclareFormDaoImpl extends AbstractBaseDao implements
		DeclareFormDao {

	@Override
	public DeclareForm addDeclareForm(DeclareForm declareForm) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"declareForm.addDeclareForm", declareForm);

		return getSimpleDeclareFormById(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<DeclareForm> findDeclareForms(DeclareFormVo declareFormVo,
			Integer page, Integer rows) {
		int totalRecord = (Integer) getSqlMapClientTemplate().queryForObject(
				"declareForm.countFindDeclareForms", declareFormVo);
		PageInfo<DeclareForm> result = new PageInfo<DeclareForm>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"declareForm.findDeclareForms", declareFormVo,
				(page - 1) * rows, rows));

		return result;
	}

	@Override
	public DeclareForm updateDeclareForm(DeclareForm declareForm) {
		getSqlMapClientTemplate().update("declareForm.updateDeclareForm",
				declareForm);
		return declareForm;
	}

	@Override
	public DeclareForm getSimpleDeclareFormById(Long id) {
		return (DeclareForm) getSqlMapClientTemplate().queryForObject(
				"declareForm.getSimpleDeclareFormById", id);
	}

	@Override
	public DeclareForm getSimpleDeclareFormByStepId(Long id) {
		return (DeclareForm) getSqlMapClientTemplate().queryForObject(
				"declareForm.getSimpleDeclareFormByStepId", id);
	}

}
