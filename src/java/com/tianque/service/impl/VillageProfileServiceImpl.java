package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.dao.VillageProfileDao;
import com.tianque.domain.Organization;
import com.tianque.domain.VillageProfile;
import com.tianque.service.VillageProfileService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
@Service("villageProfileService")
public class VillageProfileServiceImpl extends AbstractBaseService implements VillageProfileService{

	@Autowired
	private VillageProfileDao villageProfileDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Override
	public VillageProfile addVillageProfile(VillageProfile villageProfile) {
		try {
			autoFillOrganizationInternalCode(villageProfile);
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
		return villageProfileDao.addVillageProfile(villageProfile);
	}

	@Override
	public VillageProfile findVillageProfileById(Long id,Long orgId) {
		return villageProfileDao.findVillageProfileById(id,orgId);
	}
	
	@Override
	public VillageProfile deletePhotosAndOrgById(String imgUrl,Long id,Long orgId) {
		if(imgUrl == null || id == null || orgId == null){
			throw new ServiceException("参数错误");
			}
		VillageProfile villageProfile = villageProfileDao.findVillageProfileById(id,orgId);
		if(("/"+villageProfile.getImgUrl()).equals(imgUrl)){
			villageProfile.setImgUrl(null);
			villageProfile = villageProfileDao.updateVillageProfile(villageProfile);						
		}
		return villageProfile;
	}

	@Override
	public VillageProfile updateVillageProfile(VillageProfile villageProfile) {
		return villageProfileDao.updateVillageProfile(villageProfile);
	}

	public void autoFillOrganizationInternalCode(VillageProfile villageProfile){
		if(villageProfile.getOrganization() == null){
			throw new ServiceException("找不到指定网格");
		}else{
			Organization organization =  organizationDubboService.getSimpleOrgById(villageProfile.getOrganization().getId());
			villageProfile.setOrgInternalCode(organization.getOrgInternalCode());
		}
	}

	@Override
	public VillageProfile getVillageProfileByFullPinYin(String fullPinYin){
		return villageProfileDao.getVillageProfileByFullPinYin(fullPinYin);
	}

	@Override
	public void deleteVillageProfile(Long orgId) {
		if (orgId==null) {
			throw new ServiceException("orgId不能为空");
		}
		villageProfileDao.deleteVillageProfile(orgId);
	}

	@Override
	public VillageProfile getVillageProfileByOrgId(Long orgId) {
		if(orgId == null){
			throw new ServiceException("orgId为空");
		}
		
		return villageProfileDao.getVillageProfileByOrgId(orgId);
	}

}
