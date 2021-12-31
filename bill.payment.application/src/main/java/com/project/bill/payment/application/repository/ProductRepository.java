package com.project.bill.payment.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;

public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findByStore(Store store);
	List<Product> findByName(String name);
}
