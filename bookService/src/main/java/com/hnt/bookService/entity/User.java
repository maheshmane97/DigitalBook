package com.hnt.bookService.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(	name = "users", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "userName"),
	@UniqueConstraint(columnNames = "emailId") 
})

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Name cannot be blank###")
	@Size(max = 50)
	private String name;
	
	@Email
	@NotBlank(message = "emailId cannot be blank#######")
	@Size(max = 50)
	private String emailId;
	
	@NotBlank(message = "username cannot be blank#######")
	@Size(max = 20)
	private String userName;
	
	@NotBlank(message = "password cannot be blank#######")
	@Size(max = 120)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}

	public User(@NotBlank(message = "Name cannot be blank###") @Size(max = 50) String name,
			@NotBlank(message = "emailId cannot be blank#######") @Size(max = 50) String emailId,
			@NotBlank(message = "username cannot be blank#######") @Size(max = 20) String userName,
			@NotBlank(message = "password cannot be blank#######") @Size(max = 120) String password) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
	}


	
	
	

}
