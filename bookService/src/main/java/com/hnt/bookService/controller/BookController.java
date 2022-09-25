package com.hnt.bookService.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hnt.bookService.entity.Book;
import com.hnt.bookService.entity.User;
import com.hnt.bookService.repository.AuthorRepository;
import com.hnt.bookService.repository.BookRepository;
import com.hnt.bookService.repository.UserRepository;
import com.hnt.bookService.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cogjava3185_MaheshMane Here is BookController class which is accept
 *         all the client request and send to Server
 *
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/digitalbooks")
public class BookController extends BaseController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BookService bookService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BookRepository bookRepository;

	// Get All Books
	@GetMapping
	public ResponseEntity<List<Book>> findAllBook() {
		log.debug("Inside findall book");
		List<Book> book = bookService.getAllBooks();
		ResponseEntity<List<Book>> response;
		if (book == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(book, HttpStatus.OK);
		}
		return response;
	}

	// Get book according category, Price, Publisher
	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> findBooks(@RequestParam String title, @RequestParam BigDecimal price,
			@RequestParam String publisher, @RequestParam String author) {
		ResponseEntity<List<Book>> response;
		log.debug("66 xyz");
		List<Book> book = bookService.getBook(title, price, publisher, author);
		response = new ResponseEntity<>(book, HttpStatus.OK);
		return response;
	}

	// Get book according to readermailID and bookId
	@GetMapping("/readers/{emailId}/books/{bookId}")
	public Book findBook(@PathVariable("emailId") String readerEmailId, @PathVariable Integer bookId) {
		return bookService.readBook(readerEmailId, bookId);
	}

	// Add Books
	@PostMapping("/author/{authorId}/books")
	public ResponseEntity<Integer> addBook(@Valid @RequestBody Book book, @PathVariable("authorId") Integer id) {
		ResponseEntity<Integer> response = null;
		Book books = bookRepository.findByBookId(book.getBookId());
		log.debug("92");
		if (books == null) {
			log.debug("95");
			Book book1 = bookService.addBook(book, id);
			if (book1 != null) {
				log.debug("98");
				Integer bookId = book1.getBookId();
				User user = userRepository.findById(id).get();
				response = new ResponseEntity<>(bookId, HttpStatus.CREATED);
				ResponseEntity<String> responseFromEmailService = restTemplate
						.postForEntity("http://localhost:8083/email", user.getEmailId(), String.class);
				log.debug(responseFromEmailService.getBody());
			} else {
				response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} else {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@DeleteMapping("/{bookId}")
	public Integer deleteBook(@PathVariable Integer bookId) {
		return bookService.deleteBook(bookId);
	}
}
