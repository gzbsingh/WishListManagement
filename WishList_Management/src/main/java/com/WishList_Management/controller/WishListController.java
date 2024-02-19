package com.WishList_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WishList_Management.models.WishListItem;
import com.WishList_Management.service.WishListItemService;

@RestController
@RequestMapping("/wishList")
public class WishListController {

	@Autowired
  private WishListItemService itemService;
	
	@PostMapping("/add")
	public ResponseEntity<WishListItem> addWishListItem(@RequestParam String username,@RequestBody WishListItem item){
		 
		return new ResponseEntity<>(itemService.CreateWishListItem(username, item),HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{id}")
public	ResponseEntity<String> deteteWishListItem(@PathVariable("id") long itemId){
	              itemService.deleteWishListItem(itemId);
		return new ResponseEntity<String>("delete succesFully", HttpStatus.OK);
		
	}
	@GetMapping("/itemList")
public	ResponseEntity<List<WishListItem>> getUserWishList(@RequestParam String username){
		return new ResponseEntity<List<WishListItem>>(itemService.getUserWishList(username), HttpStatus.OK);
	}

}
