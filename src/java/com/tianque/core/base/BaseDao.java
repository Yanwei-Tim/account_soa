package com.tianque.core.base;

import java.util.List;

import com.tianque.core.vo.PageInfo;

public interface BaseDao<T extends BaseDomain, SearchVo extends BaseDomain> {

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(Long id);

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public T add(T entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public T update(T entity);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(Long id);

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(Long[] ids);

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 批量插入数据
	 * 
	 * @param datas
	 */
	public void batchInsertDate(List<T> datas);

	/**
	 * 批量更新数据
	 * 
	 * @param datas
	 */
	public void batchUpdateDate(List<T> datas);
}
