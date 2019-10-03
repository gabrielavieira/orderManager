package com.gabrielavieira.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielavieira.domain.Product;
import com.gabrielavieira.dto.ProductDTO;
import com.gabrielavieira.resources.utils.URL;
import com.gabrielavieira.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<Page<ProductDTO>> search(
			@RequestParam(name = "name", defaultValue = "") String name, 
			@RequestParam(name = "categories", defaultValue = "") String categories, 
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction){
		
		Page<Product> result = service.search(  URL.decodeParam(name), 
												URL.decodeIntList(categories), 
												page, 
												linesPerPage, 
												orderBy, 
												direction);
		
		Page<ProductDTO> productDTO = result.map((obj) -> new ProductDTO(obj));
		
		return ResponseEntity.ok().body(productDTO);
	}
}
