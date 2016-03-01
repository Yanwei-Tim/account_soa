package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyContacter;

public interface MyContacterDao {

	public MyContacter addMyContacter(MyContacter myContacter);

	public MyContacter updateMyContacter(MyContacter myContacter);

	public void deleteMyContacterById(Long id);

	public MyContacter getSimpleMyContacterById(Long id);

	public PageInfo<MyContacter> findMyContacterByOwnerIdForPage(Long ownerId,
			Integer page, Integer rows, String sidx, String sord);

	public List<MyContacter> findMyContacterByOwnerId(Long ownerId);

	public PageInfo<MyContacter> searchMyContacter(MyContacter myContacter,
			Integer page, Integer rows, String sidx, String sord);
	
	public List<MyContacter> findMyContactersByNameAndPinyinAndOwnerId(
			String name, Long id);

	public Integer getMyContacterByIdInMyGroup(Long ownerId);

}
