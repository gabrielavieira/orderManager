package com.gabrielavieira.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielavieira.domain.Address;
import com.gabrielavieira.domain.City;
import com.gabrielavieira.domain.Customer;
import com.gabrielavieira.domain.enums.CustomerType;
import com.gabrielavieira.dto.CustomerDTO;
import com.gabrielavieira.dto.CustomerNewDTO;
import com.gabrielavieira.repositories.AddressRepository;
import com.gabrielavieira.repositories.CustomerRepository;
import com.gabrielavieira.services.exceptions.DataIntegrityException;
import com.gabrielavieira.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Customer find(Integer id) {
		Optional<Customer> customer = repository.findById(id);
		return customer.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}
	
	@Transactional
	public Customer insert(Customer entity) {
		entity.setId(null);
		entity = repository.save(entity);
		addressRepository.saveAll(entity.getAddress());
		return entity;
	}
	
	public Customer update(Customer entity) {
		Customer oldObj = find(entity.getId());
		updateData(oldObj, entity);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a customer.");
		}
	}
	
	public List<Customer> findAll(){
		return repository.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Customer fromDTO(CustomerDTO objDTO) {
		return new Customer(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	public Customer fromDTO(CustomerNewDTO objDTO) {
		City city = new City(objDTO.getCityId(), null, null);
		
		Customer customer = new Customer(null, 
										objDTO.getName(), 
										objDTO.getEmail(), 
										objDTO.getCpfOrCnpj(), 
										CustomerType.toEnum(objDTO.getCustomerType()));
		
		Address address = new Address(null, 
				objDTO.getStreet(), 
				objDTO.getNumber(), 
				objDTO.getComplement(), 
				objDTO.getNeighborhood(), 
				objDTO.getCep(), 
				city, 
				customer);
		
		customer.addPhoneNumber(objDTO.getPhone1());
		customer.addPhoneNumber(objDTO.getPhone2());
		customer.addPhoneNumber(objDTO.getPhone3());
		customer.getAddress().add(address);
		
		return customer;
	}
	
	private void updateData(Customer oldObj, Customer newObj) {
		oldObj.setName(newObj.getName());
		oldObj.setEmail(newObj.getEmail());
	}
}
