package com.gabrielavieira.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielavieira.domain.BankSlipPayment;
import com.gabrielavieira.domain.CreditCardPayment;

@Configuration
public class JacksonConfig {
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
		
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(CreditCardPayment.class);
				objectMapper.registerSubtypes(BankSlipPayment.class);
				super.configure(objectMapper);
			};
		};
		
		return builder;
	}
}
