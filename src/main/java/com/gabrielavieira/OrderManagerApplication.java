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
		Category c1 = new Category(null, "Computing");
		Category c2 = new Category(null, "Office");
		Category c3 = new Category(null, "Gardening");
		Category c4 = new Category(null, "Perfumery");
		Category c5 = new Category(null, "c5");
		Category c6 = new Category(null, "c6");
		Category c7 = new Category(null, "c7");
		Category c8 = new Category(null, "c8");
		Category c9 = new Category(null, "c9");
		Category c10 = new Category(null, "c10");
		Category c11 = new Category(null, "c11");
		Category c12 = new Category(null, "c12");
		
		Product p1 = new Product(null, "Printer", 2000.00);
		Product p2 = new Product(null, "Computer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p1));
		c2.getProducts().addAll(Arrays.asList(p2, p4));
		c3.getProducts().addAll(Arrays.asList(p5, p6));
		c4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		c5.getProducts().addAll(Arrays.asList(p8));
		c6.getProducts().addAll(Arrays.asList(p9, p10));
		c7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(c1, c2));
		p2.getCategories().addAll(Arrays.asList(c1));
		p3.getCategories().addAll(Arrays.asList(c1));
		
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		
		OrderItem orderItem1 =  new OrderItem(order1, p1, 0.0, 1, 2000.00);
		OrderItem orderItem2 =  new OrderItem(order1, p3, 0.0, 2, 80.00);
		OrderItem orderItem3 =  new OrderItem(order2, p2, 100.0, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem2));
		
		p1.getItems().addAll(Arrays.asList(orderItem1));
		p2.getItems().addAll(Arrays.asList(orderItem3));
		p3.getItems().addAll(Arrays.asList(orderItem2));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
		
	}

}
