package com.WishList_Management.models;

import lombok.Data;

@Data
public class JwtResponse {

	private String jwtToken;
	private String username;

}
