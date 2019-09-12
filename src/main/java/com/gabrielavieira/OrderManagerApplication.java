package com.gabrielavieira;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.domain.City;
import com.gabrielavieira.domain.Product;
import com.gabrielavieira.domain.State;
import com.gabrielavieira.repositories.CategoryRepository;
import com.gabrielavieira.repositories.CityRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category computing = new Category(null, "Computing");
		Category office = new Category(null, "Office");
		
		Product printer = new Product(null, "Printer", 2000.00);
		Product computer = new Product(null, "Computer", 800.00);
		Product mouse = new Product(null, "Mouse", 80.00);
		
		computing.getProducts().addAll(Arrays.asList(printer, computer, mouse));
		office.getProducts().addAll(Arrays.asList(printer));
		
		printer.getCategories().addAll(Arrays.asList(computing, office));
		computer.getCategories().addAll(Arrays.asList(computing));
		mouse.getCategories().addAll(Arrays.asList(computing));
		
		categoryRepository.saveAll(Arrays.asList(computing, office));
		productRepository.saveAll(Arrays.asList(printer, computer, mouse));
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
	}

}
