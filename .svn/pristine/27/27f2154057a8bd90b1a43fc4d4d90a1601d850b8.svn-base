package com.tianque.plugin.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.dao.AssignFormDao;
import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.AssignFormReceiver;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.AssignFormReceiverService;
import com.tianque.plugin.account.service.AssignFormService;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("assignFormService")
@Transactional
public class AssignFormServiceImpl implements AssignFormService {

	@Autowired
	private AssignFormDao assignFormDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private AssignFormReceiverService assignFormReceiverService;

	@Autowired
	protected ThreeRecordsIssueService threeRecordsIssueService;

	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;

	@Autowired
	@Qualifier("assignFormValidatorImpl")
	private DomainValidator<AssignForm> assignFormValidator;

	@Override
	public AssignForm addAssignForm(AssignForm assignForm) {
		ValidateResult result = assignFormValidator.validate(assignForm);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			List<AssignFormReceiver> receivers = assignForm.getReceivers();
			assignForm = assignFormDao.addAssignForm(assignForm);
			assignForm.setReceivers(receivers);
			saveAssignFormReceiver(assignForm);
			findOrg(assignForm);
			return assignForm;
		} catch (Exception e) {
			throw new ServiceValidationException("新增交办单失败!", e);
		}
	}

	private void saveAssignFormReceiver(AssignForm assignForm) {
		if (assignForm == null || assignForm.getReceivers() == null
				|| assignForm.getReceivers().size() == 0) {
			throw new BusinessValidationException("接件单参数不对!");
		}
		for (AssignFormReceiver receiver : assignForm.getReceivers()) {
			receiver.setAssignId(assignForm.getId());
			assignFormReceiverService.addAssignFormReceiver(receiver);
		}
	}

	@Override
	public AssignForm updateAssignForm(AssignForm assignForm) {
		if (assignForm == null || assignForm.getId() == null) {
			throw new BusinessValidationException("交办单更新参数不对!");
		}
		try {
			updateAssignFormReceiver(assignForm);
			assignFormDao.updateAssignForm(assignForm);
		} catch (Exception e) {
			throw new ServiceValidationException("更新交办单失败!", e);
		}
		return getSimpleAssignFormById(assignForm.getId());
	}

	@Override
	public AssignForm getSimpleAssignFormById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！交办单编号不能为空");
		}
		AssignForm assignForm = assignFormDao.getSimpleAssignFormById(id);
		findOrg(assignForm);
		loadAssignFormReceiver(assignForm);
		return assignForm;
	}

	/*
	 * 查询操作部门
	 */
	private void findOrg(AssignForm assignForm) {
		if (assignForm == null) {
			return;
		}
		if (assignForm.getSourceOrg() != null
				&& assignForm.getSourceOrg().getId() != null) {
			assignForm.setSourceOrg(organizationDubboService
					.getSimpleOrgById(assignForm.getSourceOrg().getId()));
		}
	}

	@Override
	public AssignForm getSimpleAssignFormByStepId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		AssignForm assignForm = assignFormDao.getSimpleAssignFormByStepId(id);
		findOrg(assignForm);
		loadAssignFormReceiver(assignForm);
		return assignForm;
	}

	/*
	 * 加载接收单
	 */
	private void loadAssignFormReceiver(AssignForm assignForm) {
		if (assignForm == null || assignForm.getId() == null) {
			return;
		}
		List<AssignFormReceiver> receivers = assignFormReceiverService
				.findAssignFormReceiversByAssignId(assignForm.getId());
		for (AssignFormReceiver receiver : receivers) {
			if (receiver.getTargetOrg() != null
					&& receiver.getTargetOrg().getId() != null) {
				receiver.setTargetOrg(organizationDubboService
						.getSimpleOrgById(receiver.getTargetOrg().getId()));
			}
		}
		assignForm.setReceivers(receivers);
	}

	private void updateAssignFormReceiver(AssignForm assignForm) {
		if (assignForm == null || assignForm.getReceivers() == null) {
			return;
		}
		for (AssignFormReceiver receiver : assignForm.getReceivers()) {
			assignFormReceiverService.updateAssignFormReceiver(receiver);
		}
	}

	@Override
	public AssignForm createTemporaryAssignForm(ThreeRecordsIssueLogNew log,
			Long[] tells, Long keyId) {

		if (keyId == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		Organization dealOrg = organizationDubboService.getSimpleOrgById(log
				.getDealOrg().getId());
		int type = LedgerConstants.COUNTY_NO_ASSIGN_TYPE;
		if (dealOrg.getDepartmentNo().endsWith(
				ThreeRecordsIssueConstants.COUNTY_JOINT)) {
			type = LedgerConstants.COUNTY_JOINT_ASSIGN_TYPE;
		} else if (dealOrg.getDepartmentNo().endsWith(
				ThreeRecordsIssueConstants.COUNTY_COMMITTEE)) {
			type = LedgerConstants.COUNTY_COMMITTEE_ASSIGN_TYPE;
		}
		if (type == LedgerConstants.COUNTY_NO_ASSIGN_TYPE) {
			return null;
		}
		AssignForm assignForm = new AssignForm();
		assignForm.setSerialNumber(threeRecordsKeyGeneratorService
				.getNextFormKey(ThreeRecordsIssueOperationUtil
						.getLedgerPrefix(log.getLedgerType()),
						LedgerConstants.ASSGIN_FORM));
		assignForm.setLedgerId(log.getLedgerId());
		assignForm.setLedgerType(Long.valueOf(log.getLedgerType()));
		// assignForm.setHandleContent(log.getContent());
		assignForm.setHandleStartDate(log.getOperateTime());
		assignForm.setAssignType(type);

		assignForm.setSourceOrg(log.getDealOrg());

		List<AssignFormReceiver> receivers = new ArrayList<AssignFormReceiver>();
		AssignFormReceiver receiver = null;
		if (log.getTargeOrg() != null) {
			receiver = new AssignFormReceiver();
			receiver.setIsManage(true);
			receiver.setTargetOrg(organizationDubboService.getSimpleOrgById(log
					.getTargeOrg().getId()));
			receivers.add(receiver);
		}
		for (Long tell : tells) {
			if (tell == null) {
				continue;
			}
			receiver = new AssignFormReceiver();
			receiver.setIsManage(false);
			receiver.setTargetOrg(organizationDubboService
					.getSimpleOrgById(tell));
			receivers.add(receiver);
		}
		assignForm.setStepId(keyId);
		assignForm.setReceivers(receivers);
		findOrg(assignForm);
		return assignForm;

	}

	@Override
	public AssignForm saveAssignFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			AssignForm assignForm) {

		if (assignForm == null || assignForm.getStepId() == null
				|| operation == null || operation.getTargeOrg() == null
				|| assignForm.getLedgerType() == null) {
			throw new BusinessValidationException("交办单添加时参数不对!");
		}
		try {
			assignForm = addAssignForm(assignForm);

			if (assignForm.getLedgerType().intValue() == LedgerConstants.STEADYWORK) {
				ledgerSteadyWorkService.assign(assignForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files, notices);
			} else if (assignForm.getLedgerType().intValue() == LedgerConstants.POORPEOPLE) {
				ledgerPoorPeopleService.assign(assignForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files, notices);
			} else {
				threeRecordsIssueService.assign(assignForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files, notices);

			}

			return getSimpleAssignFormById(assignForm.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("新增交办单失败!", e);
		}

	}

}
