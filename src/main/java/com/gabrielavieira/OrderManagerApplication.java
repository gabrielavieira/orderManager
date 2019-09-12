package com.gabrielavieira;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.domain.Product;
import com.gabrielavieira.repositories.CategoryRepository;
import com.gabrielavieira.repositories.ProductRepository;

@SpringBootApplication
public class OrderManagerApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

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
	}

}
