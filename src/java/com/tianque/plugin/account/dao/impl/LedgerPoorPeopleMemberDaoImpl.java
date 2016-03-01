package com.tianque.plugin.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.LedgerPoorPeopleMemberDao;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;

@Repository("ledgerPoorPeopleMemberDao")
public class LedgerPoorPeopleMemberDaoImpl extends AbstractBaseDao implements
		LedgerPoorPeopleMemberDao {

	@Override
	public LedgerPoorPeopleMember getLedgerPoorPeopleMemberById(Long id) {
		return (LedgerPoorPeopleMember) getSqlMapClientTemplate()
				.queryForObject(
						"ledgerPoorPeopleMember.getLedgerPoorPeopleMemberById",
						id);
	}

	@Override
	public Long addLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		return (Long) getSqlMapClientTemplate().insert(
				"ledgerPoorPeopleMember.addLedgerPoorPeopleMember",
				ledgerpoorpeopleMember);
	}

	@Override
	public void updateLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		getSqlMapClientTemplate().update(
				"ledgerPoorPeopleMember.updateLedgerPoorPeopleMember",
				ledgerpoorpeopleMember);

	}

	@Override
	public void deleteLedgerPoorPeopleMemberById(Long id) {
		getSqlMapClientTemplate().delete(
				"ledgerPoorPeopleMember.deleteLedgerPoorPeopleMemberById", id);
	}

	@Override
	public void deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(Long id) {
		getSqlMapClientTemplate()
				.delete("ledgerPoorPeopleMember.deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId",
						id);

	}

	@Override
	public List<LedgerPoorPeopleMember> findByLedgerPoorPeople(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(
				"ledgerPoorPeopleMember.findByLedgerPoorPeople", map);
	}

}
