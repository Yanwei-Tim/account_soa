package com.tianque;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class JettyStop {

    public static void main(String[] args) throws Exception {
    	int port=8079;
		if (args!=null && args.length>0){
			try{
				port=Integer.parseInt(args[0]);
			}catch(Exception e){
				
			}
		}
    	
        Socket s = new Socket(InetAddress.getByName("127.0.0.1"), port);
        OutputStream out = s.getOutputStream();
        System.out.println("*** sending jetty stop request");
        out.write(("\r\n").getBytes());
        out.flush();
        s.close();
    }

}
