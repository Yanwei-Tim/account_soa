package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.AssignFormReceiverDao;
import com.tianque.plugin.account.domain.AssignFormReceiver;

@Repository("assignReceiverFormDao")
public class AssignFormReceiverDaoImpl extends AbstractBaseDao implements
		AssignFormReceiverDao {

	@Override
	public AssignFormReceiver addAssignFormReceiver(
			AssignFormReceiver assignFormReceiver) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"assignFormReceiver.addAssignFormReceiver", assignFormReceiver);
		return getSimpleAssignFormReceiverById(id);

	}

	@Override
	public AssignFormReceiver updateAssignFormReceiver(
			AssignFormReceiver assignFormReceiver) {
		getSqlMapClientTemplate().update(
				"assignFormReceiver.updateAssignFormReceiver",
				assignFormReceiver);
		return assignFormReceiver;
	}

	@Override
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long id) {
		return (AssignFormReceiver) getSqlMapClientTemplate().queryForObject(
				"assignFormReceiver.getSimpleAssignFormReceiverById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssignFormReceiver> findAssignFormReceiversByAssignId(
			Long id) {
		return (List) getSqlMapClientTemplate().queryForList(
				"assignFormReceiver.findAssignFormReceiversByAssignId", id);
	}

	@Override
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long stepId,
			Long targetOrg) {
		Map map = new HashMap();
		map.put("stepId", stepId);
		map.put("targetOrg", targetOrg);
		return (AssignFormReceiver) getSqlMapClientTemplate()
				.queryForObject(
						"assignFormReceiver.getSimpleAssignFormReceiverByStepIdAndTargetOrg",
						map);
	}

}
