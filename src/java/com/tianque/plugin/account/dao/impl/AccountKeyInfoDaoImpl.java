package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.account.dao.AccountKeyInfoDao;
import com.tianque.plugin.account.domain.AccountKeyInfo;

@Repository("accountKeyInfoDao")
public class AccountKeyInfoDaoImpl extends AbstractBaseDao implements
		AccountKeyInfoDao {

	@Override
	public AccountKeyInfo addAccountKeyInfo(AccountKeyInfo accountKeyInfo) {
		if (!validate(accountKeyInfo)) {
			throw new BusinessValidationException();
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"accountKeyInfo.addAccountKeyInfo", accountKeyInfo);
		return getSimpleAccountKeyInfoById(id);
	}

	@Override
	public AccountKeyInfo getSimpleAccountKeyInfoById(long id) {
		return (AccountKeyInfo) getSqlMapClientTemplate().queryForObject(
				"accountKeyInfo.getSimpleAccountKeyInfoById", id);
	}

	@Override
	public AccountKeyInfo getSimpleAccountKeyInfoByName(String name) {
		if (name == null || "".equals(name.trim())) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sortField", null);
		map.put("order", null);
		List<AccountKeyInfo> AccountKeyInfos = getSqlMapClientTemplate()
				.queryForList("accountKeyInfo.findAccountKeyInfos", map, 0, 1);
		if (AccountKeyInfos != null && AccountKeyInfos.size() > 0) {
			return AccountKeyInfos.get(0);
		}
		return null;
	}

	@Override
	public AccountKeyInfo updateAccountKeyInfo(AccountKeyInfo accountKeyInfo) {
		if (accountKeyInfo.getName() == null
				|| "".equals(accountKeyInfo.getName().trim())) {
			throw new BusinessValidationException();
		}
		getSqlMapClientTemplate().update("accountKeyInfo.updateAccountKeyInfo",
				accountKeyInfo);
		return getSimpleAccountKeyInfoById(accountKeyInfo.getId());
	}

	private boolean validate(AccountKeyInfo accountKeyInfo) {
		if (accountKeyInfo.getName() == null
				|| "".equals(accountKeyInfo.getName().trim())) {
			return false;
		}
		if (getSimpleAccountKeyInfoByName(accountKeyInfo.getName()) != null) {
			return false;
		}
		return true;
	}
}
