package com.hnt.bookService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnt.bookService.entity.ERole;
import com.hnt.bookService.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(ERole name);
	
}
