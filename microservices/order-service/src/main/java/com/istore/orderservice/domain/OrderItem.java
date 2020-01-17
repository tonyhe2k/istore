package com.istore.orderservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ISTORE_ORDER_ITEM")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 9116101058400245773L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Order order;
	
	private Long productId;
	
	private BigDecimal price;
	
	private int quantity;
	
}
