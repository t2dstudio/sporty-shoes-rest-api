package com.sporty.shoes.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sporty.shoes.entity.Purchase;
import com.sporty.shoes.util.ReturningValues;

public interface PurchaseService {

	List<Purchase> getPurchases(Pageable pageable);

	ReturningValues getPurchaseReportByCreatedDate(String createdAt, Pageable pageable);

	List<Purchase> getPurchaseReportByCategory(String category, Pageable pageable);

}
