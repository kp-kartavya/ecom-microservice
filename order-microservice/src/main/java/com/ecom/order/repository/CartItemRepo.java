package com.ecom.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecom.order.entity.CartItem;

public interface CartItemRepo extends MongoRepository<CartItem, String>{
	CartItem findByUserIdAndProductId(String userId, String productId);
	void deleteByUserIdAndProductId(String userId, String productId);
	boolean existsByUserIdAndProductId(String userId, String productId);
	List<CartItem> findByUserId(String userId);
	void deleteByUserId(String userId);
}
