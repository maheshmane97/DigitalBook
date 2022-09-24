package com.hnt.bookService.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnt.bookService.entity.Author;
import com.hnt.bookService.entity.Book;
import com.hnt.bookService.entity.BookCategory;
import com.hnt.bookService.entity.Reader;
import com.hnt.bookService.entity.User;
import com.hnt.bookService.repository.AuthorRepository;
import com.hnt.bookService.repository.BookRepository;
import com.hnt.bookService.repository.ReaderRepository;
import com.hnt.bookService.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cogjava3185_MaheshMane All Business logic are here to complete all
 *         request of client. Request comes from controller class are handle
 *         here and passes to the server and return the expected output through
 *         service class to controller class
 *
 */
@Slf4j
@Service
public class BookService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	// Get All Books
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// Get book according category, Price, Publisher
	public List<Book> getBook(String title, BigDecimal price, String publisher, String author) {
		List<Book> books = bookRepository.findAll().stream()
				.filter(e -> e.getTitle().equalsIgnoreCase(title) || e.getPrice() == price
						|| e.getPublisher().equalsIgnoreCase(publisher) || e.getAuthor().equalsIgnoreCase(author))
				.collect(Collectors.toList());
		return books;
	}

	// Reader Can read Book
	public Book readBook(String readerMailId, Integer bookId) {
		Book book = null;
		if (userRepository.existsByEmailId(readerMailId)) {
			log.debug("Inside readbook bookservice");
			book = bookRepository.findByBookId(bookId);
		}
		return book;
	}

	public Book addBook(Book book, Integer authorId) {
		User author = userRepository.findById(authorId).get();
		Book book1 = null;
		if (author != null) {
			book1 = bookRepository.save(book);
		}
		return book1;
	}

	public Integer deleteBook(Integer bookId) {
		Book book=bookRepository.findByBookId(bookId);
		if(bookId==book.getBookId()) {
			 bookRepository.delete(book);
		}
		return book.getBookId();
	}

}
