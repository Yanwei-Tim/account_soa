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
import com.tianque.plugin.account.dao.LedgerPoorPeopleMemberDao;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;
import com.tianque.plugin.account.service.LedgerPoorPeopleMemberService;
import com.tianque.plugin.account.util.ComparisonAttribute;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Service("ledgerPoorpeopleMemberService")
@Transactional
public class LedgerPoorPeopleMemberServiceImpl implements
		LedgerPoorPeopleMemberService {

	@Autowired
	private LedgerPoorPeopleMemberDao ledgerPoorPeopleMemberDao;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	@Qualifier("ledgerPoorPeopleMemberValidatorImpl")
	private DomainValidator ledgerPoorPeopleMemberValidator;

	@Override
	public LedgerPoorPeopleMember getLedgerPoorPeopleMemberById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("查询参数未获得");
		}
		return ledgerPoorPeopleMemberDao.getLedgerPoorPeopleMemberById(id);
	}

	@Override
	public LedgerPoorPeopleMember addLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		ValidateResult result = ledgerPoorPeopleMemberValidator
				.validate(ledgerpoorpeopleMember);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			return getLedgerPoorPeopleMemberById(ledgerPoorPeopleMemberDao
					.addLedgerPoorPeopleMember(ledgerpoorpeopleMember));
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众成员新增失败!", e);
		}
	}

	@Override
	public void updateLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		ValidateResult result = ledgerPoorPeopleMemberValidator
				.validate(ledgerpoorpeopleMember);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			ledgerPoorPeopleMemberDao
					.updateLedgerPoorPeopleMember(ledgerpoorpeopleMember);
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众成员修改失败!", e);
		}
	}

	@Override
	public void deleteLedgerPoorPeopleMemberById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数未获得");
		}
		try {
			ledgerPoorPeopleMemberDao.deleteLedgerPoorPeopleMemberById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众成员删除失败!", e);
		}
	}

	@Override
	public void deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数未获得");
		}
		try {
			ledgerPoorPeopleMemberDao
					.deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(id);
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众成员删除失败!", e);
		}
	}

	@Override
	public List<LedgerPoorPeopleMember> findByLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople) {
		if (null == ledgerPoorPeople || ledgerPoorPeople.getId() == null) {
			throw new BusinessValidationException("查询参数未获得");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerPoorPeopleId", ledgerPoorPeople.getId());
		List<LedgerPoorPeopleMember> poorPeopleMembers = ledgerPoorPeopleMemberDao
				.findByLedgerPoorPeople(map);
		if (null != poorPeopleMembers && poorPeopleMembers.size() > 0) {
			try {
				for (LedgerPoorPeopleMember poorPeopleMember : poorPeopleMembers) {
					Class poorPeopleClass = poorPeopleMember.getClass();
					ComparisonAttribute.loadPropertyDictValue(poorPeopleMember,
							poorPeopleClass, propertyDictDubboService);
				}
			} catch (Exception e) {
				throw new ServiceValidationException("参数错误，查询失败!", e);
			}
		}
		return poorPeopleMembers;
	}

	@Override
	public void addPoorPeopleMembers(LedgerPoorPeople ledgerPoorPeople) {
		if (null != ledgerPoorPeople && null != ledgerPoorPeople.getId()
				&& null != ledgerPoorPeople.getLedgerPoorPeopleMembers()
				&& ledgerPoorPeople.getLedgerPoorPeopleMembers().size() > 0) {
			try {
				deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(ledgerPoorPeople
						.getId());
				for (LedgerPoorPeopleMember ledgerPoorPeopleMember : ledgerPoorPeople
						.getLedgerPoorPeopleMembers()) {
					ledgerPoorPeopleMember
							.setLedgerPoorPeople(ledgerPoorPeople);
					addLedgerPoorPeopleMember(ledgerPoorPeopleMember);
				}
			} catch (Exception e) {
				throw new ServiceValidationException("困难群众成员新增失败!", e);
			}
		}

	}
}
