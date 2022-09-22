package com.hnt.bookService.entity;


import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Data
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Min(value = 1, message = "Book ID should be in number and greater than 1")
	private Integer bookId;
	
	@NotBlank(message = "Book Logo url not should be Blank###")
	private String bookLogo;
	
	@NotNull(message = "Category should not be blank###")
	@Enumerated(EnumType.STRING)
	private BookCategory category;
	
	@NotBlank(message = "Book Title not should be Blank###")
	private String title;
	
	@NotBlank(message = "author Name never should be blank")
	private String author;
	
	@NotBlank(message = "Book Publisher Name not should be Blank###")
	private String publisher;
	
	@NotNull(message = "Published Date should not be blank###")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	@JsonFormat(pattern = "dd-mm-yyyy", shape = Shape.STRING)
	private String publishedDate;
	
	@Min(value = 1, message = "Price should be more than One Rupees###")
	@DecimalMin(value = "1.0", message = "Price should not be less than 1")
	@Digits(integer = 4, fraction = 2)
	private BigDecimal price;
	
	@NotBlank(message = "Book Content not should be Blank###")
	private String content;

	@NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
	private String active;
	
	
//	@ManyToOne(targetEntity = Author.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "authorId")
//	private Author author;
	
//	@ManyToOne
//	Reader reader;
}
