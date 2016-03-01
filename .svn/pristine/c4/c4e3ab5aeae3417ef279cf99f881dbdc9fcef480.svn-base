package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.DeclareFormDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.DeclareFormService;
import com.tianque.plugin.account.vo.DeclareFormVo;

@Component
public class DeclareFormDubboServiceImpl implements DeclareFormDubboService {

	@Autowired
	private DeclareFormService declareFormService;

	@Override
	public DeclareForm addDeclareForm(DeclareForm declareForm) {
		return declareFormService.addDeclareForm(declareForm);
	}

	public PageInfo<DeclareForm> findDeclareForms(DeclareFormVo declareFormVo,
			Integer page, Integer rows) {
		PageInfo<DeclareForm> pageInfo = declareFormService.findDeclareForms(
				declareFormVo, page, rows);
		return pageInfo;
	}

	@Override
	public DeclareForm updateDeclareForm(DeclareForm declareForm) {
		return declareFormService.updateDeclareForm(declareForm);
	}

	@Override
	public DeclareForm getSimpleDeclareFormById(Long id) {
		return declareFormService.getSimpleDeclareFormById(id);
	}

	@Override
	public DeclareForm getSimpleDeclareFormByStepId(Long id) {
		return declareFormService.getSimpleDeclareFormByStepId(id);
	}

	@Override
	public DeclareForm createTemporaryDeclareForm(ThreeRecordsIssueLogNew log,
			Long keyId) {
		return declareFormService.createTemporaryDeclareForm(log, keyId);
		// if (keyId == null || log == null) {
		// throw new BusinessValidationException("参数错误！");
		// }
		// DeclareForm declareForm = new DeclareForm();
		// declareForm.setSerialNumber(threeRecordsKeyGeneratorService
		// .getNextFormKey(getLedgerPrefix(log.getLedgerType()),
		// LedgerConstants.DECLARE_FORM));
		// declareForm.setLedgerId(log.getLedgerId());
		// declareForm.setLedgerType(Long.valueOf(log.getLedgerType()));
		// declareForm.setMobile(log.getMobile());
		// declareForm.setName(log.getDealUserName());
		// declareForm.setHandleContent(log.getContent());
		// declareForm.setDeclareDate(log.getOperateTime());
		// declareForm.setTargetOrg(log.getTargeOrg());
		// declareForm.setSourceOrg(log.getDealOrg());
		// if (log.getIssueStep() != null) {
		// declareForm.setStepId(log.getIssueStep().getId());
		// }
		// // declareForm = declareFormService.addDeclareForm(declareForm);
		// return declareForm;
	}

	@Override
	public DeclareForm saveDeclareFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, DeclareForm declareForm) {
		return declareFormService.saveDeclareFormAndCompletePorcess(operation,
				tellOrgIds, files, declareForm);
	}

}
