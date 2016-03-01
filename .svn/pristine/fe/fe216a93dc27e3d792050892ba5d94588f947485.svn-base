package com.tianque.plugin.account.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.vo.AcceptFormVo;

public interface AcceptFormDao {

	/**
	 * 新增受理单
	 * 
	 * @param reportForm
	 * */
	public AcceptForm addAcceptForm(AcceptForm acceptForm);

	/**
	 * 更新受理单
	 * 
	 * @param acceptForm
	 * */
	public AcceptForm updateAcceptForm(AcceptForm acceptForm);

	/**
	 * 查询受理单
	 * 
	 * */
	public PageInfo<AcceptForm> findAcceptForms(AcceptFormVo acceptFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取受理单
	 */
	public AcceptForm getSimpleAcceptFormById(Long id);

	/*
	 * 根据步骤id获取受理单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public List<AcceptForm> findSimpleAcceptFormByStepId(Long id);

}
