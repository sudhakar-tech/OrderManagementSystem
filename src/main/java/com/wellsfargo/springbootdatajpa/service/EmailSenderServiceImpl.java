package com.wellsfargo.springbootdatajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	SimpleMailMessage message = null;
	
	@Override
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		message = new SimpleMailMessage();
		message.setFrom("85.sudha@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
		System.out.println("mail Send....");
	}

}
