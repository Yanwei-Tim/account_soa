package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;

public interface AssignFormService {

	/**
	 * 新增交办单
	 * 
	 * @param assignForm
	 * */
	public AssignForm addAssignForm(AssignForm assignForm);

	/**
	 * 更新交办单
	 * 
	 * @param assignForm
	 * */
	public AssignForm updateAssignForm(AssignForm assignForm);

	/*
	 * 根据编号获取交办单
	 */
	public AssignForm getSimpleAssignFormById(Long id);

	/*
	 * 根据步骤id获取交办单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public AssignForm getSimpleAssignFormByStepId(Long id);

	/**
	 * 根据操作措施，步骤编号，辅办编号创建临时交办单
	 * 
	 * @param operation
	 *            工作措施
	 * @param tells
	 *            辅办编号
	 * @param keyId
	 *            步骤编号
	 * @return
	 */
	public AssignForm createTemporaryAssignForm(
			ThreeRecordsIssueLogNew operation, Long[] tells, Long keyId);

	/**
	 * 先生成交办单，在提交流程
	 * 
	 * @param operation
	 *            工作措施
	 * @param tellOrgIds
	 *            协办
	 * @param notices
	 *            抄告编号
	 * @param files
	 *            附件
	 * @param AssignForm
	 *            交办单
	 * @return
	 */
	public AssignForm saveAssignFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			AssignForm assignForm);

}
