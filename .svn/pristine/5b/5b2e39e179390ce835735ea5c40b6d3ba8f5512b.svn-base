package com.tianque.core.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.GridProperties;

public class OpenfireConnectListener implements ServletContextListener {
	private static Logger logger = LoggerFactory
			.getLogger(OpenfireConnectListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		GlobalSetting.conn.disconnect();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
			XMPPConnection conn = new XMPPConnection(
					GridProperties.OPENFIRESERVER);
			conn.connect();
			conn.login("admin", "admin");
			GlobalSetting.conn = conn;
		} catch (XMPPException e) {
			logger.error("连接openfire服务器失败", e);
		}
	}
}
