package com.gabrielavieira.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.Order;
import com.gabrielavieira.repositories.OrderRepository;
import com.gabrielavieira.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order find(Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id: " + id));
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}
}
