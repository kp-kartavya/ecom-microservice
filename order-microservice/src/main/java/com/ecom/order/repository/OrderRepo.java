package com.ecom.order.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecom.order.entity.Order;

public interface OrderRepo extends MongoRepository<Order, String>{

}
