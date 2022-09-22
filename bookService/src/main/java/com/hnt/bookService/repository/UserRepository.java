package com.hnt.bookService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnt.bookService.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);

	Boolean existsByEmailId(String emailId);
	

}
