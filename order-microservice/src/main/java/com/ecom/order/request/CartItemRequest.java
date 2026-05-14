package com.ecom.order.request;

import lombok.Data;

@Data
public class CartItemRequest {
	private String productId;
	private Integer quantity;
}
