package com.sporty.shoes.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.dto.ProductDTO;
import com.sporty.shoes.dto.UserDTO;
import com.sporty.shoes.entity.Product;
import com.sporty.shoes.entity.User;
import com.sporty.shoes.service.iface.ProductService;
import com.sporty.shoes.util.Constants;
import com.sporty.shoes.util.ControllerUtil;

@RestController
@RequestMapping("/product")
@SuppressWarnings("unchecked")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/add")
	public <T> ResponseEntity<T> addProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
		List<String> errorMessages = ControllerUtil.errorHandling(result);
		if (errorMessages.size() == 0) {
			Product product = modelMapper.map(productDTO, Product.class);
			String message = productService.addProduct(product);
			if (message.equals(Constants.productSaved)) {
				return new ResponseEntity<T>((T) message, HttpStatus.OK);
			} else if (message.equals(Constants.productNotSaved)) {
				return new ResponseEntity<T>((T) message, HttpStatus.SERVICE_UNAVAILABLE);
			}
		} else {
			return new ResponseEntity<T>((T) errorMessages, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<T>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	@PostMapping("/update")
	public <T> ResponseEntity<T> updateProduct(@Valid @RequestParam Long productId, @Valid @RequestBody ProductDTO productDTO,
			BindingResult result) {
		List<String> errorMessages = ControllerUtil.errorHandling(result);
//		System.out.println("Error message : " + errorMessages);
		if (errorMessages.size() == 0) {
			Product product = modelMapper.map(productDTO, Product.class);
			product.setId(productId);
			String message = productService.updateProduct(product);
			if (message.equals(Constants.productIdLtZero)) {
				return new ResponseEntity<T>((T) message, HttpStatus.BAD_REQUEST);
			} else if (message.equals(Constants.productUpdatedSuccess)) {
				return new ResponseEntity<T>((T) message, HttpStatus.OK);
			} else if (message.equals(Constants.productNotUpdated)) {
				return new ResponseEntity<T>((T) message, HttpStatus.NOT_MODIFIED);
			} else if (message.equals(Constants.productNotExist)) {
				return new ResponseEntity<T>((T) message, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<T>((T) errorMessages, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<T>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	@PostMapping("/delete")
	public <T> ResponseEntity<T> deleteProduct(@RequestParam Long productId) {
		String message = productService.deleteProduct(productId);
		if (message.equals(Constants.productIdLtZero)) {
			return new ResponseEntity<T>((T) Constants.productIdLtZero, HttpStatus.BAD_REQUEST);
		} else if (message.equals(Constants.productNotExist)) {
			return new ResponseEntity<T>((T) message, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<T>((T) Constants.productDeleted, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/getProducts")
	<T> ResponseEntity<T> getProductsPage(@RequestParam int page, @RequestParam int size) {
		if (page < 0 || size < 0) {
			return new ResponseEntity<T>((T) Constants.invalidPageAndSize, HttpStatus.BAD_REQUEST);
		} else {
			PageRequest pageRequest = PageRequest.of(page - 1, size);
			List<Product> productList = productService.getProducts(pageRequest);
			List<ProductDTO> productDTOlist = new ArrayList<>();
			for(Product product: productList) {
				ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
				productDTOlist.add(productDTO);	
			}
	
			return new ResponseEntity<T>((T) productDTOlist, HttpStatus.OK);

		}
	}
}
