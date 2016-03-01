package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;

public interface LedgerPoorPeopleMemberDubboService {

	/**
	 * 按ID获取困难群众成员
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPoorPeopleMember getLedgerPoorPeopleMemberById(Long id);

	/**
	 * 新增困难群众成员
	 * 
	 * @param ledgerpoorpeopleMember
	 * @return
	 */
	public LedgerPoorPeopleMember addLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember);

	/**
	 * 修改困难群众成员
	 * 
	 * @param ledgerpoorpeopleMember
	 * @return
	 */
	public void updateLedgerPoorPeopleMember(
			LedgerPoorPeopleMember ledgerpoorpeopleMember);

	/**
	 * 按ID删除困难群众成员
	 * 
	 * @param ledgerpoorpeopleMember
	 * @return
	 */
	public void deleteLedgerPoorPeopleMemberById(Long id);

	/**
	 * 按困难群众Id删除关联的成员
	 * 
	 * @param id
	 */
	public void deleteLedgerPoorPeopleMemberByLedgerPoorPeopleId(Long id);

	/**
	 * 按困难群众查找关联的成员
	 * 
	 * @param id
	 */
	public List<LedgerPoorPeopleMember> findByLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople);

	/**
	 * 按困难群众中的困难成员新增成员
	 * @param ledgerPoorPeople
	 */
	public void addPoorPeopleMembers(LedgerPoorPeople ledgerPoorPeople);

}
