package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

public interface WorkContacterDao {

	public WorkContacter addWorkContacter(WorkContacter workContacter);

	public WorkContacter updateWorkContacter(WorkContacter workContacter);

	

	public WorkContacter getSimpleWorkContacterById(Long id);
	
	public ContacterVo getSimpleContacterById(Long id);

	public PageInfo<WorkContacter> findWorkContacterForPage(Integer page,
			Integer rows, String sidx, String sord);

	public List<WorkContacter> findWorkContacter();
	
	public WorkContacter getSimpleWorkContacterByUserId(Long userId);
	
	public List<WorkContacter> findWorkContactersByNameAndPinyin(String name);
	
	public List<WorkContacter> findWorkContactersByOrganizationId(Long id);
	
}
