package com.hnt.bookService.service;



import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hnt.bookService.entity.Book;
import com.hnt.bookService.entity.BookCategory;
import com.hnt.bookService.repository.BookRepository;

public class BookServiceTest {
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookService bookService;
	
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
//	void testAddBook() {
//		Book book=getBook();
//		when(bookRepository.save(book)).thenReturn(book);
//		bookService.addBook(book);
//		assertEquals(book, book.getBookId());
//	}
	
//	@Test
//	void testReadBook() {
//		Book book=getBook();
//		Integer bookId=1;
//		String readermailId="reader@gmail.com";
//		List<Book> list=new ArrayList<>();
//		list.add(book);
//		when(bookRepository.findAll()).thenReturn(list);
//		Book list1=bookService.readBook(readermailId, bookId);
//		assertEquals(list, list1);
//	}
	
//	@Test
//	void testGetBook() {
//		Book book=getBook();
//		List<Book> list=new ArrayList<>();
//		list.add(book);
//		String category="Comic";
//		Integer price=300;
//		String publisher="New";
//		when(bookRepository.findAll()).thenReturn(list);
//		List<Book> actual=bookService.getBook(category, price, publisher);
//		assertEquals(list, actual);
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
