package com.tianque.core.base;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.DAOException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Session;

@Repository("baseDao")
@Transactional
public class BaseDaoImpl<T extends BaseDomain, SearchVo extends BaseDomain>
		extends AbstractBaseDao implements BaseDao<T, SearchVo> {

	protected static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	protected Class<T> entityClass;

	public void batchInsertDate(List<T> datas) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setCreateDate(session.getAccessTime());
			datas.get(i).setCreateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		batchInsertDate(datas, getInsertSqlId(entityClass.getSimpleName()));
	}

	public void batchUpdateDate(List<T> datas) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setUpdateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		batchUpdateDate(datas, getUpdateSqlId(entityClass.getSimpleName()));
	}

	public BaseDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Override
	public T get(Long id) {
		String className = entityClass.getSimpleName();
		return (T) getSqlMapClientTemplate().queryForObject(
				getSelectByIdSqlId(className), id);
	}

	@Override
	public T add(T entity) {
		try {
			checkEntityWhenAdd(entity);
			return get((Long) getSqlMapClientTemplate().insert(
					getInsertSqlId(getClassNameByEntity(entity)), entity));
		} catch (Exception e) {
			logger.error("添加数据失败", e);
			throw new DAOException("添加数据失败：" + e);
		}
	}

	@Override
	public T update(T entity) {
		checkEntityWhenUpdate(entity);
		getSqlMapClientTemplate().update(
				getUpdateSqlId(getClassNameByEntity(entity)), entity);
		return get(entity.getId());
	}

	@Override
	public void delete(Long id) {

		String className = entityClass.getSimpleName();
		getSqlMapClientTemplate().delete(getDeleteSqlId(className), id);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	@Override
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		String className = entityClass.getSimpleName();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				getCountResulSizeBySearchVoSqlId(className), searchVo);

		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getFindPageInfoBySearchVoSqlId(className), searchVo,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<T>(pageSize, pageSize, countNum, resultList);
	}

	private String getFindPageInfoBySearchVoSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "find" + className
				+ "s" + "BySearchVo";
	}

	private String getCountResulSizeBySearchVoSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "count"
				+ className + "s" + "BySearchVo";
	}

	/**
	 * 通过class获取slqMap的命名空间
	 * 
	 * @param className
	 * @return 例子：className : Druggy =>druggy
	 * 
	 */
	protected String getSqlNamespaceByClassName(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	/**
	 * 获取通过Id获取对象的sql语句的Id
	 * 
	 * @param className
	 * @return 例子druggy.getDruggyById
	 */
	protected String getSelectByIdSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "get" + className
				+ "ById";

	}

	/**
	 * 获取插入的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.addDruggy
	 */
	protected String getInsertSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "add" + className;
	}

	/**
	 * 获取删除语句的sqlId
	 * 
	 * @param className
	 * @return 例子：deleteMeetingWorkingRecordById
	 */
	protected String getDeleteSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "delete"
				+ className + "ById";
	}

	/**
	 * 获取修改的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.updateDruggy
	 */
	protected String getUpdateSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "update"
				+ className;
	}

	private String getClassNameByEntity(T entity) {
		return entity.getClass().getSimpleName();
	}

	protected void checkEntityWhenUpdate(T entity) {

	}

	protected void checkEntityWhenAdd(T entity) {
		try {
			Method method = entity.getClass().getMethod("validate");
			ValidateResult vresult = (ValidateResult) method.invoke(entity);
			if (null != vresult && vresult.hasError()) {
				throw new DAOException(vresult.getErrorMessages());
			}
		} catch (Exception e) {
			logger.error("获取pojo中的验证方法出错：", e);
			throw new DAOException(e.getMessage());
		}
	}

}
