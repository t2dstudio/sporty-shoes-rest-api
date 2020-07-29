package com.sporty.shoes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.sporty.shoes.ann.ValueOfEnumProductCategory;
import com.sporty.shoes.entity.ProductCategory;

import lombok.Data;

@Data
public class ProductDTO {
	@NotBlank(message = "Product Name is mandatory")
	private String productname;
	@Size(min = 5, max = 250, message = "product description should be between 5 and 250 characters")
	private String description;
	private String manufacturerName;
	private String model;
	@Range(min = 0, message = "cost should be greater than or equal to zero")
	private Double cost;
	@ValueOfEnumProductCategory(enumClass = ProductCategory.class)
	private String category;

}
