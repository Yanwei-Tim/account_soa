package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerPoorPeople;

@Service("ledgerPoorPeopleValidatorImpl")
public class LedgerPoorPeopleValidatorImpl implements
		DomainValidator<LedgerPoorPeople> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerPoorPeople domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.nullObj(domain)) {
			result.addErrorMessage("参数错误!");
		}
		if (validateHelper.nullObj(domain.getPoorSource())) {
			result.addErrorMessage("困难原因不能为空!");
		}
		if (!validateHelper.nullObj(domain.getMemberNo())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getMemberNo().toString())
				&& validateHelper.illegalNumberZS(domain.getMemberNo()
						.toString())) {
			result.addErrorMessage("家庭人口最多输入长度10，且只能输入正整数!");
		}
		if (!validateHelper.emptyString(domain.getAccountNo())
				&& validateHelper.illegalStringLength(0, 16, domain
						.getAccountNo())) {
			result.addErrorMessage("户口号最多输入长度16!");
		}
		if (validateHelper.nullObj(domain.getPoorType())) {
			result.addErrorMessage("困难类型不能为空!");
		}
		if (validateHelper.nullObj(domain.getRequiredType())) {
			result.addErrorMessage("具体需求不能为空!");
		}
		if (!validateHelper.emptyString(domain.getDifficultyDegree())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getDifficultyDegree())) {
			result.addErrorMessage("困难程度最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getAttentionDegree())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getAttentionDegree())) {
			result.addErrorMessage("关注程度最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getCensusRegisterAddress())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getCensusRegisterAddress())) {
			result.addErrorMessage("户籍地址最多输入长度20!");
		}
		if (!validateHelper.emptyString(domain.getCensusRegisterNature())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getCensusRegisterNature())) {
			result.addErrorMessage("户籍性质最多输入长度20!");
		}
		if (!validateHelper.emptyString(domain.getHealthCondition())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getHealthCondition())) {
			result.addErrorMessage("健康状况最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getAnnualPerCapitaIncome())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getAnnualPerCapitaIncome())) {
			result.addErrorMessage("人均年收入最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getGoOutReason())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getGoOutReason())) {
			result.addErrorMessage("是否外出及原因最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getSkillsSpeciality())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getSkillsSpeciality())) {
			result.addErrorMessage("技能特长最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getHousingStructure())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getHousingStructure())) {
			result.addErrorMessage("住房结构最多输入长度10!");
		}
		if (!validateHelper.nullObj(domain.getHousingArea())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getHousingArea().toString())
				&& validateHelper.illegalNumberZS(domain.getHousingArea()
						.toString())) {
			result.addErrorMessage("住房面积最多输入长度10，且只能输入正整数!");
		}
		if (!validateHelper.emptyString(domain.getUnemploymentReason())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getUnemploymentReason())) {
			result.addErrorMessage("失业原因最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getRegistrationCardNumber())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getRegistrationCardNumber())) {
			result.addErrorMessage("登记证号最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getOtherInfo())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getOtherInfo())) {
			result.addErrorMessage("其他信息最多输入长度10!");
		}
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("姓名不能为空!");
		} else if (validateHelper.illegalStringLength(1, 20, domain.getName())) {
			result.addErrorMessage("姓名只能输入1-20个字符!");
		}
		if (validateHelper.emptyString(domain.getIdCardNo())) {
			result.addErrorMessage("身份证号不能为空!");
		} else if (validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage("请输入合法的身份证号码!");
		}
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
		if (validateHelper.nullObj(domain.getPosition())
				|| validateHelper.nullObj(domain.getPosition().getId())) {
			result.addErrorMessage("职业或身份不能为空!");
		}
		if (validateHelper.emptyString(domain.getSerialNumber())) {
			result.addErrorMessage("编号不能为空!");
		} else if (validateHelper.illegalStringLength(1, 30, domain
				.getSerialNumber())) {
			result.addErrorMessage("编号只能输入30个字符!");
		}
		if (validateHelper.nullObj(domain.getGender())
				|| validateHelper.nullObj(domain.getGender().getId())) {
			result.addErrorMessage("性别不能为空!");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 200, domain
						.getRemark())) {
			result.addErrorMessage("编号只能输入200个字符!");
		}
		return result;
	}
}
