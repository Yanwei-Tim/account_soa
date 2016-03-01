package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.LedgerConvertDao;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.vo.LedgerConvertVo;

@Repository("ledgerConvertDao")
public class LedgerConvertDaoImp extends AbstractBaseDao implements
		LedgerConvertDao {


	@Override
	public boolean addLedgerConvert(LedgerConvert ledgerConvert) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"ledgerConvert.addLedgerConvert", ledgerConvert);
		return id != null && id > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<LedgerConvert> findLedgerConverts(
			LedgerConvertVo ledgerConvertVo, Integer page, Integer rows) {
		int totalRecord = (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerConvert.countFindLedgerConverts", ledgerConvertVo);
		PageInfo<LedgerConvert> result = new PageInfo<LedgerConvert>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"ledgerConvert.findLedgerConverts", ledgerConvertVo,
				(page - 1) * rows, rows));

		return result;
	}

	@Override
	public LedgerConvert updateLedgerConvert(LedgerConvert ledgerConvert) {
		getSqlMapClientTemplate().update("ledgerConvert.updateLedgerConvert",
				ledgerConvert);
		return ledgerConvert;
	}
	

	@Override
	public LedgerConvert getSimpleLedgerConvertById(long id) {
		return (LedgerConvert) getSqlMapClientTemplate().queryForObject(
				"ledgerConvert.getSimpleLedgerConvertById", id);
	}

}
