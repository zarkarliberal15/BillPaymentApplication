package com.project.bill.payment.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;
import com.project.bill.payment.application.exception.ProductNotFoundException;
import com.project.bill.payment.application.repository.ProductRepository;
import com.project.bill.payment.application.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repo;
	
	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) repo.findAll();
	}

	@Override
	public String addProduct(Product product) {
		Product p = repo.save(product);
		if(p != null) {
			return "Product added";
		}else {
			return "Product not added!";	
		}
		
	}

	@Override
	public String deleteProduct(long id) {
		repo.deleteById(id);
		return "Product deleted!";
	}

	@Override
	public String editProduct(Product product) {
		Product p = repo.save(product);
		if(p != null) {
			return "Product updated";
		}else {
			return "Product not updated!";	
		}
	}

	@Override
	public Product getProduct(long id) {
		Product product = repo.findById(id).get();
		if(product == null) {
			try {
				throw new ProductNotFoundException("Product not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return product;
	}

	@Override
	public List<Product> findByStore(Store store) {
		return repo.findByStore(store);
	}

	@Override
	public List<Product> findByName(String name) {
		return repo.findByName(name);
	}

}