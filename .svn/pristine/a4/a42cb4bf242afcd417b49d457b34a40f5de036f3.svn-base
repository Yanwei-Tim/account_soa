package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.vo.LedgerConvertVo;

public interface LedgerConvertDao {


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
	public LedgerConvert updateLedgerConvert(LedgerConvert ledgerConvert) ;
	
	
	

	/**
	 * 查询转换台账
	 * 
	 * */
	public PageInfo<LedgerConvert> findLedgerConverts(
			LedgerConvertVo ledgerConvertVo, Integer page, Integer rows);
	
	/*
	 * 根据编号获取转换台账
	 */
	public LedgerConvert getSimpleLedgerConvertById(long id);

}
