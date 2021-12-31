package com.project.bill.payment.application.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bill.payment.application.entity.Client;
import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.entity.OrderProduct;
import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;
import com.project.bill.payment.application.exception.OrderNotFoundException;
import com.project.bill.payment.application.repository.ClientRepository;
import com.project.bill.payment.application.repository.OrderProductRepository;
import com.project.bill.payment.application.repository.OrderRepository;
import com.project.bill.payment.application.repository.ProductRepository;
import com.project.bill.payment.application.repository.StoreRepository;
import com.project.bill.payment.application.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private OrderProductRepository oprepo;
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private ClientRepository crepo;
	
	@Autowired
	private StoreRepository srepo;
	
	
	@Override
	public List<Order> getAllOrder() {
		return (List<Order>) repo.findAll();
	}

	@Override
	public String addOrder(Order order) {
		double totalAmount = calculateTotalAmount(order);
		order.setTotal(totalAmount);
		Order o = repo.save(order);
		if(o != null) {
			return "Order added";
		}else {
		return "Order not added!";
		}
	}

	@Override
	public String deleteOrder(long id) {
		repo.deleteById(id);
		return "Order deleted!";
	}

	@Override
	public String editOrder(Order order) {
		double totalAmount = calculateTotalAmount(order);
		order.setTotal(totalAmount);
		Order o = repo.save(order);
		if(o != null) {
			return "Order updated";
		}else {
		return "Order not updated!";
		}
	}

	@Override
	public Order getOrder(long id) {
		Order order = repo.findById(id).get();
		if(order == null) {
			try {
				throw new OrderNotFoundException("Order not found!");
			}catch(Exception e ) {
				System.out.println(e.getMessage());
			}
		}
		return order;
	}

	@Override
	public double calculateTotalAmount(Order order) {
		List<OrderProduct> oplist = oprepo.findByOrder(order);
		double totalAmount = 0;
		for(OrderProduct op: oplist) {
			totalAmount += op.getAmount();
		}
		return totalAmount;
	}

	@Override
	public String payment(Order order) {
		String result = "";
		Store store = null;
		if(order.getClient().getBalance() >= order.getTotal()) {
			
			
			List<OrderProduct> ops = oprepo.findByOrder(order);
			store = ops.get(0).getProduct().getStore();
			for(OrderProduct op:ops) {
				Product product = prepo.findById(op.getProduct().getId()).get();
				int currentQuantity = product.getQuantity();
				int sellQuantity =  op.getQuantity();
				if(currentQuantity >= sellQuantity) {
					product.setQuantity(currentQuantity-sellQuantity);
					prepo.save(product);	
				}else {
					return "Something went wrong!";
				}
				
			}
			result = "Success";
			order.setPayment_status(result);
			
			double tempAmount = order.getTotal();
			Client client = order.getClient();
			double clientBalance = client.getBalance();
			client.setBalance(clientBalance - tempAmount);
			
			double storeBalance = store.getBalance();
			store.setBalance(storeBalance + tempAmount);
			
			crepo.save(client);
			srepo.save(store);
			
		}else {
			result = "Failed: Transaction Error!";
			order.setPayment_status(result);
		}
		
		
		order.setDateTime(new Date());
		order.setTransaction_id(""+new Date().getTime());
		repo.save(order);
		return result;
	}
	
	

}
