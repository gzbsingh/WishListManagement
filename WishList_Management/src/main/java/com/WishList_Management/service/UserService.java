package com.WishList_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.WishList_Management.models.User;
import com.WishList_Management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public void signUp(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
