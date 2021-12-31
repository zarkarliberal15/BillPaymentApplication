package com.project.bill.payment.application.service;

import java.util.List;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;

public interface ProductService {
	List<Product> getAllProduct();
	String addProduct(Product product);
	String deleteProduct(long id);
	String editProduct(Product product);
	Product getProduct(long id);
	List<Product> findByStore(Store store);
	List<Product> findByName(String name);
}
