package com.tianque.account.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.vo.TurnFormVo;

public interface TurnFormDubboService {
	/**
	 * 新增转办单
	 * 
	 * @param TurnForm
	 * @return
	 */
	public TurnForm addTurnForm(TurnForm TurnForm);

	/**
	 * 更新转办单
	 * 
	 * @param turnForm
	 * @return
	 */
	public TurnForm updateTurnForm(TurnForm turnForm);

	/**
	 * 查询转办单
	 * 
	 * @param turnFormVo
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<TurnForm> findTurnForms(TurnFormVo turnFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取转办单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public TurnForm getSimpleTurnFormById(Long id);

	/*
	 * 根据步骤id获取转办单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public TurnForm getSimpleTurnFormByStepId(Long id);
	
	/**
	 * 根据操作措施，步骤编号创建临时转办单
	 * 
	 * @param operation
	 * @param keyId
	 * @return
	 */
	public TurnForm createTemporaryTurnForm(ThreeRecordsIssueLogNew operation,
			Long keyId);

	/**
	 * 先生成转办单，在提交流程
	 * 
	 * @param operation
	 *            工作措施
	 * @param tellOrgIds
	 *            协办
	 * @param notices
	 *            抄告编号
	 * @param files
	 *            附件
	 * @param turnForm
	 *            转办单
	 * @return
	 */
	public TurnForm saveTurnFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			TurnForm turnForm);


}
