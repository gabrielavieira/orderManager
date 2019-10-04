package com.gabrielavieira.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.BankSlipPayment;

@Service
public class BankSlipService {
	
	/*
	 * Simulating a call from a web service that generates a ticket
	 * */
	public void generateDueDate(BankSlipPayment payment, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		payment.setDueDate(cal.getTime());
	}
}
