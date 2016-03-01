package com.tianque.dao;

import com.tianque.domain.VillageProfile;

public interface VillageProfileDao {
	/**
	 * 查询VillageProfile的信息
	 * @param id
	 * @param orgId
	 * @return
	 */
	public VillageProfile findVillageProfileById(Long id,Long orgId);

	/**
	 * 添加村基本系信息
	 * @param villageProfile
	 * @return
	 */
	public VillageProfile addVillageProfile(VillageProfile villageProfile) ;

	/**
	 * 修改村基本信息
	 * @param villageProfile
	 * @return
	 */
	public VillageProfile updateVillageProfile(VillageProfile villageProfile);


	public VillageProfile getVillageProfileByFullPinYin(String fullPinYin);
	
	public void deleteVillageProfile(Long orgId);

	public VillageProfile getVillageProfileByOrgId(Long orgId);

}
