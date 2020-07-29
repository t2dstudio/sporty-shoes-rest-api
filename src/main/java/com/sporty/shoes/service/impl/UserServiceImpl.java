package com.sporty.shoes.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sporty.shoes.entity.User;
import com.sporty.shoes.repositry.UserRepository;
import com.sporty.shoes.security.MyUserDetails;
import com.sporty.shoes.security.SecuredPasswordGenerator;
import com.sporty.shoes.service.iface.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepositry;

	@Override
	public String changePassword(String password) {
		MyUserDetails securedUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = userRepositry.getUserByUsername(securedUser.getUsername());
		user.setPassword(SecuredPasswordGenerator.securedPassword(password));
		user.setModifiedAt(new Date());
		if (userRepositry.save(user) != null) {
			return user.getUsername() + " password changed successfully";
		} else {
			return user.getUsername() + " password not changed successfully";
		}

	}

	@Override
	public List<User> getUsers(Pageable pageable) {
		return userRepositry.findAll(pageable).getContent();
	}

	@Override
	public User findUserByName(String name) {
		return userRepositry.getUserByUsername(name);
	}
}
