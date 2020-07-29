package com.sporty.shoes.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sporty.shoes.entity.Purchase;
import com.sporty.shoes.repositry.PurchaseRepository;
import com.sporty.shoes.repositry.PurchaseRepository;
import com.sporty.shoes.service.iface.PurchaseService;
import com.sporty.shoes.util.Constants;
import com.sporty.shoes.util.ReturningValues;
import com.sporty.shoes.util.ServiceUtil;

@SuppressWarnings("unused")
@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepositry;

	@Override
	public List<Purchase> getPurchases(Pageable pageable) {
		return purchaseRepositry.findAllByPage(pageable).getContent();
	}

	@Override
	public ReturningValues getPurchaseReportByCreatedDate(String createdAt, Pageable pageable) {
		ReturningValues rv = new ReturningValues();
		if (!GenericValidator.isDate(createdAt, "yyyy-MM-dd", true)) {
			rv.setMessage(Constants.invalidDateFormat);
		} else {
			String createdAtStartStr = createdAt + " 00:00:00";
			String createdAtEndStr = createdAt + " 23:59:59";

			System.out.println("createdAt: " + createdAt);
			Date createdAtStart = ServiceUtil.convertToDate(createdAtStartStr);
			Date createdAtEnd = ServiceUtil.convertToDate(createdAtEndStr);

			if (createdAtStart == null || createdAtEnd == null) {
				System.out.println("Dates are null");
			} else {
				System.out.println("createdAtStart and createdAtEnd: " + createdAtStart.toString() + " : "
						+ createdAtEnd.toString());
			}

			rv.setList(
					(purchaseRepositry.findAllByCreatedAtBetween(createdAtStart, createdAtEnd, pageable)).getContent());
		}
		return rv;
	}

	@Override
	public List<Purchase> getPurchaseReportByCategory(String category, Pageable pageable) {
		return purchaseRepositry.getPurchaseReportByCategory(category, pageable).getContent();
	}
}
