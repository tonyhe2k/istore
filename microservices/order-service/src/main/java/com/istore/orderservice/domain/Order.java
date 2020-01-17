package com.istore.orderservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="ISTORE_ORDER", uniqueConstraints = {@UniqueConstraint(columnNames = "ID") })
public class Order implements Serializable {

	private static final long serialVersionUID = -7260907893121786603L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();
	
	private OrderStatus orderStatus = OrderStatus.CREATED;
	
	@Column(name = "ORDER_BY", unique = false, nullable = false, length = 100)
	private String orderBy;

	@Column(name = "ORDER_TOTAL", unique = false, nullable = false, length = 100)
	private BigDecimal orderTotal;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="ORDER_ID")
	private List<OrderItem> items;
	
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="PAYMENT_ID")
	private OrderPaymentInfo payment;
	
}
