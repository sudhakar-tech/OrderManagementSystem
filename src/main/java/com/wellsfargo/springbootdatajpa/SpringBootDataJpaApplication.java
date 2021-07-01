package com.wellsfargo.springbootdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.wellsfargo.springbootdatajpa.service.EmailSenderServiceImpl;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class SpringBootDataJpaApplication {
	
	@Autowired
	private EmailSenderServiceImpl emailSenderServiceImpl; 

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		emailSenderServiceImpl.sendSimpleEmail("sudhaksr570@gmail.com", "this order is successfully placed",
				"place the order");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

}
