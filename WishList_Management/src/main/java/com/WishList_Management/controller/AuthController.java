package com.WishList_Management.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WishList_Management.config.JwtHelper;
import com.WishList_Management.models.JwtResponse;
import com.WishList_Management.models.LoginRequest;
import com.WishList_Management.models.User;
import com.WishList_Management.service.UserService;

@RestController
@RequestMapping("/user")
public class AuthController {

	
	@Autowired
	private UserService userService;

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
     public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest jwtRequest){
	
    	this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
    	UserDetails userdetails=userDetailsService.loadUserByUsername(jwtRequest.getUsername());
    	
    	String token=this.helper.generateToken(userdetails);
    	JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		response.setUsername(userdetails.getUsername());
		
    	
    	return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
    	
    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
	@PostMapping("/signup")
	ResponseEntity<String> addUser(@RequestBody User user){
		userService.signUp(user);
		return new ResponseEntity<String>("SuccesFully Added",HttpStatus.CREATED);
	}
	
}
