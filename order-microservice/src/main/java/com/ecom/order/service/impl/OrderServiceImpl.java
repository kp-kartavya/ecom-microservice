package com.ecom.order.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.dto.CartItemDto;
import com.ecom.order.dto.OrderDto;
import com.ecom.order.dto.OrderStatus;
import com.ecom.order.entity.Order;
import com.ecom.order.entity.OrderItem;
import com.ecom.order.repository.CartItemRepo;
import com.ecom.order.repository.OrderRepo;
import com.ecom.order.service.CartItemService;
import com.ecom.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CartItemService cartItemService;

	@Override
	public OrderDto placeOrder(String userId) {
		List<CartItemDto> cartItems = cartItemService.getCartItems(userId);

		if (cartItems.isEmpty()) {
			throw new RuntimeException("Cart is empty");
		}

//		User user = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

		BigDecimal totalPrice = cartItems.stream().map(CartItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

		Order order = new Order();
		order.setUserId(userId);
		order.setTotalAmount(totalPrice);
		order.setStatus(OrderStatus.CONFIRMED);

		List<OrderItem> orderItems = cartItems.stream()
				.map(item -> new OrderItem(null, item.getProductId(), item.getQuantity(), item.getPrice(), order))
				.collect(Collectors.toList());

		order.setOrderItems(orderItems);
		Order savedOrder = orderRepo.save(order);
		
		cartItemService.clearCart(userId);
		OrderDto orderDto = modelMapper.map(savedOrder, OrderDto.class);
		return orderDto;
	}

}
