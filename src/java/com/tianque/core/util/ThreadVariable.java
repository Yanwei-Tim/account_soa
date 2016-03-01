package com.tianque.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;

public class ThreadVariable {
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	
	public static void clearThreadVariable(){
		threadLocal.remove();
	}
	
	public static void setSession(Object session) {
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.LOGIN_SESSION_ID,session);
		threadLocal.set(map);
	}

	public static Session getSession() {
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (Session)map.get(GlobalValue.LOGIN_SESSION_ID);
		}
		return null;
	}
	
	public static void setUser(User user) {
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.LOGIN_USER_ID,user);
		threadLocal.set(map);
	}
	public static void setOrganization(Organization organization) {
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.LOGIN_ORGANIZATION,organization);
		threadLocal.set(map);
	}
	
	public static User getUser() {
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (User)map.get(GlobalValue.LOGIN_USER_ID);
		}
		return null;
	}
	
	public static Organization getOrganization() {
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (Organization)map.get(GlobalValue.LOGIN_ORGANIZATION);
		}
		return null;
	}
	
	public static void setPermissionEnameList(List<String> enames){
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.PERMISSIONS,enames);
		threadLocal.set(map);
	}
	
	public static List<String> getPermissionEnameList() {
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (List<String>)map.get(GlobalValue.PERMISSIONS);
		}
		return null;
	}
	
	public static Organization getUploadOrg(){
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (Organization)map.get(GlobalValue.UPLOAD_ORG);
		}
		return null;
	}
	
	public static void setUploadOrg(Organization uploadOrg){
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.UPLOAD_ORG,uploadOrg);
		threadLocal.set(map);
	}
	
	public static void setCheckOrgOrNot(int checkOrgOrNot){
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.CHECK_ORG_OR_NOT,checkOrgOrNot);
		threadLocal.set(map);
	}
	
	public static int getCheckOrgOrNot(){
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (Integer)map.get(GlobalValue.CHECK_ORG_OR_NOT);
		}
		return 1;
	}
	public static void setDataFrom(PropertyDict dataFrom){
		Map map = (Map)threadLocal.get();
		if(map==null){
			map=new HashMap();
		}
		map.put(GlobalValue.DATAFROM,dataFrom);
		threadLocal.set(map);
	}
	
	public static PropertyDict getDataFrom(){
		Map map = (Map)threadLocal.get();
		if(map!=null){
			return (PropertyDict)map.get(GlobalValue.DATAFROM);
		}
		return null;
	}
}