package com.project.bill.payment.application.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_product")
public class OrderProduct {
	@Id
	private long id;
	private int quantity;
	private double d_price;
	private double t_price;
	private double amount;
	
	@OneToOne
	@JoinColumn(name="oid")
	private Order order;
	
	@OneToOne
	@JoinColumn(name="pid")
	private Product product;

	public OrderProduct() {
	}

	public OrderProduct(long id, int quantity, Order order, Product product) {
		this.id = id;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getD_price() {
		return d_price;
	}

	public void setD_price(double d_price) {
		this.d_price = d_price;
	}

	public double getT_price() {
		return t_price;
	}

	public void setT_price(double t_price) {
		this.t_price = t_price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return String.format("%-5s %-15s %-15.2f %-15s %-15.2f %-15.2f %-15.2f %-15s",id,product.getName(), product.getPrice(), quantity,d_price,t_price,amount,order.getClient().getName());
	}
}
