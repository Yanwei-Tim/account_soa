package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.AcceptFormDao;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.AcceptFormService;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.AcceptFormVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
@Service("acceptFormService")
@Transactional
public class AcceptFormServiceImpl implements AcceptFormService {
	
	@Autowired
	private AcceptFormDao acceptFormDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	@Qualifier("acceptFormValidatorImpl")
	private DomainValidator<AcceptForm> acceptFormValidator;
	
	@Autowired
	protected ThreeRecordsIssueService threeRecordsIssueService;

	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;
	
	
	@Override
	public PageInfo<AcceptForm> findAcceptForms(AcceptFormVo acceptFormVo,
			Integer page, Integer rows) {
		if(acceptFormVo == null)
			throw new BusinessValidationException("受理单查询参数有误！");
		return acceptFormDao.findAcceptForms(acceptFormVo, page, rows);
	}

	@Override
	public AcceptForm addAcceptForm(AcceptForm acceptForm) {
		ValidateResult result = acceptFormValidator.validate(acceptForm);
		if(result.hasError())
			throw new BusinessValidationException(result.getErrorMessages());
		try {
			acceptForm = acceptFormDao.addAcceptForm(acceptForm);
			getOrg(acceptForm);
			return acceptForm;
		} catch (Exception e) {
			throw new ServiceValidationException("新增受理单失败!", e);
		}
	}
	
	private void getOrg(AcceptForm acceptForm){
		if(acceptForm == null)
			return;
		if(acceptForm.getAcceptOrg() != null && acceptForm.getAcceptOrg().getId() != null)
			acceptForm.setAcceptOrg(organizationDubboService.getSimpleOrgById(acceptForm.getAcceptOrg().getId()));
	}

	@Override
	public AcceptForm updateAcceptForm(AcceptForm acceptForm) {
		if(acceptForm == null || acceptForm.getId() == null)
			throw new BusinessValidationException("更新受理单参数有误！");
		try {
			return acceptFormDao.updateAcceptForm(acceptForm);
		} catch (Exception e) {
			throw new ServiceValidationException("更新收单失败！", e);
		}
	}

	@Override
	public AcceptForm getSimpleAcceptFormById(Long id) {
		if(id == null)
			throw new BusinessValidationException("查询受理单失败，id为空！");
		try {
			return acceptFormDao.getSimpleAcceptFormById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("根据id查询受理单失败!", e);
		}
	}

	@Override
	public AcceptForm getSimpleAcceptFormByStepId(Long id) {
		if(id == null)
			throw new BusinessValidationException("查询受理单出错，id为null");
		List<AcceptForm> forms = acceptFormDao.findSimpleAcceptFormByStepId(id);
		if(forms == null || forms.isEmpty())
			return null;
		AcceptForm form = forms.get(0);
		getOrg(form);
		return form;
	}

	@Override
	public AcceptForm saveAcceptFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, AcceptForm acceptForm) {
		if (acceptForm == null || acceptForm.getStepId() == null
				|| operation == null || acceptForm.getLedgerType() == null) {
			throw new BusinessValidationException("受理单添加时参数不对!");
		}
		try {
			acceptForm = addAcceptForm(acceptForm);
			switch (acceptForm.getLedgerType().intValue()) {
			case LedgerConstants.STEADYWORK:
				ledgerSteadyWorkService.concept(acceptForm.getStepId(), operation);
				break;
			case LedgerConstants.POORPEOPLE:
				ledgerPoorPeopleService.concept(acceptForm.getStepId(), operation);
				break;
			default:
				threeRecordsIssueService.concept(acceptForm.getStepId(), operation);
				break;
			}
			return getSimpleAcceptFormById(acceptForm.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("新增受理单失败!", e);
		}
	}

	@Override
	public AcceptForm createTemporaryAcceptForm(
			Long keyId, Long ledgerType) {
		if (keyId == null || ledgerType == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			AcceptForm acceptForm = new AcceptForm();
			acceptForm.setStepId(keyId);
			acceptForm.setLedgerType(ledgerType);
			acceptForm.setSerialNumber(threeRecordsKeyGeneratorService
					.getNextFormKey(ThreeRecordsIssueOperationUtil
							.getLedgerPrefix(ledgerType.intValue()),
							LedgerConstants.ACCEPT_FORM));
			getOrg(acceptForm);
			return acceptForm;

		} catch (Exception e) {
			throw new ServiceValidationException("创建临时呈报单失败!", e);
		}
	}
}
