package com.WishList_Management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WishList_Management.exception.UserNotFoundExcepion;
import com.WishList_Management.exception.WishListItemNotFoundException;
import com.WishList_Management.models.User;
import com.WishList_Management.models.WishListItem;
import com.WishList_Management.repository.UserRepository;
import com.WishList_Management.repository.WishListItemRepository;

@Service
public class WishListItemService {

	@Autowired
	private WishListItemRepository wishListItemRepository;

	@Autowired
  private UserRepository userRepository;

	//creating Wish List Item
	public  WishListItem CreateWishListItem(String username,WishListItem item) {
		User user= userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundExcepion("user not found of username "+username));
		item.setUser(user);
		return wishListItemRepository.save(item);
	 }
	
	//delete wishLitst  method 
	public void deleteWishListItem(long itemId) {
		WishListItem item=wishListItemRepository.findById(itemId).
				orElseThrow(()->new WishListItemNotFoundException("WishList item not found with id"+itemId));
		wishListItemRepository.delete(item);
	}
	 
	public List<WishListItem> getUserWishList(String username){
	User user= userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundExcepion("user not found of username "+username));
	  // Get the wish list items from the user object
	List<WishListItem> userWishList = wishListItemRepository.findByUser(user);
	if (userWishList == null) {
	        throw new WishListItemNotFoundException("Wish list items not found for user with username: " + username);
	    }
	return user.getItems();
	                
		 
	}
	
	 
}
