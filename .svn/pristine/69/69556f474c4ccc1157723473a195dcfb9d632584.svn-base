/**
 * tianque-com.tianque.dao-SysSessionUserDao.java Created on Apr 6, 2009
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.component.dao;

import java.util.Date;
import java.util.List;

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
public interface SessionDao {

	public Session addSession(Session session);

	public Session findSessionBySessionId(String id);

	public void deleteSessionBySessionId(String id);

	public Session updateSessionAccessTimeBySessionId(String id,
			Date accessDate, String lastLoginUrl,String accessIp);

	public int deleteSessionsWhenTimeOut();

	public PageInfo<Session> getSessions(int pageNum, int pageSize,
			String sortField, String order);

	

	public List<Session> findSessionByUserName(String userName);
	
	public Session updateSessionHasLogined(String sessionId,boolean logined);

	public PageInfo<Session> getSessionsByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order);

	public void validateUserSessionByUserName(String userName);

	
	
}
