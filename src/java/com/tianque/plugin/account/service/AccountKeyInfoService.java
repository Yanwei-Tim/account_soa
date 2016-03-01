package com.tianque.plugin.account.service;

import com.tianque.plugin.account.domain.AccountKeyInfo;

public interface AccountKeyInfoService {
	/***
	 * 新增台账序列号
	 * @param accountKeyInfo
	 * @return
	 */
	/**
	 * 新增AccountKeyInfo（三本台账编号生成）
	 * @param accountKeyInfo
	 * @return
	 */
	public AccountKeyInfo addAccountKeyInfo(AccountKeyInfo accountKeyInfo);

	/**
	 * 修改AccountKeyInfo（三本台账编号生成）
	 * @param accountKeyInfo
	 * @return
	 */
	public AccountKeyInfo updateAccountKeyInfo(AccountKeyInfo accountKeyInfo);

	/**
	 * 按名字查找AccountKeyInfo（三本台账编号生成）
	 * @param name
	 * @return
	 */
	public AccountKeyInfo getSimpleAccountKeyInfoByName(String name);

}
