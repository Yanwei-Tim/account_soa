package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;

@Service("ledgerPeopleAspirationValidatorImpl")
public class LedgerPeopleAspirationValidatorImpl implements
		DomainValidator<LedgerPeopleAspirations> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerPeopleAspirations domain) {
		ValidateResult result = new ValidateResult();

		if(validateHelper.nullObj(domain.getHistoryId())) {
			if (validateHelper.nullObj(domain.getResponseGroupNo())) {
				result.addErrorMessage("反映人数不能为空!");
			} else if (validateHelper.illegalStringLength(0, 10, domain
					.getResponseGroupNo().toString())
					&& validateHelper.illegalNumberZS(domain.getResponseGroupNo()
					.toString())) {
				result.addErrorMessage("反映人数最多输入长度10，且只能输入正整数!");
			}
			if (validateHelper.emptyString(domain.getServerContractor())) {
				result.addErrorMessage("登记单位联系人不能为空!");
			} else if (validateHelper.illegalStringLength(0, 50, domain
					.getServerContractor())) {
				result.addErrorMessage("登记单位联系人最多输入长度50!");
			}
			if (validateHelper.emptyString(domain.getServerJob())) {
				result.addErrorMessage("单位及职务不能为空!");
			} else if (validateHelper.illegalStringLength(0, 50, domain
					.getServerJob())) {
				result.addErrorMessage("单位及职务最多输入长度50!");
			}
			if (validateHelper.emptyString(domain.getServerTelephone())) {
				result.addErrorMessage("服务联系电话不能为空!");
			} else if (validateHelper.illegalStringLength(0, 15, domain
					.getServerTelephone())
					&& validateHelper.illegalNumberZS(domain.getServerTelephone())) {
				result.addErrorMessage("联系电话最多输入长度15，且只能输入正整数!");
			}
			if (validateHelper.nullObj(domain.getCreateTableType())
					|| validateHelper.nullObj(domain.getCreateTableType().getId())) {
				result.addErrorMessage("建表类型不能为空!");
			}
			if (validateHelper.emptyString(domain.getSerialNumber())) {
				result.addErrorMessage("编号不能为空!");
			} else if (validateHelper.illegalStringLength(1, 30, domain
					.getSerialNumber())) {
				result.addErrorMessage("编号只能输入30个字符!");
			}
			validateOccurDate(domain, result);
			if (!validateHelper.emptyString(domain.getMobileNumber())
					&& validateHelper.illegalStringLength(0, 15, domain
					.getMobileNumber())
					&& validateHelper.illegalNumberZS(domain.getMobileNumber())) {
				result.addErrorMessage("联系电话最多输入长度15，且只能输入正整数!");
			}
			if (!validateHelper.emptyString(domain.getPermanentAddress())
					&& validateHelper.illegalStringLength(0, 50, domain
					.getPermanentAddress())) {
				result.addErrorMessage("常住地址最多输入长度50!");
			}
			if (!validateHelper.emptyString(domain.getName())&&validateHelper.illegalStringLength(1, 20, domain.getName())) {
				result.addErrorMessage("姓名只能输入1-20个字符!");
			}
			if (!validateHelper.emptyString(domain.getIdCardNo())&&validateHelper.illegalIdcard(domain.getIdCardNo())) {
				result.addErrorMessage("请输入合法的身份证号码!");
			}
			if (domain.isAnonymity()) {
				return result;
			}
			if (validateHelper.emptyString(domain.getName())) {
				result.addErrorMessage("姓名不能为空!");
			} 
			
			if (validateHelper.emptyString(domain.getIdCardNo())) {
				result.addErrorMessage("身份证号不能为空!");
			}  
			if (validateHelper.nullObj(domain.getGender())
					|| validateHelper.nullObj(domain.getGender().getId())) {
				result.addErrorMessage("性别不能为空!");
			}
			if (validateHelper.nullObj(domain.getPosition())
					|| validateHelper.nullObj(domain.getPosition().getId())) {
				result.addErrorMessage("职业或身份不能为空!");
			}

		}
		return result;
	}

	private void validateOccurDate(LedgerPeopleAspirations domain,
			ValidateResult result) {
		if (domain.getOccurDate() == null) {
			result.addErrorMessage("请输该台账的发生时间!");
		} else if (CalendarUtil.now().before(domain.getOccurDate())) {
			result.addErrorMessage("台账发生时间不能大于当前时间!");
		}
	}
}
