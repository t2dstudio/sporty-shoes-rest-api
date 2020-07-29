package com.sporty.shoes.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sporty.shoes.entity.ProductCategory;
import com.sporty.shoes.entity.Product;
import com.sporty.shoes.entity.Purchase;
import com.sporty.shoes.entity.User;
import com.sporty.shoes.repositry.ProductRepository;
import com.sporty.shoes.repositry.PurchaseRepository;
import com.sporty.shoes.repositry.UserRepository;
import com.sporty.shoes.security.SecuredPasswordGenerator;


@Configuration
public class DBUtil {
	
	@Autowired
	UserRepository userRepositry;
	
	@Autowired
	ProductRepository productRepositry;
	
	@Autowired
	PurchaseRepository purchaseRepositry;
	
	@Bean
	public void onStartup() {
		
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword(SecuredPasswordGenerator.securedPassword("admin"));
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		user.setEnabled(true);
		user.setRole("ROLE_ADMIN");
		user.setEmail("admin@sporty-shoes.com");
		userRepositry.save(user);
		
		
		
		for(int i = 1; i < 50; i++) {
			user = new User();
			user.setUsername("user"+i);
			user.setPassword(SecuredPasswordGenerator.securedPassword("user"+i));
			user.setCreatedAt(new Date());
			user.setModifiedAt(new Date());
			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setEmail("user"+i+"@gmail.com");
			userRepositry.save(user);
	
			
			Product product = new Product();
			product.setCategory(ProductCategory.MISC.toString());
			product.setCost((double) (120+i));
			product.setCreatedAt(new Date());
			product.setDescription("Product Description" + i );
			product.setManufacturerName("Manufacturer Name: " + i);
			product.setModifiedAt(new Date());
			product.setProductname("Product Name "+ i);
			product.setModel("Model Number "+i);
			productRepositry.save(product);
		
			
			Purchase purchase = new Purchase();
			purchase.setUser(user);
			purchase.setProduct(product);
			purchase.setCreatedAt(new Date());
			purchase.setUpdatedAt(new Date());
			purchaseRepositry.save(purchase);
			
//			List<Purchase> userListOfPurchases = new ArrayList<>();
//			userListOfPurchases.add(purchase);
//			user.setPurchase(userListOfPurchases);
//			product.setPurchase(purchase);

		}		
	}
}
