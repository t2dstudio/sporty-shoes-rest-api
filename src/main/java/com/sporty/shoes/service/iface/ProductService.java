package com.sporty.shoes.service.iface;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sporty.shoes.entity.Product;

public interface ProductService {

	public String addProduct(@Valid Product product);

	public String updateProduct(@Valid Product product);
	
	String deleteProduct(@Valid Long productId);

	public List<Product> getProducts(Pageable pageable);

}