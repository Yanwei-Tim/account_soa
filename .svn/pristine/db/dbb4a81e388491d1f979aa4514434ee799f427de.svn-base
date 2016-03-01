package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.vo.TurnFormVo;

public interface TurnFormDao {

	/**
	 * 新增转办单
	 * 
	 * @param TurnForm
	 * */
	public TurnForm addTurnForm(TurnForm TurnForm);

	/**
	 * 更新转办单
	 * 
	 * @param turnForm
	 * */
	public TurnForm updateTurnForm(TurnForm turnForm);

	/**
	 * 查询转办单
	 * 
	 * */
	public PageInfo<TurnForm> findTurnForms(TurnFormVo turnFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取转办单
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

}
