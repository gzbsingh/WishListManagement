package com.WishList_Management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
     @Column(unique = true)
	String username;
    
	 String password;
	 
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	 @JsonIgnore
	private List<WishListItem> items;
}
