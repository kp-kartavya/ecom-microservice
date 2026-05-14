package com.ecom.product.service;

import java.util.List;

import com.ecom.product.dto.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);

	ProductDto updateProduct(String id, ProductDto productDto);

	List<ProductDto> getAllProducts();

	void deleteProduct(String id);

	List<ProductDto> searchProducts(String keyword);

}
