package com.tianque.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.component.SessionManager;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.domain.Session;

public class ValidateCodeServlet extends HttpServlet {
	private int imgWidth = 60;
	private int imgHeight = 30;
	private int codeCount = 4;
	private int codeXPonit = 0;
	private int codeYPoint;
	private int codeHeight;
	private char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private SessionManager sessionManager;
	public void init() throws ServletException {
		codeXPonit = imgWidth / (codeCount + 1);
		codeYPoint = imgHeight - 4;
		codeHeight = imgHeight - 2;
		sessionManager=getSessionManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		proccess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		proccess(req, resp);
	}

	private void proccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		BufferedImage buffImg = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();
		Random random = new Random();
		fillAndDrawRect(gd);
//		drawLine(gd, random);
		String validateCode = drawValidateCode(gd, random);
		proccessSession(validateCode,request,response);
		response.setContentType("image/jpeg");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", servletOutputStream);
		servletOutputStream.close();
	}

	private String drawValidateCode(Graphics2D gd, Random random) {
		StringBuffer randomCode = new StringBuffer();
		
		Font font = new Font("Fixedsys", Font.PLAIN, codeHeight);
		gd.setFont(font);
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);

			gd.setColor(new Color(0, 0, 0));
			gd.drawString(strRand, (int)((double)(i + 0.7)) * codeXPonit+5, codeYPoint);

			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	private void fillAndDrawRect(Graphics2D gd) {
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, imgWidth, imgHeight);

		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, imgWidth - 1, imgHeight - 1);
	}

//	private void drawLine(Graphics2D gd, Random random) {
//		gd.setColor(Color.BLACK);
//		for (int i = 0; i < 40; i++) {
//			int x = random.nextInt(imgWidth);
//			int y = random.nextInt(imgHeight);
//			int xl = random.nextInt(12);
//			int yl = random.nextInt(12);
//			gd.drawLine(x, y, x + xl, y + yl);
//		}
//	}
	
	private void proccessSession(String validateCode,HttpServletRequest request, HttpServletResponse response){
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		sessionManager.deleteSessionBySessionId(sessionId);
		Session session=new Session();
		session.setSessionId(UUID.randomUUID().toString());
		session.setValidateCode(validateCode);
		session.setAccessTime(Calendar.getInstance().getTime());
		session = sessionManager.addSession(session);
		CookieUtil.putSessionIdInCookies(request, response, GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
 	}
	
	private SessionManager getSessionManager() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		SessionManager sessionManager = (SessionManager) applicationContext
				.getBean("sessionManager");
		return sessionManager;
	}
}
