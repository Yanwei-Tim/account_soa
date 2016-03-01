package com.tianque.plugin.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.AccountKeyInfo;
import com.tianque.plugin.account.service.AccountKeyInfoService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("threeRecordsIssueSerialKeyGeneratorServiceImpl")
@Transactional
public class ThreeRecordsIssueSerialKeyGeneratorServiceImpl implements
		ThreeRecordsKeyGeneratorService {

	private static final String DEFAULT_PREFIX_FORMAT = "yyMMdd";
	private static final String PREFIX_FORMAT = "yyyyMMdd";
	private static final String DEFAULT_FIX_CONTEXT = "000000";
	private static final String DEFAULT_FIX_REPORT_CONTEXT = "00000";

	@Autowired
	private AccountKeyInfoService accountKeyInfoService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	public String getNextKey(String prefix, Long orgId) {
		Organization district = organizationDubboService
				.findSomeLevelParentOrg(orgId, OrganizationLevel.DISTRICT);
		if (district == null) {
			district = organizationDubboService.findOrganizationsByParentId(
					null).get(0);
		}
		prefix = getCurrentPrefix(prefix);
		AccountKeyInfo accountKeyInfo = accountKeyInfoService
				.getSimpleAccountKeyInfoByName(prefix);
		if (accountKeyInfo == null) {
			accountKeyInfo = addNewAccountKeyInfo(prefix);
		}
		accountKeyInfo.setCurNum(accountKeyInfo.getCurNum()
				+ accountKeyInfo.getStep());
		accountKeyInfo = accountKeyInfoService
				.updateAccountKeyInfo(accountKeyInfo);
		String curNum = String.valueOf(accountKeyInfo.getCurNum());
		if (curNum.length() > 6) {
			curNum = curNum.substring(0, 6);
		} else {
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 6 - curNum.length())
					+ curNum;
		}
		return prefix.substring(6) + curNum;
	}

	public String getNextFormKey(String prefix, String formType) {
		prefix = formType + getPrefixDate(PREFIX_FORMAT) + prefix;
		AccountKeyInfo accountKeyInfo = accountKeyInfoService
				.getSimpleAccountKeyInfoByName(prefix);
		if (accountKeyInfo == null) {
			accountKeyInfo = addNewAccountKeyInfo(prefix);
		}
		accountKeyInfo.setCurNum(accountKeyInfo.getCurNum()
				+ accountKeyInfo.getStep());
		accountKeyInfo = accountKeyInfoService
				.updateAccountKeyInfo(accountKeyInfo);
		String curNum = String.valueOf(accountKeyInfo.getCurNum());
		if (curNum.length() > 5) {
			curNum = curNum.substring(0, 5);
		} else {
			curNum = DEFAULT_FIX_REPORT_CONTEXT.substring(0, 5 - curNum
					.length())
					+ curNum;
		}
		return prefix.substring(10) + curNum;
	}

	private String getPrefixDate(String PREFIX_FORMAT) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PREFIX_FORMAT);
		return simpleDateFormat.format(Calendar.getInstance().getTime());
	}

	private String getCurrentPrefix(String prefix) {
		String dateSerial = getPrefixDate(DEFAULT_PREFIX_FORMAT);
		return LedgerConstants.LEDGER + dateSerial + prefix;
	}

	private AccountKeyInfo addNewAccountKeyInfo(String prefix) {
		return accountKeyInfoService
				.addAccountKeyInfo(createAccountKeyInfoInstance(prefix));
	}

	private AccountKeyInfo createAccountKeyInfoInstance(String prefix) {
		AccountKeyInfo accountKeyInfo = new AccountKeyInfo();
		accountKeyInfo.setName(prefix);
		accountKeyInfo.setCurNum(0L);
		accountKeyInfo.setStep(1);
		return accountKeyInfo;
	}

	@Override
	public String getNextKey(String prefix, Long orgId, Date createDate) {
		if (orgId == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Organization district = organizationDubboService
				.findSomeLevelParentOrg(orgId, OrganizationLevel.DISTRICT);
		if (district == null) {
			district = organizationDubboService.findOrganizationsByParentId(
					null).get(0);
		}
		prefix = getCreateDatePrefix(prefix, createDate);
		AccountKeyInfo accountKeyInfo = accountKeyInfoService
				.getSimpleAccountKeyInfoByName(prefix);
		if (accountKeyInfo == null) {
			accountKeyInfo = addNewAccountKeyInfo(prefix);
		}
		accountKeyInfo.setCurNum(accountKeyInfo.getCurNum()
				+ accountKeyInfo.getStep());
		accountKeyInfo = accountKeyInfoService
				.updateAccountKeyInfo(accountKeyInfo);
		String curNum = String.valueOf(accountKeyInfo.getCurNum());
		if (curNum.length() > 6) {
			curNum = curNum.substring(0, 6);
		} else {
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 6 - curNum.length())
					+ curNum;
		}
		return prefix.substring(6) + curNum;

	}

	private String getCreateDatePrefix(String prefix, Date createDate) {
		if (createDate == null) {
			return getCurrentPrefix(prefix);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DEFAULT_PREFIX_FORMAT);
		String dateSerial = simpleDateFormat.format(createDate);
		return LedgerConstants.LEDGER + dateSerial + prefix;
	}

	@Override
	public String getNextFormKey(String prefix, String formType, Date createDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PREFIX_FORMAT);
		if (createDate == null) {
			prefix = formType + getPrefixDate(PREFIX_FORMAT) + prefix;
		} else {
			prefix = formType + simpleDateFormat.format(Calendar.getInstance().getTime()) + prefix;
		}
		AccountKeyInfo accountKeyInfo = accountKeyInfoService.getSimpleAccountKeyInfoByName(prefix);
		if (accountKeyInfo == null) {
			accountKeyInfo = addNewAccountKeyInfo(prefix);
		}
		accountKeyInfo.setCurNum(accountKeyInfo.getCurNum() + accountKeyInfo.getStep());
		accountKeyInfo = accountKeyInfoService.updateAccountKeyInfo(accountKeyInfo);
		String curNum = String.valueOf(accountKeyInfo.getCurNum());
		if (curNum.length() > 5) {
			curNum = curNum.substring(0, 5);
		} else {
			curNum = DEFAULT_FIX_REPORT_CONTEXT.substring(0, 5 - curNum.length()) + curNum;
		}
		return prefix.substring(10) + curNum;
	}
}
