package com.tianque.core.cache.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.core.util.StringUtil;

@Service("cacheService")
@SuppressWarnings("serial")
public class MemCacheService extends AbstractBaseService implements
		CacheService {
	private static final String NAMESPACE_PREFIX = "namespace_cache_";

	@Autowired
	@Qualifier("memCachedManage")
	private MemCachedManage memCachedManage;// java memcached 管理类 单例的

	@Override
	public Object get(String key) {
		try {
			if (StringUtil.isStringAvaliable(key)) {
				return memCachedManage.get(key);
			}
			return null;
		} catch (Exception e) {
			logger.error(
					"Exception occur when getCached value with key=" + key, e);
			return null;
		}
	}

	@Override
	public void set(String key, final Object value) {
		if (null == key || null == value) {
			return;
		}
		memCachedManage
				.set(key, value, new Date(1000 * DEFAULT_EXPIRED_SECOND));
	}

	@Override
	public void set(String key, int expried, final Object value) {
		try {
			if (null == key || null == value) {
				return;
			}
			memCachedManage.set(key, value, new Date(1000 * expried));
		} catch (Exception e) {
			logger.error("Exception occur when setCached value with key=" + key
					+ ",value=" + value, e);
		}
	}

	@Override
	public void remove(String key) {
		try {
			if (StringUtil.isStringAvaliable(key)) {
				memCachedManage.delete(key);
			}
		} catch (Exception e) {
			logger.error("Exception occur when removeCached value with key="
					+ key, e);
		}
	}

	private String getNamespaceActualKey(String namespace) {
		return NAMESPACE_PREFIX + namespace;
	}

	private long getNamespaceSeqNo(String namespace) {
		String namespaceKey = getNamespaceActualKey(namespace);
		return memCachedManage.getCounter(namespaceKey);

	}

	private String getCachedValueActualKey(String namespace, String key) {
		long currentNamespaceSeq = getNamespaceSeqNo(namespace);
		return key + "_" + Long.toString(currentNamespaceSeq);
	}

	@Override
	public Object get(String namespace, String key) {
		return get(getCachedValueActualKey(namespace, key));
	}

	@Override
	public void invalidateNamespaceCache(String namespace) {
		try {
			if (StringUtil.isStringAvaliable(namespace)) {
				memCachedManage.incr(getNamespaceActualKey(namespace));
			}
		} catch (Exception e) {
			logger.error(
					"Exception occur when invalidateNamespaceCache[namespace="
							+ namespace + "]", e);
		}
	}

	@Override
	public void remove(String namespace, String key) {
		if (StringUtil.isStringAvaliable(namespace)
				&& StringUtil.isStringAvaliable(key)) {
			remove(getCachedValueActualKey(namespace, key));
		}
	}

	@Override
	public void set(String namespace, String key, Object value) {
		if (StringUtil.isStringAvaliable(namespace)
				&& StringUtil.isStringAvaliable(key)) {
			set(getCachedValueActualKey(namespace, key), value);
		}
	}

	@Override
	public void set(String namespace, String key, int expried, Object value) {
		if (StringUtil.isStringAvaliable(namespace)
				&& StringUtil.isStringAvaliable(key)) {
			set(getCachedValueActualKey(namespace, key), expried, value);
		}
	}

	@Override
	public boolean add(String key, Object value) {
		if (StringUtil.isStringAvaliable(key)) {
			return (Boolean) memCachedManage.add(key, value, new Date(
					1000 * DEFAULT_EXPIRED_SECOND));
		}
		return false;
	}

	@Override
	public boolean add(String key, int expried, Object value) {
		if (StringUtil.isStringAvaliable(key)) {
			return memCachedManage.add(key, value, new Date(1000 * expried));
		}
		return false;
	}

	@Override
	public boolean add(String namespace, String key, Object value) {
		if (StringUtil.isStringAvaliable(namespace)
				&& StringUtil.isStringAvaliable(key)) {
			return add(getCachedValueActualKey(namespace, key), value);
		}
		return false;
	}

	@Override
	public boolean add(String namespace, String key, int expried, Object value) {
		if (StringUtil.isStringAvaliable(namespace)
				&& StringUtil.isStringAvaliable(key)) {
			return add(getCachedValueActualKey(namespace, key), expried, value);
		}
		return false;
	}

}
