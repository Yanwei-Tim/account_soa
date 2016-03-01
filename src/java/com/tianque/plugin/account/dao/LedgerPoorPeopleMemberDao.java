package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;

public interface LedgerPoorPeopleMemberDao {

	public LedgerPoorPeopleMember getLedgerPoorPeopleMemberById(Long id);

	public Long addLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember);

	public void updateLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember);

	public void deleteLedgerPoorPeopleMemberById(Long id);

	public void deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(Long id);

	public List<LedgerPoorPeopleMember> findByLedgerPoorPeople(
			Map<String, Object> map);

}
