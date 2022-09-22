package com.hnt.bookService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnt.bookService.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	Author findAuthorByAuthorId(Integer authorId);

}
