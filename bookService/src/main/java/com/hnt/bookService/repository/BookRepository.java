package com.hnt.bookService.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hnt.bookService.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
		Book findByBookId(Integer bookId);
		
}
