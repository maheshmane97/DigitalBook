package com.hnt.bookService.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnt.bookService.entity.Author;
import com.hnt.bookService.entity.Book;
import com.hnt.bookService.entity.BookCategory;
import com.hnt.bookService.entity.Reader;
import com.hnt.bookService.repository.AuthorRepository;
import com.hnt.bookService.repository.BookRepository;
import com.hnt.bookService.repository.ReaderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	ReaderRepository readerRepository;
	// Get All Books
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// Get book according category, Price, Publisher
	public List<Book> getBook(String title, BigDecimal price, String publisher, String author) {
		List<Book> books = bookRepository.findAll().stream().filter(e -> e.getTitle().equalsIgnoreCase(title)
				|| e.getPrice() == price || e.getPublisher().equalsIgnoreCase(publisher) || e.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
		return books;
	}

	// Reader Can read Book
	public Book readBook(String readerMailId, Integer bookId) {
		Book book = null;
		List<Reader> listOfReader=readerRepository.findAll();
		Stream<Reader> reader=listOfReader.stream().filter(e->e.getReaderMailId().equalsIgnoreCase(readerMailId));
		if (reader != null) {
			book = bookRepository.findByBookId(bookId);
		}
		return book;
	}
	
	public Book addBook(Book book, Integer authorId) {
		Author author=authorRepository.findAuthorByAuthorId(authorId);
		Book book1=null;
		if(author!=null) {
			 book1= bookRepository.save(book);
		}
		return book1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
