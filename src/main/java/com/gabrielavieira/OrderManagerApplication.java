package com.gabrielavieira;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.repositories.CategoryRepository;

@SpringBootApplication
public class OrderManagerApplication implements CommandLineRunner {
	
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category informatica = new Category(null, "Informática");
		Category escritorio = new Category(null, "Escritório");
	
		categoryRepository.saveAll(Arrays.asList(informatica, escritorio));
	}

}
