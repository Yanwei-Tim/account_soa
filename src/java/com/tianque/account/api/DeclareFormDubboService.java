package com.tianque.account.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.vo.DeclareFormVo;

public interface DeclareFormDubboService {
	/**
	 * 新增申报单
	 * 
	 * @param declareForm
	 * @return
	 */
	public DeclareForm addDeclareForm(DeclareForm declareForm);

	/**
	 * 更新申报单
	 * 
	 * @param declareForm
	 * @return
	 */
	public DeclareForm updateDeclareForm(DeclareForm declareForm);

	/**
	 * 查询申报单
	 * 
	 * @param declareFormVo
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<DeclareForm> findDeclareForms(DeclareFormVo declareFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取申报单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public DeclareForm getSimpleDeclareFormById(Long id);

	/*
	 * 根据步骤id获取申报单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public DeclareForm getSimpleDeclareFormByStepId(Long id);

	/**
	 * 根据操作措施，步骤编号创建临时申报单
	 * 
	 * @param operation
	 * @param keyId
	 * @return
	 */
	public DeclareForm createTemporaryDeclareForm(
			ThreeRecordsIssueLogNew operation, Long keyId);

	/**
	 * 先生成申报单，在提交流程
	 * 
	 * @param operation
	 * @param tellOrgIds
	 * @param files
	 * @param declareForm
	 * @return
	 */
	public DeclareForm saveDeclareFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, DeclareForm declareForm);

}
