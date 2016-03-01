package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.vo.ReportFormVo;

public interface ReportFormService {

	/**
	 * 查询呈报单
	 * 
	 * @param reportForm
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<ReportForm> findReportForms(ReportFormVo reportFormVo,
			Integer page, Integer rows);

	/**
	 * 新增呈报单
	 * 
	 * @param reportForm
	 * */
	public ReportForm addReportForm(ReportForm reportForm);

	/**
	 * 更新呈报单
	 * 
	 * @param reportForm
	 * */
	public ReportForm updateReportForm(ReportForm reportForm);

	/*
	 * 根据编号获取呈报单
	 * 
	 * @param id
	 */
	public ReportForm getSimpleReportFormById(Long id);

	/*
	 * 根据步骤id获取呈报单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public ReportForm getSimpleReportFormByStepId(Long id);

	/**
	 * 先生成呈报单，在提交流程
	 * 
	 * @param operation
	 * @param tellOrgIds
	 * @param files
	 * @param reportForm
	 * @return
	 */
	public ReportForm saveReportFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, ReportForm reportForm);

	/**
	 * 根据工作措施，操作步骤生成临时呈报单
	 * 
	 * @param log
	 * @param keyId
	 * @return
	 */
	public ReportForm createTemporaryReportForm(ThreeRecordsIssueLogNew log,
			Long keyId);

}
