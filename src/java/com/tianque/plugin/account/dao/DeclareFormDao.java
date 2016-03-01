package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.vo.DeclareFormVo;

public interface DeclareFormDao {

	/**
	 * 新增申报单
	 * 
	 * @param declareForm
	 * */
	public DeclareForm addDeclareForm(DeclareForm declareForm);

	/**
	 * 更新申报单
	 * 
	 * @param declareForm
	 * */
	public DeclareForm updateDeclareForm(DeclareForm declareForm);

	/**
	 * 查询申报单
	 * 
	 * */
	public PageInfo<DeclareForm> findDeclareForms(DeclareFormVo declareFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取申报单
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

}
