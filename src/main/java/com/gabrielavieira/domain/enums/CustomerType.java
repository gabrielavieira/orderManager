package com.gabrielavieira.domain.enums;

public enum CustomerType {
	FISICAL_PERSON (1, "Fisical Person"),
	LEGAL_PERSON(2, "Legal Person");
	
	private Integer code;
	private String description;
	
	private CustomerType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static CustomerType toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(CustomerType customer: CustomerType.values()){
			if(customer.getCode().equals(code)) {
				return customer;
			}
		}
		
		throw new IllegalArgumentException("Código Inválido: " + code);
	}
}
