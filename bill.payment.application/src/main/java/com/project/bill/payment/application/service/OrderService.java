package com.project.bill.payment.application.service;

import java.util.List;

import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.entity.OrderProduct;
import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;

public interface OrderService {
	List<Order> getAllOrder();
	String addOrder(Order order);
	String deleteOrder(long id);
	String editOrder(Order order);
	Order getOrder(long id);
	double calculateTotalAmount(Order order);
	String payment(Order order);
	
}
