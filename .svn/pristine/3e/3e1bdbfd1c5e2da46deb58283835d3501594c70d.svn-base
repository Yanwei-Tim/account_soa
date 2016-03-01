package com.tianque.plugin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.account.dao.AccountKeyInfoDao;
import com.tianque.plugin.account.domain.AccountKeyInfo;
import com.tianque.plugin.account.service.AccountKeyInfoService;

@Service("accountKeyInfoService")
@Transactional
public class AccountKeyInfoServiceImpl extends AbstractBaseService implements
		AccountKeyInfoService {

	@Autowired
	private AccountKeyInfoDao accountKeyInfoDao;

	@Override
	public AccountKeyInfo addAccountKeyInfo(AccountKeyInfo accountKeyInfo) {
		if (!validate(accountKeyInfo)){
			throw new BusinessValidationException();
		}
		return accountKeyInfoDao.addAccountKeyInfo(accountKeyInfo);
	}

	@Override
	public AccountKeyInfo getSimpleAccountKeyInfoByName(String name) {
		return accountKeyInfoDao.getSimpleAccountKeyInfoByName(name);
	}

	@Override
	public AccountKeyInfo updateAccountKeyInfo(AccountKeyInfo accountKeyInfo) {
		if (accountKeyInfo.getName() == null
				|| "".equals(accountKeyInfo.getName().trim())){
			throw new BusinessValidationException();
		}
		return accountKeyInfoDao.updateAccountKeyInfo(accountKeyInfo);
	}

	private boolean validate(AccountKeyInfo accountKeyInfo) {
		if (accountKeyInfo.getName() == null
				|| "".equals(accountKeyInfo.getName().trim())){
			return false;
		}
		if (getSimpleAccountKeyInfoByName(accountKeyInfo.getName()) != null){
			return false;
		}
		return true;
	}

}
