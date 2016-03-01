package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.AssignFormReceiverDao;
import com.tianque.plugin.account.domain.AssignFormReceiver;
import com.tianque.plugin.account.service.AssignFormReceiverService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("assignFormReceiverService")
@Transactional
public class AssignFormReceiverServiceImpl implements AssignFormReceiverService {

	@Autowired
	private AssignFormReceiverDao assignFormReceiverDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	@Qualifier("assignFormReceiverValidatorImpl")
	private DomainValidator<AssignFormReceiver> assignFormReceiverValidator;

	@Override
	public AssignFormReceiver addAssignFormReceiver(
			AssignFormReceiver assignFormReceiver) {
		ValidateResult result = assignFormReceiverValidator
				.validate(assignFormReceiver);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			assignFormReceiver = assignFormReceiverDao
					.addAssignFormReceiver(assignFormReceiver);
			findOrg(assignFormReceiver);
			return assignFormReceiver;
		} catch (Exception e) {
			throw new ServiceValidationException("新增接件单失败!", e);
		}
	}

	@Override
	public AssignFormReceiver updateAssignFormReceiver(
			AssignFormReceiver assignFormReceiver) {
		if (assignFormReceiver == null || assignFormReceiver.getId() == null) {
			throw new BusinessValidationException("接件单更新参数不对!");
		}
		try {
			return assignFormReceiverDao
					.updateAssignFormReceiver(assignFormReceiver);
		} catch (Exception e) {
			throw new ServiceValidationException("更新接件单失败!", e);
		}
	}

	@Override
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！接件单编号不能为空");
		}
		AssignFormReceiver AssignFormReceiver = assignFormReceiverDao
				.getSimpleAssignFormReceiverById(id);
		findOrg(AssignFormReceiver);
		return AssignFormReceiver;
	}

	/*
	 * 
	 * 查询部门
	 */
	private void findOrg(AssignFormReceiver assignFormReceiver) {
		if (assignFormReceiver == null) {
			return;
		}
		if (assignFormReceiver.getTargetOrg() != null
				&& assignFormReceiver.getTargetOrg().getId() != null) {
			assignFormReceiver
					.setTargetOrg(organizationDubboService
							.getSimpleOrgById(assignFormReceiver.getTargetOrg()
									.getId()));
		}
	}

	@Override
	public List<AssignFormReceiver> findAssignFormReceiversByAssignId(
			Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		List<AssignFormReceiver> list = assignFormReceiverDao
				.findAssignFormReceiversByAssignId(id);
		for (AssignFormReceiver receiver : list) {
			findOrg(receiver);
		}
		return list;
	}

	@Override
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long stepId,
			Long targetOrg) {
		if (stepId == null || targetOrg == null) {
			throw new BusinessValidationException("参数错误！");
		}
		return assignFormReceiverDao.getSimpleAssignFormReceiverById(stepId,
				targetOrg);
	}

}
