package com.tianque.userAuth.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;

public interface SessionManagerDubboService {
	public HashMap<String, Object> isLogin(String sessionId, String requestURI,
			String ipAddr);

	public boolean isFirstLogin();

	public void deleteSessionsWhenTimeOut();

	public HashMap<String, Object> login(User user, Session session,
			String oldSessionId, String sso);

	public PageInfo<Session> getSessionsByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order, Organization organization,
			int accountType);

	public void logout(String sessionId);

	public void deleteSessionBySessionId(String sessionId);

	public Session addSession(Session session);

	public Session findSessionBySessionId(String sessionId);

	public Session updateSessionAccessTimeBySessionId(String id,
			Date accessDate, String lastLoginUrl, String accessIp);

	public void validateUserSessionByUserName(String userName);

	public HashMap<String, Object> mockLogin(Session session, String userName,
			String oldSessionId, String curSessionId);

	public HashMap<String, Object> loginForMobile(User user, Session session,
			String oldSessionId, String imsiNo);

	/**
	 * 
	 * @Title: updateMobileVersion
	 * @Description: TODO手机端 登录时传入内部版本号和版本号参数
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-7-21 下午05:31:15
	 */
	public String updateMobileVersion(String mobileVersion,
			String mobileInnerVersion);

	public List<Session> findSessionByUserName(String userName);
}
