package com.project.bill.payment.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.bill.payment.application.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
