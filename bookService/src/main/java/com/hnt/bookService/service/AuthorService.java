package com.hnt.bookService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnt.bookService.entity.Author;
import com.hnt.bookService.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	AuthorRepository authorRepository;
	
	public Author addAuthor(Author author) {
		return authorRepository.save(author);
	}
}
