package com.project.bill.payment.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.entity.OrderProduct;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>{
	List<OrderProduct> findByOrder(Order order);
}
