package com.ty.webapp.config;

import java.util.Properties;

import org.springframework.stereotype.Component;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailConfiguration {
	public boolean sendEmail(String to, String from, String subject, String message) {
		boolean flag = false;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		String username = "webonliebatches";
		String password = "akap gyfb akhw cepy";

		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message2 = new MimeMessage(session);
			message2.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message2.setFrom(new InternetAddress(from));
			message2.setSubject(subject);
			message2.setText(message);

			Transport.send(message2);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}
