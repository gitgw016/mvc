

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GoogleAuthenticator;

@WebServlet("/mailTest")
public class GoogleMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	class MyAuthentication extends Authenticator{
		
		PasswordAuthentication pas;
		
		public MyAuthentication() {
			String id = "gwxkas0106@gmail.com";
			String pw = "dzixttvetcxwffvo";
			pas = new PasswordAuthentication(id, pw);
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return pas;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SMTP(Simple Mail Transfer Protocol - 간이 우편 전송 규약)
		// SSL 보안 소켓 계층 - Secure Socket Layer
		// https://
		// TLS 전송 계층 보안 - Transport Layer Security
		Properties p = System.getProperties();
		
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "587");
		// jdk 1.8.3xx TLS1.1 issue
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		
		// TLS - 587 , SSL - 465
		System.out.println(p);
		
		//Authenticator auth = new MyAuthentication();
		//Session session = Session.getDefaultInstance(p,auth);
		GoogleAuthenticator auth = new GoogleAuthenticator();
		Session session = Session.getDefaultInstance(auth.getProperties(), auth);
		try {
			MimeMessage msg = new MimeMessage(session);
			// 받는 사람
			InternetAddress to = new InternetAddress("gwgwii@naver.com");
			msg.setRecipient(Message.RecipientType.TO, to);
			// TO 받는사람
			// CC 참조
			// BCC 숨은 참조
			InternetAddress from = new InternetAddress("mingu@gmail.net","MASTER");
			msg.setFrom(from);
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			//msg.setContent("<img src=''/>","utf-8");
			msg.setSubject("테스트용 제목밍구","utf-8");
			msg.setText("테스트 내용으로 보낸다밍구.","utf-8");
			
			javax.mail.Transport.send(msg);
			System.out.println("메일 전송~");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
