package com.ecom.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.order.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long>{
	CartItem findByUserIdAndProductId(String userId, Long productId);
	void deleteByUserIdAndProductId(String userId, Long productId);
	boolean existsByUserIdAndProductId(String userId, Long productId);
	List<CartItem> findByUserId(String userId);
	void deleteByUserId(String userId);
}
