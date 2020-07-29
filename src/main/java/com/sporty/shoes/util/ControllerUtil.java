package com.sporty.shoes.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ControllerUtil {
	public static <T> List<String> errorHandling(BindingResult result) {
		List<String> errorMessages = new ArrayList<String>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(s -> {
				errorMessages.add(s.getDefaultMessage());
			});
			return errorMessages;
		}
		return errorMessages;
	}

}
