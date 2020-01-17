package com.istore.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="ISTORE_ORDER_PAYMENT", uniqueConstraints = {@UniqueConstraint(columnNames = "ID") })
public class OrderPaymentInfo implements Serializable {

	private static final long serialVersionUID = 7311167899272742781L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@OneToOne(mappedBy="payment")
	private Order order;

	@Column(name = "PAYMENT_CONFIRAMATION_CODE", unique = true, nullable = false, length = 100)
	private String paymentConfirmationCode;
	
	private String paymentMethod;
	
	private String ccNumber;
	
	private String expirationDate;
	
	private String securityCode;


}
