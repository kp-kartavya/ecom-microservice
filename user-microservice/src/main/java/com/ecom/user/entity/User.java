package com.ecom.user.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.user.dto.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/* @Entity
@Table(name = "users") */
@Document(collection = "users")
public class User {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
//	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
//	@Enumerated(EnumType.STRING) // to store the enum value as a string in the database
//	@Enumerated(EnumType.ORDINAL) // to store the enum value as an integer in the database
	private UserRole role = UserRole.CUSTOMER;
	
//	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
//	@CreationTimestamp
	@CreatedDate
	private LocalDateTime createdAt;
//	@UpdateTimestamp
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
