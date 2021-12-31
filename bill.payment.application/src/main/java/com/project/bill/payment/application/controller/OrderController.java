package com.project.bill.payment.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.exception.OrderNotFoundException;
import com.project.bill.payment.application.impl.OrderServiceImpl;

@RestController
@RequestMapping("/app")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl impl;
	
	@GetMapping("/orders")
	public List<Order> getAll(){
		return impl.getAllOrder();
	}
	
	@GetMapping("/order/{id}")
	public Order getOrder(@PathVariable long id) {
		return impl.getOrder(id);
	}
	
	@PostMapping("/order/addOrder")
	public String addOrder(@RequestBody Order order) {
		return impl.addOrder(order);
	}
	
	@PatchMapping("/order/editOrder")
	public String editOrder(@RequestBody Order order) {
		return impl.editOrder(order);
	}
	
	@DeleteMapping("/order/deleteOrder/{id}")
	public String deleteOrder(@PathVariable long id) {
		return impl.deleteOrder(id);
	}
	
	@PostMapping("/order/{id}/pay/wallet/{code}")
	public String payment(@PathVariable long id, @PathVariable String code) {
		String status = "";
		Order order = impl.getOrder(id);
		if(order == null) {
			try {
				throw new OrderNotFoundException("Order not found!");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		if((order.getClient().getPassword()).equals(code)) {
			 order.setPayment_mode("Wallet");
			 status = impl.payment(order);
		}else {
			status =  "Invalid Code!";
		}
		return status;
	}
}
