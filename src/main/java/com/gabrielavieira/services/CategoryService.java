package com.gabrielavieira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabrielavieira.domain.Category;
import com.gabrielavieira.repositories.CategoryRepository;
import com.gabrielavieira.services.exceptions.DataIntegrityException;
import com.gabrielavieira.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category find(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id));
	}
	
	public Category insert(Category entity) {
		entity.setId(null);
		return repository.save(entity);
	}
	
	public Category update(Category entity) {
		find(entity.getId());
		return repository.save(entity);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a category that has products associated with it");
		}
	}

}
