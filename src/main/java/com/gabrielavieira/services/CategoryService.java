package com.gabrielavieira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category find(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElse(null);
	}

}
