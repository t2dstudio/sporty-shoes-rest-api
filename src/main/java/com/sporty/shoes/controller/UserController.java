package com.sporty.shoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.shoes.service.iface.UserService;


@Controller
public class UserController {

	
	@Autowired
	UserService userService;
	
//	@GetMapping("/")
//	public String loginSuccessHandler() {
//		System.out.println("reached login success handler");
//		return "index";
//	}
	
//	@GetMapping("/login")
//	public String login() {
//		System.out.println("reached login  handler");
//		return "login";
//	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String password, Model model) {
//		System.out.println("reached changed password controller");
//		System.out.println("Password is " + password);
		model.addAttribute("message",userService.changePassword(password));
		return "index";
	}

	
}
