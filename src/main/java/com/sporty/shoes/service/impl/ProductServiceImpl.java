package com.sporty.shoes.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sporty.shoes.entity.Product;
import com.sporty.shoes.repositry.ProductRepository;
import com.sporty.shoes.service.iface.ProductService;
import com.sporty.shoes.util.Constants;

@SuppressWarnings("unused")
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepositry;

	@Override
	public String addProduct(@Valid Product product) {
		product.setCreatedAt(new Date());
		product.setModifiedAt(new Date());
		if (productRepositry.save(product) != null) {
			return Constants.productSaved;

		} else {
			return Constants.productNotSaved;
		}
	}

	@Override
	public String updateProduct(@Valid Product product) {
		if (product.getId() < 0) {
			return Constants.productIdLtZero;
		}

		if (productRepositry.existsById(product.getId())) {
			product.setModifiedAt(new Date());
			if (productRepositry.save(product) != null) {
				return Constants.productUpdatedSuccess;

			} else {
				return Constants.productNotUpdated;
			}
		} else {
			return Constants.productNotExist;
		}

	}

	@Override
	public String deleteProduct(Long productId) {
		if (productId < 0) {
			return Constants.productIdLtZero;
		}
		if (productRepositry.existsById(productId)) {
			productRepositry.deleteById(productId);
			return Constants.productDeleted;
		} else {
			return Constants.productNotExist;
		}

	}

	@Override
	public List<Product> getProducts(Pageable pageable) {
		return productRepositry.findAllByPage(pageable).getContent();
	}

}
