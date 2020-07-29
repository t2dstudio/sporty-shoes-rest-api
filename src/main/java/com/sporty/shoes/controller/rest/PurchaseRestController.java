package com.sporty.shoes.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.dto.PurchaseDTO;
import com.sporty.shoes.dto.UserDTO;
import com.sporty.shoes.entity.Product;
import com.sporty.shoes.entity.Purchase;
import com.sporty.shoes.entity.User;
import com.sporty.shoes.service.iface.PurchaseService;
import com.sporty.shoes.util.Constants;
import com.sporty.shoes.util.ReturningValues;

@RestController
@RequestMapping("/purchase")
@SuppressWarnings("unchecked")
public class PurchaseRestController {

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/getPurchases")
	<T> ResponseEntity<T> getPurchasesPage(@RequestParam int page, @RequestParam int size) {
		if (page < 0 || size < 0) {
			return new ResponseEntity<T>((T) Constants.invalidPageAndSize, HttpStatus.BAD_REQUEST);
		} else {
			PageRequest pageRequest = PageRequest.of(page - 1, size);
			List<Purchase> purchaseList = purchaseService.getPurchases(pageRequest);
			List<PurchaseDTO> purchaseDTOlist = convertToPurchaseDTOList(purchaseList);
			return new ResponseEntity<T>((T) purchaseDTOlist, HttpStatus.OK);

		}
	}

	public List<PurchaseDTO> convertToPurchaseDTOList(List<Purchase> purchaseList) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<PurchaseDTO> purchaseDTOlist = new ArrayList<>();
		for (Purchase purchase : purchaseList) {
			PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);
			purchaseDTOlist.add(purchaseDTO);
		}
		return purchaseDTOlist;
	}

	@GetMapping(path = "/getPurchaseReportByCreatedDate")
	<T> ResponseEntity<T> getPurchaseReportByCreatedDate(@RequestParam String createdAt, @RequestParam int page,
			@RequestParam int size) {
		if (page < 0 || size < 0) {
			return new ResponseEntity<T>((T) Constants.invalidPageAndSize, HttpStatus.BAD_REQUEST);
		} else {
			PageRequest pageRequest = PageRequest.of(page - 1, size);
			ReturningValues rv = purchaseService.getPurchaseReportByCreatedDate(createdAt, pageRequest);
			if (rv.message == null) {
				List<PurchaseDTO> purchaseDTOlist = convertToPurchaseDTOList((List<Purchase>) rv.getList());
				return new ResponseEntity<T>((T) ((List<PurchaseDTO>) purchaseDTOlist), HttpStatus.OK);
			} else {
				return new ResponseEntity<T>((T) rv.message, HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping(path = "/getPurchaseReportByCategory")
	<T> ResponseEntity<T> getPurchaseReportByCategory(@RequestParam String category, @RequestParam int page,
			@RequestParam int size) {
		if (page < 0 || size < 0) {
			return new ResponseEntity<T>((T) Constants.invalidPageAndSize, HttpStatus.BAD_REQUEST);
		} else {
			Pageable pageable = PageRequest.of(page - 1, size);
			List<Purchase> purchaseList = purchaseService.getPurchaseReportByCategory(category, pageable);
			List<PurchaseDTO> purchaseDTOlist = convertToPurchaseDTOList(purchaseList);
			if (purchaseDTOlist.isEmpty()) {
				return new ResponseEntity<T>((T) Constants.incorrectCategory, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<T>((T) purchaseDTOlist, HttpStatus.OK);
			}
		}
	}
}
