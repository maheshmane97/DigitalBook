package com.hnt.bookService.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.hnt.bookService.entity.Book;
import com.hnt.bookService.entity.BookCategory;
import com.hnt.bookService.service.BookService;

import lombok.Data;
import lombok.Setter;

@ExtendWith(MockitoExtension.class)
@Data
public class BookControllerTest {
	
	@Mock
	BookService bookService;
	
	@InjectMocks
	BookController bookController;
	
//	Book getBook(){
//		Book book=new Book();
//		book.setBookId(1);
//		book.setBookLogo("book.com");
//		book.setCategory(BookCategory.COMIC);
//		book.setContent("marvel");
//		book.setPrice(BigDecimal.valueOf(300.00));
//		book.setPublishedDate("2022-08-21");
//		book.setPublisher("New");
//		book.setReaderMailId("me@gmail.com");
//		book.setTitle("XXXX");
//		book.setAuthor("YYYY");
//		return book;
//	}
	
//	@Test
//	void testAddBooks() {
//		Book book=getBook();
//		when(bookService.addBook(book)).thenReturn(book);
//		ResponseEntity<Integer> saveBookId=bookController.addBook(book);
//		assertEquals(1, saveBookId.getStatusCodeValue());
//	}
	
	@Test
	void testReadbook() {
		List<Book> list=new ArrayList<>();
		BookCategory category=BookCategory.Comic;
		BigDecimal price=BigDecimal.valueOf(300.00);
		String publisher="XYZ";
		String author="ABCD";
		when(bookService.getBook(category, price, publisher, author)).thenReturn(list);
		ResponseEntity<List<Book>> book1=bookController.findBooks(category, price, publisher, author);
		assertEquals(list, book1.getBody());
	}
	
//	@Test
//	void testFindBook() {
//		Book book=getBook();
//		String readerMailId="mk@gmail.com";
//		Integer bookId=1;
//		when(bookService.readBook(readerMailId, bookId)).thenReturn(book);
//		Book book1=bookController.findBook(readerMailId, bookId);
//		assertEquals(book, book1 );
//	}
	
//	@Test
//	void testGetAllBooks() {
//		List<Book> books1=bookController.findAllBook();
//		assertThat(books1).size().isGreaterThan(1);
//		
//	}
	
}











