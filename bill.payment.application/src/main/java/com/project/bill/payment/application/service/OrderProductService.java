package com.project.bill.payment.application.service;

import java.util.List;

import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.entity.OrderProduct;
import com.project.bill.payment.application.entity.Product;

public interface OrderProductService {
	List<OrderProduct> getAllOrderProduct();
	String addOrderProduct(OrderProduct orderProduct);
	String deleteOrderProduct(long id);
	String editOrderProduct(OrderProduct orderProduct);
	OrderProduct getOrderProduct(long id);
	double calculateAmount(double price, int quantity, double taxAmount, double discountAmount);
	double calculateTaxAmount(double price, int quantity, double taxAmount);
	double calculateDiscountAmount(double price, int quantity, double discountAmount);
	List<OrderProduct> findByOrder(Order order);
	boolean isProductAvailable(long id,int quantity);
}
