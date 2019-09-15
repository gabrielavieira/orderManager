package com.gabrielavieira.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.gabrielavieira.domain.enums.PaymentState;

@Entity
public class BankSlipPayment extends Payment {
	
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	
	private Date paymentDate;
	
	public BankSlipPayment() {}

	public BankSlipPayment(Integer id, PaymentState state, Order order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
