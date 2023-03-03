package com.nelioalves.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nelioalves.services.DBService;
import com.nelioalves.services.EmailService;
import com.nelioalves.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTesteDatabase();
		return true;
	}
	
	//para instanciar a interface
	@Bean //anotacao torna o metodo disponivel como componente no sistema
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
