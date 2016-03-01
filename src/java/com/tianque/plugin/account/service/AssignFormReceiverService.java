package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.AssignFormReceiver;

public interface AssignFormReceiverService {

	/**
	 * 新增接件单
	 * 
	 * @param assignFormReceiver
	 * */
	public AssignFormReceiver addAssignFormReceiver(
			AssignFormReceiver assignFormReceiver);

	/**
	 * 更新接件单
	 * 
	 * @param AssignFormReceiver
	 * */
	public AssignFormReceiver updateAssignFormReceiver(
			AssignFormReceiver assignFormReceiver);

	/*
	 * 根据接件单编号获取接件单
	 */
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long id);

	/*
	 * 根据交办单编号获取接件单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public List<AssignFormReceiver> findAssignFormReceiversByAssignId(Long id);

	/**
	 * 根据步骤编号,目标部门编号获取接件单
	 * 
	 * @param stepId
	 * @param targetOrg
	 * @return
	 */
	public AssignFormReceiver getSimpleAssignFormReceiverById(Long stepId,
			Long targetOrg);

}
