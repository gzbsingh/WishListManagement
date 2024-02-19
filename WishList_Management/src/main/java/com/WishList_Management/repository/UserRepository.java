package com.WishList_Management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WishList_Management.models.User;
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);

}
