package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.AssignFormDao;
import com.tianque.plugin.account.domain.AssignForm;

@Repository("assignFormDao")
public class AssignFormDaoImpl extends AbstractBaseDao implements AssignFormDao {

	@Override
	public AssignForm addAssignForm(AssignForm assignForm) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"assignForm.addAssignForm", assignForm);

		return getSimpleAssignFormById(id);

	}

	@Override
	public AssignForm updateAssignForm(AssignForm assignForm) {
		getSqlMapClientTemplate().update("assignForm.updateAssignForm",
				assignForm);
		return assignForm;
	}

	@Override
	public AssignForm getSimpleAssignFormById(Long id) {
		return (AssignForm) getSqlMapClientTemplate().queryForObject(
				"assignForm.getSimpleAssignFormById", id);
	}

	@Override
	public AssignForm getSimpleAssignFormByStepId(Long id) {
		return (AssignForm) getSqlMapClientTemplate().queryForObject(
				"assignForm.getSimpleAssignFormByStepId", id);
	}

}
