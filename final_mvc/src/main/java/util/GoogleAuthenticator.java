package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthenticator extends Authenticator{
	// 인증에 필요한 정보를 저장하는 객체
	// Session은 Authenticator의 getPasswordAuthentication method를 이용하여 사용자 인증 정보를 검색함.
	PasswordAuthentication passAuth;
	
	public GoogleAuthenticator() {
		try {
		URL url = GoogleAuthenticator.class.getResource("../prop/google_mail.properties");
		String path = url.getPath();
		System.out.println("path : "+path);
		Properties prop = new Properties();
		prop.load(new FileReader(path));
		String auth = prop.getProperty("auth");
		String pass = prop.getProperty("pass");
		System.out.println("auth : "+auth+ ", pass : "+pass);
		this.passAuth = new PasswordAuthentication(auth, pass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
	
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		return p;
	}
}
