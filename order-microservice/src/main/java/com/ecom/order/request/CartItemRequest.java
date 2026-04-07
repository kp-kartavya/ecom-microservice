package com.ecom.order.request;

import lombok.Data;

@Data
public class CartItemRequest {
	private Long productId;
	private Integer quantity;
}
