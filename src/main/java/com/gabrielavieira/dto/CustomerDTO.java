package com.gabrielavieira.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gabrielavieira.domain.Address;
import com.gabrielavieira.domain.Customer;

public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer customerType;
	private List<Address> address = new ArrayList<Address>();
	private Set<String> phones = new HashSet<>();
	
	public CustomerDTO() {}
	
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.cpfOrCnpj = customer.getCpfOrCnpj();
		this.customerType = customer.getCustomerType().getCode();
		this.address = customer.getAddress();
		this.phones = customer.getPhones();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}
	
	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}
	
	public Integer getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	public List<Address> getAddress() {
		return address;
	}
	
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	public Set<String> getPhones() {
		return phones;
	}
	
	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

}
