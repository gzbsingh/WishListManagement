package com.WishList_Management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.WishList_Management.exception.AlreadyUserExistException;
import com.WishList_Management.models.User;
import com.WishList_Management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public void signUp(User user) throws AlreadyUserExistException {
		Optional<User> existUser=userRepository.findByUsername(user.getUsername());
		if(existUser.isPresent()) {
			throw new AlreadyUserExistException(user.getUsername()+"is alreay exist please login"); 
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
