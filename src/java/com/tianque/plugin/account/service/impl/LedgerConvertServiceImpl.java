package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.DateUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.dao.LedgerConvertDao;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.service.LedgerConvertService;
import com.tianque.plugin.account.vo.LedgerConvertVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("ledgerConvertService")
@Transactional
public class LedgerConvertServiceImpl implements LedgerConvertService {

	@Autowired
	private LedgerConvertDao ledgerConvertDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	@Qualifier("ledgerConvertValidatorImpl")
	private DomainValidator<LedgerConvert> ledgerConvertValidator;

	@Override
	public boolean addLedgerConvert(LedgerConvert ledgerConvert) {
		ValidateResult result = ledgerConvertValidator.validate(ledgerConvert);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			return ledgerConvertDao.addLedgerConvert(ledgerConvert);
		} catch (Exception e) {
			throw new ServiceValidationException("新增事件转换台账信息失败!", e);
		}
	}

	public PageInfo<LedgerConvert> findLedgerConverts(
			LedgerConvertVo ledgerConvertVo, Integer page, Integer rows) {
		if (ledgerConvertVo == null || ledgerConvertVo.getCreateOrg() == null) {
			throw new BusinessValidationException("事件转换台账查询参数不对!");
		}
		if(ledgerConvertVo.getConvertEnd() != null){
			ledgerConvertVo.setConvertEnd(DateUtil.getEndTime(ledgerConvertVo.getConvertEnd()));
		}
		PageInfo<LedgerConvert> pageInfo = ledgerConvertDao.findLedgerConverts(
				ledgerConvertVo, page, rows);
		if (pageInfo != null) {
			List<LedgerConvert> list = pageInfo.getResult();
			for (LedgerConvert ledger : list) {
				if (ledger.getOccurOrg() != null
						&& ledger.getOccurOrg().getId() != null) {
					ledger.setOccurOrg(organizationDubboService
							.getSimpleOrgById(ledger.getOccurOrg().getId()));
				}
			}
		}
		return pageInfo;
	}

	@Override
	public LedgerConvert updateLedgerConvert(LedgerConvert ledgerConvert) {
		if (ledgerConvert == null || ledgerConvert.getId() == null) {
			throw new BusinessValidationException("事件转换台账更新参数不对!");
		}
		try {
			return ledgerConvertDao.updateLedgerConvert(ledgerConvert);
		} catch (Exception e) {
			throw new ServiceValidationException("更新事件转换台账信息失败!", e);
		}
	}

	@Override
	public LedgerConvert getSimpleLedgerConvertById(long id) {
		LedgerConvert ledgerConvert = ledgerConvertDao
				.getSimpleLedgerConvertById(id);
		if (ledgerConvert == null) {
			return null;
		}
		if (ledgerConvert.getOccurOrg() != null) {
			ledgerConvert.setOccurOrg(organizationDubboService
					.getSimpleOrgById(ledgerConvert.getOccurOrg().getId()));
		}
		if (ledgerConvert.getCreateOrg() != null) {
			ledgerConvert.setCreateOrg(organizationDubboService
					.getSimpleOrgById(ledgerConvert.getCreateOrg().getId()));
		}
		return ledgerConvert;
	}

}
