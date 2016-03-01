package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.TurnFormDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.service.TurnFormService;
import com.tianque.plugin.account.vo.TurnFormVo;

@Component
public class TurnFormDubboServiceImpl implements TurnFormDubboService {

	@Autowired
	private TurnFormService turnFormService;

	@Override
	public TurnForm addTurnForm(TurnForm turnForm) {
		return turnFormService.addTurnForm(turnForm);
	}

	public PageInfo<TurnForm> findTurnForms(TurnFormVo turnFormVo,
			Integer page, Integer rows) {
		PageInfo<TurnForm> pageInfo = turnFormService.findTurnForms(turnFormVo,
				page, rows);
		return pageInfo;
	}

	@Override
	public TurnForm updateTurnForm(TurnForm turnForm) {
		return turnFormService.updateTurnForm(turnForm);
	}

	@Override
	public TurnForm getSimpleTurnFormById(Long id) {
		return turnFormService.getSimpleTurnFormById(id);
	}

	@Override
	public TurnForm getSimpleTurnFormByStepId(Long id) {
		return turnFormService.getSimpleTurnFormByStepId(id);
	}

	@Override
	public TurnForm createTemporaryTurnForm(ThreeRecordsIssueLogNew operation,
			Long keyId) {
		return turnFormService.createTemporaryTurnForm(operation, keyId);
	}

	@Override
	public TurnForm saveTurnFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			TurnForm turnForm) {
		return turnFormService.saveTurnFormAndCompletePorcess(operation,
				tellOrgIds, notices, files, turnForm);
	}

}
