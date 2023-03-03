package com.nelioalves.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
