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

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;
import com.project.bill.payment.application.impl.ProductServiceImpl;
import com.project.bill.payment.application.impl.StoreServiceImpl;

@RestController
@RequestMapping("/app")
public class ProductController {
	@Autowired
	private ProductServiceImpl impl;
	
	@Autowired
	private StoreServiceImpl simpl;
	
	@GetMapping("/products")
	public List<Product> getAll(){
		return impl.getAllProduct();
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable long id) {
		return impl.getProduct(id);
	}
	
	@PostMapping("/product/addProduct")
	public String addProduct(@RequestBody Product product) {
		return impl.addProduct(product);
	}
	
	@PatchMapping("/product/editProduct")
	public String editProduct(@RequestBody Product product) {
		return impl.editProduct(product);
	}
	
	@DeleteMapping("/product/deleteProduct/{id}")
	public String editProduct(@PathVariable long id) {
		return impl.deleteProduct(id);
	}
	
	/*
	@GetMapping("/store/{sid}/products")
	public List<Product> getAllProductByStore(@PathVariable long sid){
		Store store = simpl.getStore(sid);
		return impl.findByStore(store);
	}
	
 	@GetMapping("/products/{name}")
 
	public List<Product> getAllProductByName(@PathVariable String name){
		return impl.findByName(name);
	}
	*/
}
