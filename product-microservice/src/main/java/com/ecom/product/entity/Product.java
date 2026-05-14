package com.ecom.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
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
@Document(collection = "products")
public class Product {
	@Id
	private String id;

	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private String category;
	private String imageUrl;
	private Boolean active = true;

	@CreatedDate
	private LocalDateTime createdAt;
}
