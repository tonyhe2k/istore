package com.istore.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.istore.orderservice.domain.Order;
import com.istore.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/status")
	public String status() {
		log.info("UP");
		return "UP";
	}

	@PostMapping("")
	public ResponseEntity<Order> submitOrder(@RequestBody Order order) {
		log.info(order.toString());
		return ResponseEntity.status(HttpStatus.OK).body(orderService.submitOrder(order));
	}
}
