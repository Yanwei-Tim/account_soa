package com.tianque;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;



public class Jetty {
	private static Server server;
	
	public static void main(String[] args) throws Exception {
		// change the default file encoding to utf-8
		// need add the -Dfile.encoding=utf-8 to command line in deploy
		// environment
		int port=8086;
		int listenerport=8076;
		String path="/";
		String rootdir="./webroot";
		if (args!=null && args.length>0){
			try{
				port=Integer.parseInt(args[0]);
			}catch(Exception e){
			}
		}
		if (args!=null && args.length>1){
			try{
				listenerport=Integer.parseInt(args[1]);
			}catch(Exception e){
			}
		}
		
		if (args!=null && args.length>2){
			path=args[2].trim();
		}
		
		if (args!=null && args.length>3){
			rootdir=args[3].trim();
		}
		
		System.setProperty("file.encoding", "utf-8");
		server = new Server();
		
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(10);
		threadPool.setMaxThreads(500);
		server.setThreadPool(threadPool);
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		//每个请求被accept前允许等待的连接数
		connector.setHeaderBufferSize(8192*2);
		
		connector.setAcceptQueueSize(50);
		//同事监听read事件的线程数
		connector.setAcceptors(2);
		//连接最大空闲时间，默认是200000，-1表示一直连接
		connector.setMaxIdleTime(3000);
		//表示线程资源稀少时的maxIdleTime
		connector.setLowResourceMaxIdleTime(1000);
//		connector.setThreadPool(threadPool);
		
		server.setConnectors(new Connector[] { connector });
		WebAppContext context = new WebAppContext(rootdir, path);
		context.setOverrideDescriptor(Jetty.class.getResource("/")+"web_override_development.xml");
		HandlerCollection handlers = new HandlerCollection();
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		RequestLogHandler requestLogHandler = new RequestLogHandler();
		handlers.setHandlers(new Handler[] { contexts, new DefaultHandler(),
				requestLogHandler });
		contexts.addHandler(context);
		server.setHandler(handlers);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		Thread monitor = new MonitorThread(listenerport);
		monitor.start();
		server.start();
		server.join();
	}

	private static class MonitorThread extends Thread {

		private ServerSocket socket;

		public MonitorThread(int listenerport) {
			setDaemon(true);
			setName("StopMonitor");
			try {
				socket = new ServerSocket(listenerport, 1, InetAddress
						.getByName("127.0.0.1"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void run() {
			System.out.println("*** running jetty 'stop' thread");
			Socket accept;
			try {
				accept = socket.accept();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(accept.getInputStream()));
				reader.readLine();
				System.out.println("*** stopping jetty embedded server");
				server.stop();
				accept.close();
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
}
