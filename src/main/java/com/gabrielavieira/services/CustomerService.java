package com.gabrielavieira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.Customer;
import com.gabrielavieira.repositories.CustomerRepository;
import com.gabrielavieira.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer find(Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
	}
}
