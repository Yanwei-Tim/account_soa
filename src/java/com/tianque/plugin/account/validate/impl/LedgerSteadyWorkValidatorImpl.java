package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerSteadyWork;

@Service("ledgerSteadyWorkValidatorImpl")
public class LedgerSteadyWorkValidatorImpl implements
		DomainValidator<LedgerSteadyWork> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerSteadyWork domain) {
		ValidateResult result = new ValidateResult();

		if (!validateHelper.nullObj(domain.getInvolvingSteadyNum())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getInvolvingSteadyNum().toString())
				&& validateHelper.illegalNumberZS(domain
						.getInvolvingSteadyNum().toString())) {
			result.addErrorMessage("涉稳群体人数最多输入长度10，且只能输入正整数!");
		}
		if (validateHelper.nullObj(domain.getInvolvingSteadyType())
				|| validateHelper.nullObj(domain.getInvolvingSteadyType()
						.getId())) {
			result.addErrorMessage("涉稳人群类别不能为空!");
		}
		if (validateHelper.emptyString(domain.getInvolvingSteadyInfo())) {
			result.addErrorMessage("涉稳事项不能为空!");
		} else if (validateHelper.illegalStringLength(1, 500,
				domain.getInvolvingSteadyInfo())) {
			result.addErrorMessage("涉稳事项只能输入1-500个字符!");
		}

		if (!validateHelper.emptyString(domain.getSteadyUnit())
				&& validateHelper.illegalStringLength(0, 60,
						domain.getSteadyUnit())) {
			result.addErrorMessage("稳控责任单位最多输入长度60!");
		}
		if (!validateHelper.emptyString(domain.getSteadyUser())
				&& validateHelper.illegalStringLength(0, 10,
						domain.getSteadyUser())) {
			result.addErrorMessage("稳控责任人最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getResolveUnit())
				&& validateHelper.illegalStringLength(0, 60,
						domain.getResolveUnit())) {
			result.addErrorMessage("化解责任部门最多输入长度60!");
		}
		if (!validateHelper.emptyString(domain.getResolveUser())
				&& validateHelper.illegalStringLength(0, 10,
						domain.getResolveUser())) {
			result.addErrorMessage("化解责任人最多输入长度10!");
		}
		if (!validateHelper.emptyString(domain.getSteadyInfo())
				&& validateHelper.illegalStringLength(0, 300,
						domain.getSteadyInfo())) {
			result.addErrorMessage("涉稳人员或群体稳定现状最多输入长度300!");
		}
		if (validateHelper.nullObj(domain.getSteadyWorkType())
				|| validateHelper.nullObj(domain.getSteadyWorkType().getId())) {
			result.addErrorMessage("涉稳类型不能为空!");
		}
		if (validateHelper.nullObj(domain.getSteadyWorkProblemType())
				|| validateHelper.nullObj(domain.getSteadyWorkProblemType()
						.getId())) {
			result.addErrorMessage("涉稳问题类型不能为空!");
		}
		if (validateHelper.emptyString(domain.getSerialNumber())) {
			result.addErrorMessage("编号不能为空!");
		} else if (validateHelper.illegalStringLength(1, 30,
				domain.getSerialNumber())) {
			result.addErrorMessage("编号只能输入30个字符!");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(1, 20, domain.getName())) {
				result.addErrorMessage("姓名只能输入1-20个字符!");
		}
		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
				result.addErrorMessage("请输入合法的身份证号码!");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalStringLength(0, 15,
						domain.getMobileNumber())
				&& validateHelper.illegalNumberZS(domain.getMobileNumber())) {
			result.addErrorMessage("联系电话最多输入长度15，且只能输入正整数!");
		}
		if (!validateHelper.emptyString(domain.getPermanentAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getPermanentAddress())) {
			result.addErrorMessage("常住地址最多输入长度50!");
		}
		if (validateHelper.nullObj(domain.getSteadyWorkWarnLevel())
				|| validateHelper.nullObj(domain.getSteadyWorkWarnLevel().getId())) {
			result.addErrorMessage("预警级别不能为空!");
		}
		if (validateHelper.nullObj(domain.getSteadyWorkWarnLevelDate())) {
			result.addErrorMessage("预警时间不能为空!");
		}
		if (domain.isAnonymity()) {
			return result;
		}
		/*取消验证**/
//		if (validateHelper.emptyString(domain.getName())) {
//			result.addErrorMessage("姓名不能为空!");
//		}
//		if (validateHelper.emptyString(domain.getIdCardNo())) {
//			result.addErrorMessage("身份证号不能为空!");
//		}
//		if (validateHelper.nullObj(domain.getGender())
//				|| validateHelper.nullObj(domain.getGender().getId())) {
//			result.addErrorMessage("性别不能为空!");
//		}
//		
//		if (validateHelper.nullObj(domain.getPosition())
//				|| validateHelper.nullObj(domain.getPosition().getId())) {
//			result.addErrorMessage("职业或身份不能为空!");
//		}
		

		return result;
	}
}
