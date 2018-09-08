package com.medina.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired private MailSender mailSender;
	
	@Autowired private JavaMailSender javaMailSender;
	
	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);


	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		
		log.info("Enviando email");
		mailSender.send(msg);
		log.info("Email Enviado");

	}


	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		log.info("Enviando email");
		javaMailSender.send(msg);
		log.info("Email Enviado");
		
	}

}