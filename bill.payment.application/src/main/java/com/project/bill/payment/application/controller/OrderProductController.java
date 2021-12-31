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
import com.project.bill.payment.application.entity.OrderProduct;
import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;
import com.project.bill.payment.application.exception.OrderNotFoundException;
import com.project.bill.payment.application.exception.OrderProductNotFoundException;
import com.project.bill.payment.application.exception.ProductNotFoundException;
import com.project.bill.payment.application.impl.OrderProductServiceImpl;
import com.project.bill.payment.application.impl.OrderServiceImpl;
import com.project.bill.payment.application.impl.ProductServiceImpl;

@RestController
@RequestMapping("/app")
public class OrderProductController {
	
	@Autowired
	private OrderProductServiceImpl impl;
	
	@Autowired
	private ProductServiceImpl pimpl;
	
	@Autowired
	private OrderServiceImpl oimpl;
	
	
	
	@GetMapping("/carts")
	public List<OrderProduct> getAll(){
		return impl.getAllOrderProduct();
	}
	
	@GetMapping("/cart/{id}")
	public OrderProduct getOrderProduct(@PathVariable long id) {
		return impl.getOrderProduct(id);
	}
	
	@GetMapping("/order/{oid}/cart")
	public List<OrderProduct> getByStore(@PathVariable long oid){
		Order order = oimpl.getOrder(oid);
		if(order == null) {
			try {
				throw new OrderNotFoundException("Order not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return impl.findByOrder(order);
	}
	
	@PostMapping("/cart/createCart")
	public String addOrderProduct(@RequestBody OrderProduct orderProduct) {
		return impl.addOrderProduct(orderProduct);
	}
	
	@PostMapping("/cart/addToCart/{oid}/{id}/{pid}")
	public String addToCart(@PathVariable long id, @PathVariable long oid,@PathVariable long pid ,@RequestBody OrderProduct orderProduct) {
		Order order = oimpl.getOrder(oid);
		if(order == null) {
			try {
				throw new OrderNotFoundException("Order not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		orderProduct.setOrder(order);
		
		orderProduct.setId(id);
		
		Product product = pimpl.getProduct(pid);
		
		if(product == null) {
			try {
				throw new ProductNotFoundException("Product not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		orderProduct.setProduct(product);
		
		return impl.addOrderProduct(orderProduct);
	}
	
	@PatchMapping("/cart/editCart/{id}")
	public String editOrderProduct(@PathVariable long id, @RequestBody OrderProduct orderProduct) {
			OrderProduct op = impl.getOrderProduct(id);
			if(op == null) {
				try {
					throw new OrderProductNotFoundException("Not found!");
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			op.setQuantity(orderProduct.getQuantity());
		return impl.editOrderProduct(op);
	}
	
	@DeleteMapping("/cart/delete/{id}")
	public String deleteOrderProduct(@PathVariable long id) {
		return impl.deleteOrderProduct(id);
	}
}
