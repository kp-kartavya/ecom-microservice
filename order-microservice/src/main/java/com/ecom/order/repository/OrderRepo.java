package com.ecom.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.order.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
