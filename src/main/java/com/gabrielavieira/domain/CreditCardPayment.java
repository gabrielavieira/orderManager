package com.gabrielavieira.domain;

import javax.persistence.Entity;

import com.gabrielavieira.domain.enums.PaymentState;

@Entity
public class CreditCardPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public CreditCardPayment() {}

	public CreditCardPayment(Integer id, PaymentState state, Order order, Integer numberOfInstallments) {
		super(id, state, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
}
