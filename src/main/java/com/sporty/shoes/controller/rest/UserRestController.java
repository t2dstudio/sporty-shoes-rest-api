package com.sporty.shoes.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.dto.UserDTO;
import com.sporty.shoes.entity.Product;
import com.sporty.shoes.entity.User;
import com.sporty.shoes.service.iface.UserService;
import com.sporty.shoes.util.Constants;

@RestController
@RequestMapping("/users")
@SuppressWarnings(value = "unchecked")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/list")
	<T> ResponseEntity<T> getUsersPage(@RequestParam int page, @RequestParam int size) {
		if (page < 0 || size < 0) {
			return new ResponseEntity<T>((T) Constants.invalidPageAndSize, HttpStatus.BAD_REQUEST);
		} else {
			Pageable pageRequest = PageRequest.of(page - 1, size);
			List<User> userList = userService.getUsers(pageRequest);
			List<UserDTO> userDTOlist = new ArrayList<>();
			for(User user: userList) {
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				userDTOlist.add(userDTO);	
			}
//			System.out.println("User: " + userList.get(0));
			return new ResponseEntity<T>((T) userDTOlist, HttpStatus.OK);

		}
	}

	@PostMapping(path = "/findUserByName")
	public <T> ResponseEntity<T> findUserByName(String name) {

		User user = userService.findUserByName(name);
		if (user == null) {
			return new ResponseEntity<T>((T) Constants.userNotFound, HttpStatus.NOT_FOUND);
		} else {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return new ResponseEntity<T>((T) userDTO, HttpStatus.OK);
		}
	}

}
