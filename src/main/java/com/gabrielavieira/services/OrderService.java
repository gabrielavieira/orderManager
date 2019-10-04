package com.gabrielavieira.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.BankSlipPayment;
import com.gabrielavieira.domain.Order;
import com.gabrielavieira.domain.OrderItem;
import com.gabrielavieira.domain.enums.PaymentState;
import com.gabrielavieira.repositories.OrderItemRepository;
import com.gabrielavieira.repositories.OrderRepository;
import com.gabrielavieira.repositories.PaymentRepository;
import com.gabrielavieira.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BankSlipService bankSlipService;
	
	public Order find(Integer id) {
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id: " + id));
	}

	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setDate(new Date());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		
		if(obj.getPayment() instanceof BankSlipPayment ) {
			BankSlipPayment payment = (BankSlipPayment) obj.getPayment();
			bankSlipService.generateDueDate(payment, obj.getDate());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		
		for(OrderItem orderItem : obj.getItems()) {
			orderItem.setDiscount(0.0);
			orderItem.setOrder(obj);
			orderItem.setPrice(productService.find(orderItem.getProduct().getId()).getPrice());
		}
		orderItemRepository.saveAll(obj.getItems());
		
		return obj;
	}
}
