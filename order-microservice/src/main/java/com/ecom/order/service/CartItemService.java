package com.ecom.order.service;

import java.util.List;

import com.ecom.order.dto.CartItemDto;
import com.ecom.order.request.CartItemRequest;

public interface CartItemService {

	CartItemDto addToCart(String userId, CartItemRequest cartItemRequest);

	boolean deleteCartItem(String userId, Long productId);

	List<CartItemDto> getCartItems(String userId);

	void clearCart(String userId);

}
