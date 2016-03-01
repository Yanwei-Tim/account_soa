package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.ServiceException;
import com.tianque.dao.VillageProfileDao;
import com.tianque.domain.Organization;
import com.tianque.domain.VillageProfile;

@Repository("villageProfileDao")
public class VillageProfileDaoImpl extends AbstractBaseDao implements VillageProfileDao {

	@Override
	public VillageProfile addVillageProfile(VillageProfile villageProfile) {
			Long id = (Long)getSqlMapClientTemplate().insert(
							"villageProfile.addVillageProfile", villageProfile);
		return findVillageProfileById(id,null);
	}

	@Override
	public VillageProfile findVillageProfileById(Long id,Long orgId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		VillageProfile villageProfile=new VillageProfile();
		try {
			villageProfile = (VillageProfile) getSqlMapClientTemplate()
					.queryForObject("villageProfile.getvillageProfileResultById", map);
		} catch (DataAccessException e) {
			logger.error("异常信息", e);
		}
		return villageProfile;
	}

	@Override
	public VillageProfile updateVillageProfile(VillageProfile villageProfile) {
		getSqlMapClientTemplate().update("villageProfile.updateVillageProfile",
				villageProfile);
		VillageProfile villageProfiles = findVillageProfileById(villageProfile.getId(),null);
		return villageProfiles;
	}

	@Override
	public VillageProfile getVillageProfileByFullPinYin(String fullPinYin){
	    Organization organization = new Organization();
	    organization.setFullPinyin(fullPinYin);
		return (VillageProfile) getSqlMapClientTemplate().queryForObject("villageProfile.selectByfullPinyin", organization);
	}

	@Override
	public void deleteVillageProfile(Long orgId) {
		getSqlMapClientTemplate().delete("villageProfile.deleteVillageProfile",orgId);
	}

	@Override
	public VillageProfile getVillageProfileByOrgId(Long orgId) {
		if(orgId == null){
			throw new ServiceException("orgId为空");
		}
		return (VillageProfile)getSqlMapClientTemplate().queryForObject("villageProfile.getVillageProfileByOrgId", orgId);
	}

}
