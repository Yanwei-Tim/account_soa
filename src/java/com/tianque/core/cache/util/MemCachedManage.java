package com.tianque.core.cache.util;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 
 * @author 龙振东
 * 
 */
public class MemCachedManage {
	private static MemCachedClient mcc = new MemCachedClient();

	private int initConn = 10;
	private int minConn = 5;
	private int maxConn = 20;
	private long maxIdle = 1000 * 60 * 60 * 6;
	private long maintSleep = 60;
	private int socketTO = 3000;
	private int socketConnectTO = 3000;
	private String servers;

	private void init() {
		String[] serversList = {};
		if (null != servers && !"".equals(servers)) {
			serversList = servers.split(",");
		}
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(serversList);
		pool.setInitConn(initConn);
		pool.setMinConn(minConn);
		pool.setMaxConn(maxConn);
		pool.setMaxIdle(maxIdle);
		pool.setMaintSleep(maintSleep);
		pool.setNagle(false);
		pool.setSocketTO(socketTO);// 设置 读取 超时
		pool.setSocketConnectTO(socketConnectTO); // 连接超时
		pool.initialize();
	}

	public boolean set(String key, Object value, Date timeOut) {
		return mcc.set(key, value, timeOut);
	}

	/**
	 * 同一个key只能添加一次
	 * 
	 * @param key
	 * @param value
	 * @param timeOut
	 * @return
	 */
	public boolean add(String key, Object value, Date timeOut) {
		return mcc.add(key, value, timeOut);
	}

	public long getCounter(String key) {
		return mcc.addOrIncr(key);
	}

	public long incr(String key) {
		return mcc.addOrIncr(key, 1);
	}

	public long incr(String key, int incr) {
		return mcc.incr(key, Long.parseLong(incr + ""));
	}

	public long decr(String key, int decr) {
		return mcc.decr(key, Long.parseLong(decr + ""));
	}

	public void storeCounter(String key, long counter, Date expire) {
		mcc.storeCounter(key, counter, expire);
	}

	public long storeCounter(String key, int counter) {
		return mcc.addOrIncr(key, Long.parseLong(counter + ""));
	}

	public Object get(String key) {
		return mcc.get(key);
	}

	public void delete(String key) {
		mcc.delete(key);
	}

	public void flushAll() {
		mcc.flushAll();
	}

	public int getInitConn() {
		return initConn;
	}

	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public long getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(long maxIdle) {
		this.maxIdle = maxIdle;
	}

	public long getMaintSleep() {
		return maintSleep;
	}

	public void setMaintSleep(long maintSleep) {
		this.maintSleep = maintSleep;
	}

	public int getSocketTO() {
		return socketTO;
	}

	public void setSocketTO(int socketTO) {
		this.socketTO = socketTO;
	}

	public int getSocketConnectTO() {
		return socketConnectTO;
	}

	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getServers() {
		return servers;
	}

}