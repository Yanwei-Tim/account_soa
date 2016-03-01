package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.MyContacterDao;
import com.tianque.dao.MyGroupDao;
import com.tianque.dao.WorkContacterDao;
import com.tianque.domain.Contacter;
import com.tianque.domain.MyArea;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.service.ContacterService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("contacterService")
@Transactional
public class ContacterServiceImpl implements ContacterService {

	@Autowired
	private MyGroupDao myGroupDao;

	@Autowired
	private MyContacterDao myContacterDao;

	@Autowired
	private WorkContacterDao workContacterDao;
	
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	
	
	@Override
	public MyGroup addMyGroup(MyGroup myGroup) {
		if (!validate(myGroup))
			throw new ServiceException();
		setContacterChinesePinyin(myGroup);
		return myGroupDao.addMyGroup(myGroup);
	}

	@Override
	public List<MyGroup> findMyGroupByOwnerId(Long id) {
		return myGroupDao.findMyGroupByOwnerId(id);
	}

	@Override
	public MyGroup getSimpleMyGroupById(Long id) {
		return myGroupDao.getSimpleMyGroupById(id);
	}

	@Override
	public List<MyGroup> findMyGroupsByNameAndPinyinAndOwnerId(String name,
			Long id) {
		return myGroupDao.findMyGroupsByNameAndPinyinAndOwnerId(name, id);
	}

	@Override
	public MyGroup updateMyGroup(MyGroup myGroup) {
		if (!validate(myGroup))
			throw new ServiceException();
		setContacterChinesePinyin(myGroup);
		return myGroupDao.updateMyGroup(myGroup);
	}
	
	@Override
	public List<ContacterVo> findMyGroupHasContactersByGroupId(Long id) {
		return myGroupDao.findMyGroupHasContactersByGroupId(id);
	}
	
	@Override
	public List<WorkContacter> findWorkContacterById(Long id) {
		if(myGroupDao.isMyGroupById(id)){
			return myGroupDao.findMyGroupHasWorkContactersByGroupId(id);
		}else{
			WorkContacter workContacter=workContacterDao.getSimpleWorkContacterById(id);
			if(workContacter!=null)
				return Arrays.asList(new WorkContacter[]{workContacter});
		}
		return null;
	}

	private boolean validate(MyGroup myGroup) {
		if (myGroup == null)
			return false;
		if (myGroup.getName() == null || "".equals(myGroup.getName().trim()))
			return false;
		if (myGroup.getOwner() == null || myGroup.getOwner().getId() == null)
			return false;
		if (myGroup.getBelongClass() == null
				|| "".equals(myGroup.getBelongClass().trim()))
			return false;
		return true;
	}

	@Override
	public void addMyGroupHasContacter(Long groupId, Long contacterId) {
		myGroupDao.addMyGroupHasContacter(groupId, contacterId);
	}

	@Override
	public PageInfo<ContacterVo> findMyGroupHasContactersByGroupId(Long id,
			String belongClass, Integer page, Integer rows, String sidx,
			String sord) {
		return myGroupDao.findMyGroupHasContactersByGroupId(id, belongClass,
				page, rows, sidx, sord);
	}

	@Override
	public void deleteMyGroupHasAllContacterByGroupId(Long groupId) {
		myGroupDao.deleteMyGroupHasAllContacterByGroupId(groupId);
	}

	@Override
	public void deleteMyGroupHasSingleContacterByTwoId(Long groupId,
			Long contacterId) {
		myGroupDao.deleteMyGroupHasSingleContacterByTwoId(groupId, contacterId);
	}

	@Override
	public List<MyContacter> findMyContacterByOwnerId(Long ownerId) {
		return myContacterDao.findMyContacterByOwnerId(ownerId);
	}

	@Override
	public List<MyContacter> findMyContactersByNameAndPinyinAndOwnerId(
			String name, Long id) {
		return myContacterDao.findMyContactersByNameAndPinyinAndOwnerId(name,
				id);
	}

	@Override
	public MyContacter addMyContacter(MyContacter myContact) {
		if (!validate(myContact))
			throw new ServiceException();
		setContacterChinesePinyin(myContact);
		return myContacterDao.addMyContacter(myContact);
	}

	@Override
	public boolean deleteContacterById(Long id) {
		if(myContacterDao.getMyContacterByIdInMyGroup(id)>0)
			return false;
		else{
			myContacterDao.deleteMyContacterById(id);
			return true;
		}
	}

	@Override
	public PageInfo<MyContacter> findMyContacterByOwnerId(Long ownerId,
			Integer page, Integer rows, String sidx, String sord) {
		return myContacterDao.findMyContacterByOwnerIdForPage(ownerId, page,
				rows, sidx, sord);
	}

	@Override
	public MyContacter getSimpleMyContacterById(Long id) {
		return myContacterDao.getSimpleMyContacterById(id);
	}

	@Override
	public MyContacter updateMyContacter(MyContacter myContact) {
		if (!validate(myContact))
			throw new ServiceException();
		setContacterChinesePinyin(myContact);
		return myContacterDao.updateMyContacter(myContact);
	}

	private boolean validate(MyContacter myContact) {
		if (myContact == null)
			return false;
		if (myContact.getName() == null
				|| "".equals(myContact.getName().trim()))
			return false;
		if (myContact.getMobileNumber() == null
				|| "".equals(myContact.getMobileNumber().trim()))
			return false;
		if (myContact.getOwner() == null)
			return false;
		if (myContact.getBelongClass() == null
				|| "".equals(myContact.getBelongClass().trim()))
			return false;
		return true;
	}

	@Override
	public PageInfo<MyContacter> searchMyContacter(MyContacter myContact,
			Integer page, Integer rows, String sidx, String sord) {
		return myContacterDao.searchMyContacter(myContact, page, rows, sidx,
				sord);
	}

	@Override
	public WorkContacter addWorkContacter(WorkContacter workContacter) {
		if (!validate(workContacter))
			throw new ServiceException();
		setContacterChinesePinyin(workContacter);
		return workContacterDao.addWorkContacter(workContacter);
	}

	

	@Override
	public WorkContacter getSimpleWorkContacterByUserId(Long userId) {
		return workContacterDao.getSimpleWorkContacterByUserId(userId);
	}

	@Override
	public ContacterVo getSimpleContacterById(Long id) {
		return workContacterDao.getSimpleContacterById(id);
	}

	@Override
	public WorkContacter updateWorkContacter(WorkContacter workContacter) {
		if (!validate(workContacter))
			throw new ServiceException();
		setContacterChinesePinyin(workContacter);
		return workContacterDao.updateWorkContacter(workContacter);
	}

	@Override
	public List<WorkContacter> findWorkContacter() {
		return workContacterDao.findWorkContacter();
	}

	@Override
	public List<WorkContacter> findWorkContactersByNameAndPinyin(String name) {
		return workContacterDao.findWorkContactersByNameAndPinyin(name);
	}

	@Override
	public PageInfo<WorkContacter> findWorkContacterForUpate(Integer page,
			Integer rows, String sidx, String sord) {
		return workContacterDao
				.findWorkContacterForPage(page, rows, sidx, sord);
	}

	private boolean validate(WorkContacter workContacter) {
		if (workContacter == null)
			return false;
		if (workContacter.getName() == null
				|| "".equals(workContacter.getName().trim()))
			return false;
		if (workContacter.getMobileNumber() == null
				|| "".equals(workContacter.getMobileNumber().trim()))
			return false;
		if (workContacter.getFromUser() == null)
			return false;
		if (workContacter.getBelongClass() == null
				|| "".equals(workContacter.getBelongClass().trim()))
			return false;
		return true;
	}

	private void setContacterChinesePinyin(Contacter contacter) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(contacter.getName());
		contacter.setFullPinyin(pinyin.get("fullPinyin"));
		contacter.setSimplePinyin(pinyin.get("simplePinyin"));
	}

	@Override
	public List<WorkContacter> findWorkContactersByOrganizationId(Long id) {
		return workContacterDao.findWorkContactersByOrganizationId(id);
	}

	@Override
	public List<MyArea> findMyAreaByOrgIdAndOrgLevelAndOrgType(Map<Long,List<Integer>> map,List<Integer> orgTypeInternals,List<Long> exceptOrgIds) {
		List<MyArea> myAreas=new ArrayList<MyArea>();
		for(Long key:map.keySet()){
			MyArea myArea=new MyArea();
			myArea.setOrganization(organizationDubboService.getSimpleOrgById(key));
			myArea.setOrganizations(
					organizationDubboService.
					findOrgsByOrgCodeAndOrgLevelInternalsAndOrgTypeInternals(
							myArea.getOrganization().getOrgInternalCode(),
							map.get(key),orgTypeInternals,
							exceptOrgIds));
			myAreas.add(myArea);
		}
		return myAreas;
	}

}
