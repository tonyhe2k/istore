package com.istore.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.istore.orderservice.domain.Order;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Long> {

	
}
