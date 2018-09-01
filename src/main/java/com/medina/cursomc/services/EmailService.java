package com.medina.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.medina.cursomc.domain.Pedido;

public interface EmailService {

	
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
