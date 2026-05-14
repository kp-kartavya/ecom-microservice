package com.ecom.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ecom.product.entity.Product;

public interface ProductRepo extends MongoRepository<Product, String> {
	List<Product> findByActiveTrue();

	@Query("{ 'active': true, 'stockQuantity': { $gt: 0 }, 'name': { $regex: ?0, $options: 'i' } }")
	List<Product> searchProducts(String keyword);
}
