package com.istore.orderservice.service;

import com.istore.orderservice.client.CatalogServiceClient;
import com.istore.orderservice.client.UpdateRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.resolver.CatalogResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istore.orderservice.domain.Order;
import com.istore.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CatalogServiceClient catalogService;
	
	public Order submitOrder(Order order) {
		
		/**
		 * Distributed Transaction - OrderTxn:
		 * 1. get user info
		 * 2. create order
		 * 3. decrease inventory by 1
		 * 4. charge CC
		 */
		// 3. decrease inventory by quantity
		List<UpdateRequestModel> requestModels = order.getItems().stream()
				.map(	// map orderItem to Product UpdateRequestModel
						temp -> {
							UpdateRequestModel model = new UpdateRequestModel();
							model.setProductId(temp.getProductId());
							model.setQuantity(temp.getQuantity());
							return model;
						}
				)		// need to convert to a list so only make one REST call
				.collect(Collectors.toList());

		log.info("Before calling catalog Microservice");
		log.info("model : " + requestModels.toString());
		catalogService.updateInventory(requestModels);
		log.info("After calling catalog Microservice");

//		order.getItems().stream()
//				.map(	// map orderItem to Product UpdateRequestModel
//						temp -> {
//							UpdateRequestModel model = new UpdateRequestModel();
//							model.setProductId(temp.getProductId());
//							model.setQuantity(temp.getQuantity());
//							return model;
//						}
//				)
//				.forEach( // loop to update Inventory
//						model -> {
//							log.info("Before calling catalog Microservice");
//							log.info("model : " + model.toString());
//							catalogService.updateInventory(model);
//							log.info("After calling catalog Microservice");
//						}
//				);

		// 4. call CC to charge, return confirmation code
		order.getPayment().setPaymentConfirmationCode(UUID.randomUUID().toString());

		return orderRepository.save(order);
	}
}
