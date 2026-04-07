package com.ecom.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.dto.CartItemDto;
import com.ecom.order.entity.CartItem;
import com.ecom.order.repository.CartItemRepo;
import com.ecom.order.request.CartItemRequest;
import com.ecom.order.service.CartItemService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CartItemDto addToCart(String userId, CartItemRequest cartItemRequest) {
//		Product product = productRepo.findById(cartItemRequest.getProductId()).orElseThrow(
//				() -> new ResourceNotFoundException("Product", "id", cartItemRequest.getProductId().toString()));

//		if (product.getStockQuantity() < cartItemRequest.getQuantity()) {
//			throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
//		}

//		User user = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

		CartItem existingCartItem = cartItemRepo.findByUserIdAndProductId(userId, cartItemRequest.getProductId());

		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
//			existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			existingCartItem.setPrice(BigDecimal.valueOf(19999.00));
			cartItemRepo.save(existingCartItem);
			return modelMapper.map(existingCartItem, CartItemDto.class);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setUserId(userId);
			cartItem.setProductId(cartItemRequest.getProductId());
			cartItem.setQuantity(cartItemRequest.getQuantity());
			cartItem.setPrice(BigDecimal.valueOf(19999.00));
			CartItem savedCartItem = cartItemRepo.save(cartItem);
			return modelMapper.map(savedCartItem, CartItemDto.class);
		}

	}

	@Override
	public boolean deleteCartItem(String userId, Long productId) {
//		User user = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
//
//		Product product = productRepo.findById(productId)
//				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId.toString()));

		if (!cartItemRepo.existsByUserIdAndProductId(userId, productId)) {
			return false;
		}
		cartItemRepo.deleteByUserIdAndProductId(userId, productId);
		return true;
	}

	@Override
	public List<CartItemDto> getCartItems(String userId) {
		return cartItemRepo.findByUserId(userId).stream().map(cartItem -> modelMapper.map(cartItem, CartItemDto.class))
				.toList();
	}

	@Override
	public void clearCart(String userId) {
		cartItemRepo.deleteByUserId(userId);
	}
}
