package com.gabrielavieira;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielavieira.domain.Address;
import com.gabrielavieira.domain.BankSlipPayment;
import com.gabrielavieira.domain.Category;
import com.gabrielavieira.domain.City;
import com.gabrielavieira.domain.CreditCardPayment;
import com.gabrielavieira.domain.Customer;
import com.gabrielavieira.domain.Order;
import com.gabrielavieira.domain.OrderItem;
import com.gabrielavieira.domain.Payment;
import com.gabrielavieira.domain.Product;
import com.gabrielavieira.domain.State;
import com.gabrielavieira.domain.enums.CustomerType;
import com.gabrielavieira.domain.enums.PaymentState;
import com.gabrielavieira.repositories.AddressRepository;
import com.gabrielavieira.repositories.CategoryRepository;
import com.gabrielavieira.repositories.CityRepository;
import com.gabrielavieira.repositories.CustomerRepository;
import com.gabrielavieira.repositories.OrderItemRepository;
import com.gabrielavieira.repositories.OrderRepository;
import com.gabrielavieira.repositories.PaymentRepository;
import com.gabrielavieira.repositories.ProductRepository;
import com.gabrielavieira.repositories.StateRepository;

@SpringBootApplication
public class OrderManagerApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category computing = new Category(null, "Computing");
		Category office = new Category(null, "Office");
		
		Product product1 = new Product(null, "Printer", 2000.00);
		Product product2 = new Product(null, "Computer", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		
		computing.getProducts().addAll(Arrays.asList(product1, product2, product3));
		office.getProducts().addAll(Arrays.asList(product1));
		
		product1.getCategories().addAll(Arrays.asList(computing, office));
		product2.getCategories().addAll(Arrays.asList(computing));
		product3.getCategories().addAll(Arrays.asList(computing));
		
		categoryRepository.saveAll(Arrays.asList(computing, office));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Customer customer1 = new Customer(null, "Gabriela Vieira", "gabriela@gmail.com", "36378912377", CustomerType.FISICAL_PERSON);
		
		Address address1 = new Address(null, "Street 1", 12, "","Neighborhood 1", "60540-888", city1, customer1);
		Address address2 = new Address(null, "Street 2", 55, "","Neighborhood 2", "60540-455", city2, customer1);
		
		customer1.getPhones().addAll(Arrays.asList("987788777", "685542211"));
		customer1.getAddress().addAll(Arrays.asList(address1, address2));
		
		customerRepository.saveAll(Arrays.asList(customer1));
		addressRepository.saveAll(Arrays.asList(address1, address2));
		
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order order1 = new Order(null, sdt.parse("30/09/2017 10:32"), customer1, address1);
		Order order2 = new Order(null, sdt.parse("30/10/2017 10:32"), customer1, address2);
		
		Payment payment1 = new CreditCardPayment(null, PaymentState.SETTLED, order1, 1);
		order1.setPayment(payment1);
		
		Payment payment2 = new BankSlipPayment(null, PaymentState.CANCELED, order2, sdt.parse("03/11/2017 10:32"), null);
		order2.setPayment(payment2);
		
		customer1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		
		OrderItem orderItem1 =  new OrderItem(order1, product1, 0.0, 1, 2000.00);
		OrderItem orderItem2 =  new OrderItem(order1, product3, 0.0, 2, 80.00);
		OrderItem orderItem3 =  new OrderItem(order2, product2, 100.0, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem2));
		
		product1.getItems().addAll(Arrays.asList(orderItem1));
		product2.getItems().addAll(Arrays.asList(orderItem3));
		product3.getItems().addAll(Arrays.asList(orderItem2));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
	}

}
