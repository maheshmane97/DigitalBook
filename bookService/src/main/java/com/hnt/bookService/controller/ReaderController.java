package com.hnt.bookService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnt.bookService.entity.Reader;
import com.hnt.bookService.service.ReaderService;

@RestController
@CrossOrigin
@RequestMapping("/reader")
public class ReaderController {
	@Autowired
	ReaderService readerService;
	
	@PostMapping
	public Integer addredaer(@Valid @RequestBody Reader reader) {
		readerService.addRedaer(reader);
		return reader.getId();
	}
}
