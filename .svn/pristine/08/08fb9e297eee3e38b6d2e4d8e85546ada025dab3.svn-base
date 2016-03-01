package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ReportFormDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.ReportFormService;
import com.tianque.plugin.account.vo.ReportFormVo;

@Component
public class ReportFormDubboServiceImpl implements ReportFormDubboService {

	@Autowired
	private ReportFormService reportFormService;

	@Override
	public ReportForm addReportForm(ReportForm reportForm) {
		return reportFormService.addReportForm(reportForm);
	}

	public PageInfo<ReportForm> findReportForms(ReportFormVo reportFormVo,
			Integer page, Integer rows) {
		PageInfo<ReportForm> pageInfo = reportFormService.findReportForms(
				reportFormVo, page, rows);
		return pageInfo;
	}

	@Override
	public ReportForm updateReportForm(ReportForm reportForm) {
		return reportFormService.updateReportForm(reportForm);
	}

	@Override
	public ReportForm getSimpleReportFormById(Long id) {
		return reportFormService.getSimpleReportFormById(id);
	}

	@Override
	public ReportForm getSimpleReportFormByStepId(Long id) {
		return reportFormService.getSimpleReportFormByStepId(id);
	}

	@Override
	public ReportForm saveReportFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, ReportForm reportForm) {

		return reportFormService.saveReportFormAndCompletePorcess(operation,
				tellOrgIds, files, reportForm);
	}

	@Override
	public ReportForm createTemporaryReportForm(ThreeRecordsIssueLogNew log,
			Long keyId) {
		return reportFormService.createTemporaryReportForm(log, keyId);
	}

}
