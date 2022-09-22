	package com.hnt.bookService.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Author {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@NotNull(message = "authorid should not be null")
		@Min(value = 1, message = "AuthorId should be more than 1")
		private Integer authorId;
		
		@NotBlank(message = "Author emailId never be blank")
		@Email
		private String authorMailId;
		
//		@OneToMany
//		List<Book> books;
}
