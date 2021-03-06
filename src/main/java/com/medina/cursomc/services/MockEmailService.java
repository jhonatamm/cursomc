package com.medina.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

	
	
	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		log.info("Simulando Envio de email");
		log.info(msg.toString());
		log.info("Email Enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		log.info("Simulando Envio de email HTML");
		log.info(msg.toString());
		log.info("Email Enviado");
	}

}
