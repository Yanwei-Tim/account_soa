/**
 * tianque-com.tianque.dao.impl-SysSessionUserDaoImpl.java Created on Apr 6, 2009
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.component.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.GridProperties;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Session;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * 
 * @description ***<br/>
 * 
 * @version 1.0
 */
@Repository("sessionDao")
public class SessionDaoImpl extends AbstractBaseDao implements SessionDao {

	@Autowired
	private CacheService cacheService;

	private Session loadSessionFromDatabaseById(String sessionId) {
		return (Session) getSqlMapClientTemplate().queryForObject(
				"session.findSessionBySessionId", sessionId);
	}

	@Override
	public Session addSession(Session session) {
		getSqlMapClientTemplate().insert("session.addSession", session);
		session = loadSessionFromDatabaseById(session.getSessionId());

		String cacheKey = CacheKeyGenerator.generateCacheKeyFromString(
				Session.class, session.getSessionId());
		cacheSession(session, cacheKey);

		return session;
	}

	private void cacheSession(Session session, String cacheKey) {
		cacheService.set(cacheKey, (int) GridProperties.SESSION_TIME_OUT / 1000,
				session);
	}

	@Override
	public Session findSessionBySessionId(String sessionId) {

		Session session = (Session) cacheService.get(CacheKeyGenerator
				.generateCacheKeyFromString(Session.class, sessionId));
		if (session == null) {
			session = loadSessionFromDatabaseById(sessionId);
			if(session==null) return null;
			String cacheKey = CacheKeyGenerator.generateCacheKeyFromString(
					Session.class, session.getSessionId());
			cacheSession(session, cacheKey);
		}
		return session;
	}

	@Override
	public void deleteSessionBySessionId(String id) {
		cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
				Session.class, id));
		getSqlMapClientTemplate()
				.delete("session.deleteSessionBySessionId", id);
	}
	/**
	 * 当上次访问和本次访问之间的间隔在3秒以内，忽略访问本次访问不进行数据库Session Update
	 */
	@Override
	public Session updateSessionAccessTimeBySessionId(String id,
			Date accessTime, String lastLoginUrl, String accessIp) {
		Session session = this.findSessionBySessionId(id);
		if(!isBlinkAccess(id, accessTime, session)){
			updateSession(id, accessTime, lastLoginUrl, accessIp);
		}

		return updateCachedSession(session,id,
				accessTime, lastLoginUrl, accessIp);
	}
	
	/**
	 * 是否眨眼访问
	 * 当上次访问和本次访问之间的间隔在3秒以内，则是眨眼访问
	 * 
	 * @param id
	 * @param accessTime
	 * @param session
	 * @return
	 */
	private boolean isBlinkAccess(String id,Date accessTime,Session session){
		if(accessTime.getTime()-session.getAccessTime().getTime()<=3000){
			return true;
		}
		return false;
	}
	
	private void updateSession(String id, Date accessTime, String lastLoginUrl,
			String accessIp) {
//		Map<String ,Object> map = new HashMap<String ,Object>();
//		map.put("sessionId", id);
//		map.put("accessTime", accessTime);
//		map.put("accessIp", accessIp);
//		map.put("lastUrl", lastLoginUrl);
		
		Session sessionVo=new Session();
		sessionVo.setAccessIp(accessIp);
		sessionVo.setSessionId(id);
		sessionVo.setLastUrl(lastLoginUrl);
		sessionVo.setAccessTime(accessTime);
		
		getSqlMapClientTemplate().update(
				"session.updateSessionAccessTimeBySessionId", sessionVo);
	}
	


	private Session updateCachedSession(Session session,String id,
			Date accessTime, String lastLoginUrl, String accessIp) {
		session.setAccessTime(accessTime);
		session.setAccessIp(accessIp);
		session.setLastUrl(lastLoginUrl);
		cacheService.set(CacheKeyGenerator.generateCacheKeyFromString(
				Session.class, id), (int) GridProperties.SESSION_TIME_OUT / 1000,
				session);
		return session;
	}

	@Override
	public int deleteSessionsWhenTimeOut() {
		long time = Calendar.getInstance().getTime().getTime()
				- GridProperties.SESSION_TIME_OUT;
		int deleteCount = (int) getSqlMapClientTemplate().delete(
				"session.deleteSessionsWhenTimeOut", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time)));
		return deleteCount;
	}

	@Override
	public PageInfo<Session> getSessions(int pageNum, int pageSize,
			String sortField, String order) {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("order", order);
		map.put("sortField", sortField);
		List list = getSqlMapClientTemplate()
				.queryForList("session.findSessions", map,
						(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"session.countSessions");
		PageInfo<Session> pageInfo = new PageInfo<Session>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<Session> getSessionsByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("isLogin", true);
		map.put("order", order);
		map.put("orgCode", orgInternalCode);
		map.put("sortField", sortField);
		List list = getSqlMapClientTemplate()
				.queryForList("session.findSessionsByOrgCode", map,
						(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"session.countSessionsByOrgCode",map);
		PageInfo<Session> pageInfo = new PageInfo<Session>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	
	@Override
	public List<Session> findSessionByUserName(String userName) {
		return (List) getSqlMapClientTemplate().queryForList(
				"session.findSessionByUserName", userName);
	}

	@Override
	public Session updateSessionHasLogined(String sessionId, boolean logined) {
		Session session=this.findSessionBySessionId(sessionId);
		if(session==null)
			return null;
		if(session.isLogin()==logined)
			return session;
		else
			session.setLogin(logined);
		cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
				Session.class, sessionId));
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("id",session.getId());
		map.put("logined", logined);
		getSqlMapClientTemplate().update(
				"session.updateSessionHasLogined", map);
		return session;
	}

	@Override
	public void validateUserSessionByUserName(String userName) {
		List<Session> sessions=findSessionByUserName(userName);
		getSqlMapClientTemplate().delete(
				"session.validateUserSessionByUserName", userName);
		for (Session session:sessions){
			cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
					Session.class, session.getSessionId()));
		}
	}

	
}
