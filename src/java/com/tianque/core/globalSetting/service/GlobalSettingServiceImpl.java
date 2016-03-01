package com.tianque.core.globalSetting.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.globalSetting.dao.GlobalSettingDao;
import com.tianque.core.globalSetting.util.GlobalSetting;

@Service("globalSettingService")
@Transactional
public class GlobalSettingServiceImpl extends AbstractBaseService implements
		GlobalSettingService {

	@Autowired
	private GlobalSettingDao globalSettingDao;
	@Autowired
	private CacheService cacheService;

	private Map<String, String> addGlobalSetting(Map<String, String> map) {
		if (map == null || map.size() == 0)
			throw new ServiceException();
		cachedToMemcached(map);
		try {
			String val = JSONUtil.serialize(map);
			val = globalSettingDao.addGlobalSetting(val);
			return (Map<String, String>) JSONUtil.deserialize(val);
		} catch (JSONException e) {
			logger.error("异常信息", e);
		}
		return null;
	}

	@Override
	public String getGlobalValue(String key) {
		Object cachedVal=cacheService.get(CacheKeyGenerator.generateCacheKeyFromString(
				GlobalSetting.class, key));
		if(cachedVal!=null)
			return cachedVal.toString();
		Map<String, String> map = getGlobalSetting();
		cachedToMemcached(map);
		if(map==null){
			return null;
		}
		
		String globalValue = map.get(key);
		if(null == globalValue){
			cacheService.set(CacheKeyGenerator.generateCacheKeyFromString(
					GlobalSetting.class, key),"");
		}
		return globalValue;
	}

	@Override
	public Map<String, String> getGlobalSetting() {
		String val = globalSettingDao.getGlobalSetting();
		if (val == null || "".equals(val.trim()))
			return null;
		try {
			return (Map<String, String>) JSONUtil.deserialize(val);
		} catch (JSONException e) {
			logger.error("异常信息", e);
		}
		return null;
	}

	@Override
	public Map<String, String> updateGlobalSetting(Map<String, String> map) {
		if (map == null || map.size() == 0)
			throw new ServiceException();
		if (getGlobalSetting() == null)
			return addGlobalSetting(map);
		cachedToMemcached(map);
		try {
			String val = JSONUtil.serialize(map);
			val = globalSettingDao.updateGlobalSetting(val);
			return (Map<String, String>) JSONUtil.deserialize(val);
		} catch (JSONException e) {
			logger.error("异常信息", e);
		}
		return null;
	}
	
	private void cachedToMemcached(Map<String, String> map) {
		if(map==null)
			return ;
		Set<Entry<String, String>> entrys = map.entrySet();
		for (Entry<String, String> entry : entrys) {
			cacheService.set(CacheKeyGenerator.generateCacheKeyFromString(
					GlobalSetting.class, entry.getKey()), entry.getValue());
		}
	}

	
}
