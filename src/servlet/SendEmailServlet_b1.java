package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.UserDao_b1;

import model.User_b1;
import util.DateUtil;
import util.GenRandomPassword;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet_b1")
public class SendEmailServlet_b1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet_b1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html ; charset=utf-8");
		String host = "smtp.gmail.com";
		int port = 587;
		String username = "ai.bartender1111@gmail.com";
		String password = "aibartender1111";
		
		String to = request.getParameter("email");
		String act = request.getParameter("act");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String bday = request.getParameter("bday");
		boolean b = new UserDao_b1().updatePassword(to , password);
		User_b1 user = new UserDao_b1().findUser(to);
		if(act.equals("forgetpass_b1")) {
			if(user == null) {
				response.getWriter().append("查無此帳號");
				return;
			}
		}else {
			if(user != null) {
				response.getWriter().append("此帳號已被使用");
				return;
			}
		}
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			if(act.equals("forgetpass_b1")) {
				String newRandomPassword = GenRandomPassword.getPassword(10);
				message.setSubject("AI_Bartender：重新設定密碼");
				message.setContent(
						"<font face=\"微軟正黑體\">"
						+ "	<h2>"
						+ "		您好，我們已收到您申請重新設置密碼的需求，此郵件會協助您重新設置密碼。<br/><br/>"
						+ "		您的新密碼為：<span style='color:red'>"+newRandomPassword+"</span><br/><br/>"
						+ "		請先使用此密碼 <a href='"+request.getRequestURL()+"/../jsp/front/login_b1.jsp'> 登入 </a> 後再至會員管理進行修改密碼動作。<br/><br/>"
						+ "		AI_Bartender 敬上"
						+ "	</h2>"
						, "text/html;charset=utf-8");
				Transport transport = session.getTransport("smtp");
				transport.connect(host, port, username, password);
				Transport.send(message);
				
				user.setName(user.getName());
				user.setPassword(newRandomPassword);
				user.setMobile(user.getMobile());
				user.setAddress(user.getAddress());
				user.setUpdateTime(new DateUtil().getNowDateTime());
				b = new UserDao_b1().updateUser(user);
			}else {
				message.setSubject("查無此帳號");
				
			}
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
