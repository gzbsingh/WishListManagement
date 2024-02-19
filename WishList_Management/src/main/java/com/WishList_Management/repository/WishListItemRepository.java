package com.WishList_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WishList_Management.models.User;
import com.WishList_Management.models.WishListItem;


public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

	List<WishListItem> findByUserId(long userId);

	List<WishListItem> findByUser(User user);

}
