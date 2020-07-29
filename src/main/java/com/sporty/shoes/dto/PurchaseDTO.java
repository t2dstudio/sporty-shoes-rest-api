package com.sporty.shoes.dto;

import javax.validation.constraints.Email;

import com.sporty.shoes.ann.ValueOfEnumProductCategory;
import com.sporty.shoes.entity.ProductCategory;

import lombok.Data;

@Data
public class PurchaseDTO {
	
	private String email;
	private String username;
	private String productname;
	private String description;
	private Double cost;
	private String category;
	
}
