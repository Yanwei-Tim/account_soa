package com.tianque.plugin.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.LedgerSteadyWorkPeopleInfoDao;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;
import com.tianque.plugin.account.service.LedgerSteadyWorkPeopleInfoService;
import com.tianque.plugin.account.util.ComparisonAttribute;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Service("ledgerSteadyWorkPeopleInfoService")
@Transactional
public class LedgerSteadyWorkPeopleInfoServiceImpl implements
		LedgerSteadyWorkPeopleInfoService {

	@Autowired
	private LedgerSteadyWorkPeopleInfoDao ledgerSteadyWorkPeopleInfoDao;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	@Qualifier("ledgerSteadyWorkPeopleInfoValidatorImpl")
	private DomainValidator<LedgerSteadyWorkPeopleInfo> ledgerSteadyWorkPeopleInfoValidator;

	@Override
	public LedgerSteadyWorkPeopleInfo getLedgerSteadyWorkPeopleInfoById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("查询参数未获得");
		}
		return ledgerSteadyWorkPeopleInfoDao
				.getLedgerSteadyWorkPeopleInfoById(id);
	}

	@Override
	public LedgerSteadyWorkPeopleInfo addLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerpoorpeopleMember) {
		ValidateResult result = ledgerSteadyWorkPeopleInfoValidator
				.validate(ledgerpoorpeopleMember);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			return getLedgerSteadyWorkPeopleInfoById(ledgerSteadyWorkPeopleInfoDao
					.addLedgerSteadyWorkPeopleInfo(ledgerpoorpeopleMember));
		} catch (Exception e) {
			throw new ServiceValidationException("稳定工作成员新增失败!", e);
		}
	}

	@Override
	public void updateLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerpoorpeopleMember) {
		ValidateResult result = ledgerSteadyWorkPeopleInfoValidator
				.validate(ledgerpoorpeopleMember);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			ledgerSteadyWorkPeopleInfoDao
					.updateLedgerSteadyWorkPeopleInfo(ledgerpoorpeopleMember);
		} catch (Exception e) {
			throw new ServiceValidationException("稳定工作成员修改失败!", e);
		}
	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数未获得");
		}
		try {
			ledgerSteadyWorkPeopleInfoDao
					.deleteLedgerSteadyWorkPeopleInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("稳定工作成员删除失败!", e);
		}
	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数未获得");
		}
		try {
			ledgerSteadyWorkPeopleInfoDao
					.deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(id);
		} catch (Exception e) {
			throw new ServiceValidationException("稳定工作成员删除失败!", e);
		}
	}

	@Override
	public List<LedgerSteadyWorkPeopleInfo> findByLedgerSteadyWork(
			LedgerSteadyWork ledgerSteadyWork) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerSteadyWorkId", ledgerSteadyWork.getId());
		List<LedgerSteadyWorkPeopleInfo> steadyWorkPeopleInfos = ledgerSteadyWorkPeopleInfoDao
				.findByLedgerSteadyWork(map);
		if (null != steadyWorkPeopleInfos && steadyWorkPeopleInfos.size() > 0) {
			for (LedgerSteadyWorkPeopleInfo steadyWorkPeopleInfo : steadyWorkPeopleInfos) {
				loadPropertyDictValue(steadyWorkPeopleInfo);
			}
		}
		return steadyWorkPeopleInfos;
	}

	/**
	 * 加载该对象的所有字典项属性
	 * 
	 * @param ledgerPoorPeople
	 */
	private void loadPropertyDictValue(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo) {
		if (null == ledgerSteadyWorkPeopleInfo) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			Class poorPeopleClass = ledgerSteadyWorkPeopleInfo.getClass();
			ComparisonAttribute.loadPropertyDictValue(
					ledgerSteadyWorkPeopleInfo, poorPeopleClass,
					propertyDictDubboService);
		} catch (Exception e) {
			throw new ServiceValidationException("参数错误，查询失败!", e);
		}
	}

	@Override
	public void addLedgerSteadyWorkPeopleInfos(LedgerSteadyWork ledgerSteadyWork) {
		if (null == ledgerSteadyWork || null == ledgerSteadyWork.getId()
				|| null == ledgerSteadyWork.getLedgerSteadyWorkPeopleInfos()
				|| ledgerSteadyWork.getLedgerSteadyWorkPeopleInfos().size() < 0) {
			throw new BusinessValidationException("参数错误！稳定工作成员新增失败!");
		}
		try {
			deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(ledgerSteadyWork
					.getId());
			for (LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo : ledgerSteadyWork
					.getLedgerSteadyWorkPeopleInfos()) {
				ledgerSteadyWorkPeopleInfo
						.setLedgerSteadyWork(ledgerSteadyWork);
				addLedgerSteadyWorkPeopleInfo(ledgerSteadyWorkPeopleInfo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("稳定工作成员新增失败!", e);
		}

	}
}
