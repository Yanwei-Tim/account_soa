package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerPoorPeopleMemberDubboService;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;
import com.tianque.plugin.account.service.LedgerPoorPeopleMemberService;

@Component
public class LedgerPoorPeopleMemberDubboServiceImpl implements
		LedgerPoorPeopleMemberDubboService {

	@Autowired
	private LedgerPoorPeopleMemberService ledgerPoorPeopleMemberService;

	@Override
	public LedgerPoorPeopleMember addLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		return ledgerPoorPeopleMemberService
				.addLedgerPoorPeopleMember(ledgerpoorpeopleMember);
	}

	@Override
	public void addPoorPeopleMembers(LedgerPoorPeople ledgerPoorPeople) {
		ledgerPoorPeopleMemberService.addPoorPeopleMembers(ledgerPoorPeople);
	}

	@Override
	public void deleteLedgerPoorPeopleMemberById(Long id) {
		ledgerPoorPeopleMemberService.deleteLedgerPoorPeopleMemberById(id);

	}

	@Override
	public void deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(Long id) {
		ledgerPoorPeopleMemberService
				.deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(id);

	}

	@Override
	public List<LedgerPoorPeopleMember> findByLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople) {
		return ledgerPoorPeopleMemberService
				.findByLedgerPoorPeople(ledgerPoorPeople);
	}

	@Override
	public LedgerPoorPeopleMember getLedgerPoorPeopleMemberById(Long id) {
		return ledgerPoorPeopleMemberService.getLedgerPoorPeopleMemberById(id);
	}

	@Override
	public void updateLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember) {
		ledgerPoorPeopleMemberService
				.updateLedgerPoorPeopleMember(ledgerpoorpeopleMember);
	}

}
