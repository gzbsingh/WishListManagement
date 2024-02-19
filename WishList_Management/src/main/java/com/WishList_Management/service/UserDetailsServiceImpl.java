package com.WishList_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.WishList_Management.exception.UserNotFoundExcepion;
import com.WishList_Management.models.User;
import com.WishList_Management.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	//load user from database
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user= userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundExcepion("user not found of username "+username));
	
		return new CustomeUserDeatils(user);
		
	}

	

}
