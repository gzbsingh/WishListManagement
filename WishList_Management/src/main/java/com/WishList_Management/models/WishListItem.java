package com.WishList_Management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
public class WishListItem {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 long id;
     
     String itemname;
     
     double price;
     
     @ManyToOne
     @JoinColumn(name = "user_id",nullable = false)
     @JsonIgnore
     User user;
}
