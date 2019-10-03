package com.gabrielavieira.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.domain.Product;
import com.gabrielavieira.repositories.CategoryRepository;
import com.gabrielavieira.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Page<Product> search(String name, 
								List<Integer> categoriesIds, 
								Integer page, 
								Integer linesPerPage, 
								String orderBy, 
								String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(categoriesIds);
		return repository.findDistinctByNameIgnoreCaseContainingAndCategoriesIn(name, categories, pageRequest);
	}

}
