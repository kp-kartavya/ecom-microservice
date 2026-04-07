package com.ecom.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Long id;
	private Integer quantity;
	private BigDecimal price;
	private String userId;
	private Long productId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
