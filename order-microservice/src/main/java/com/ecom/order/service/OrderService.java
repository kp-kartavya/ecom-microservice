package com.ecom.order.service;

import com.ecom.order.dto.OrderDto;

public interface OrderService {

	OrderDto placeOrder(String userId);

}
