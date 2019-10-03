package com.gabrielavieira.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielavieira.domain.Order;
import com.gabrielavieira.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	OrderService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll(){
		List<Order> objects = service.findAll();
		return ResponseEntity.ok().body(objects);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order order = service.find(id);
		return ResponseEntity.ok().body(order);
	}

}
