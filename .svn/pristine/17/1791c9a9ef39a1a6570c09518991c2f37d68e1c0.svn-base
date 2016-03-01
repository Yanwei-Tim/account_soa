package com.tianque.core.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.PageInfo;

@Transactional
public abstract class  BaseServiceImpl<T extends BaseDomain,  SearchVo extends BaseDomain > implements BaseService<T,  SearchVo> {

	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	private BaseDao<T ,  SearchVo> baseDao;
	
	private void validatorId(Long id){
		if(id==null){
			logger.error("Id不能为空");
			throw new ServiceException("Id不能为空");
		}
	}
	
	@Override
	public  T get(Long id) {
		validatorId(id);
		
		return baseDao.get(id);
	}
	
	@Override
	public abstract T add(T entity) ;
	
	@Override
	public abstract T update(T entity) ;

	@Override
	public void delete(Long id) {
		validatorId(id);
		
		baseDao.delete(id);
	}

	@Override
	public void delete(Long[] ids) {
		if(ids==null || ids.length==0){
			throw new ServiceException("Id不能为空");
		}
		for(Long id : ids){
			delete(id);
		}
		
	}

	@Override
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		
		return baseDao.findPagerBySearchVo(searchVo, pageNum, pageSize, sidx, sord);
	}
	

	public BaseDao<T,  SearchVo> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T,  SearchVo> baseDao) {
		this.baseDao = baseDao;
	}
}
