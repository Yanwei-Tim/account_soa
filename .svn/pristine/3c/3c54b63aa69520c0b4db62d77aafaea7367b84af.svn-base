package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.vo.AcceptFormVo;

public interface AcceptFormService {

	/**
	 * 查询受理单
	 * 
	 * @param reportForm
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<AcceptForm> findAcceptForms(AcceptFormVo acceptFormVo,
			Integer page, Integer rows);

	/**
	 * 新增受理单
	 * 
	 * @param reportForm
	 * @return
	 */
	public AcceptForm addAcceptForm(AcceptForm acceptForm);

	/**
	 * 更新受理单
	 * 
	 * @param reportForm
	 * @return
	 */
	public AcceptForm updateAcceptForm(AcceptForm acceptForm);

	/*
	 * 根据编号获取受理单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public AcceptForm getSimpleAcceptFormById(Long id);

	/**
	 * 根据步骤id获取受理单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	 public AcceptForm getSimpleAcceptFormByStepId(Long id);

	/**
	 * 先生成受理单，在提交流程
	 * 
	 * @param operation
	 * @param tellOrgIds
	 * @param files
	 * @param acceptForm
	 * @return
	 */
	public AcceptForm saveAcceptFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, AcceptForm acceptForm);

	/**
	 * 根据工作措施，操作步骤生成临时受理单
	 * 
	 * @param log
	 * @param keyId
	 * @return
	 */
	public AcceptForm createTemporaryAcceptForm(
			Long keyId, Long ledgerType);
}
