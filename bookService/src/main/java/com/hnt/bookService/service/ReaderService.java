package com.hnt.bookService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnt.bookService.entity.Reader;
import com.hnt.bookService.repository.ReaderRepository;

@Service
public class ReaderService {
	
	@Autowired
	ReaderRepository readerRepository;
	
	public void addRedaer(Reader reader) {
		readerRepository.save(reader);
	}
}
