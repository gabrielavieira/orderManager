package com.gabrielavieira.domain.enums;

public enum PaymentState {
	
	PENDING (1, "Pending"),
	SETTLED (2, "Settled"),
	CANCELED (3, "Canceled");
	
	private Integer code;
	private String description;
	
	private PaymentState(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentState toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(PaymentState paymentState : PaymentState.values()){
			if(paymentState.getCode() == code) {
				return paymentState;
			}
		}
		
		throw new IllegalArgumentException("Código não encontrado.");
	}
}
