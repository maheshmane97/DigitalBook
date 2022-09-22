package com.hnt.bookService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnt.bookService.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
	
}
