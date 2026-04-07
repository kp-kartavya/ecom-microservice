package com.ecom.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "address")
@Document(collection = "address")
public class Address {
	@Id
	private String id;
	
	private String street;
	private String city;
	private String state;
	private String country;
	private Integer zipcode;
}
