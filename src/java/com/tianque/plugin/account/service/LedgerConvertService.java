package com.tianque.plugin.account.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.vo.LedgerConvertVo;

public interface LedgerConvertService {

	/**
	 * 查询转换台账
	 * 
	 * @param ledgerConvert
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<LedgerConvert> findLedgerConverts(
			LedgerConvertVo ledgerConvertVo, Integer page, Integer rows);

	/**
	 * 新增转换台账
	 * 
	 * @param ledgerConvert
	 * */
	public boolean addLedgerConvert(LedgerConvert ledgerConvert);

	/**
	 * 更新转换台账状态
	 * 
	 * @param ledgerConvert
	 * */
	public LedgerConvert updateLedgerConvert(LedgerConvert ledgerConvert);

	/*
	 * 根据编号获取转换台账
	 * 
	 * @param id
	 */
	public LedgerConvert getSimpleLedgerConvertById(long id);

}
