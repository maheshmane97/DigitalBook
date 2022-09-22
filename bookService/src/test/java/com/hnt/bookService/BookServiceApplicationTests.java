package com.hnt.bookService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hnt.bookService.entity.Book;
import com.hnt.bookService.repository.BookRepository;

@SpringBootTest
class BookServiceApplicationTests {

	@Autowired
	BookRepository bookrepository;
	
	@Test
  public void testReadAll() {
		
		List<Book> list=bookrepository.findAll();
		assertThat(list).size().isGreaterThan(0);
		
	}
	

}
