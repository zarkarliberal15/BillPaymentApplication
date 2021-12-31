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
import com.project.bill.payment.application.exception.StoreNotFoundException;
import com.project.bill.payment.application.impl.ClientServiceImpl;
import com.project.bill.payment.application.impl.ProductServiceImpl;
import com.project.bill.payment.application.impl.StoreServiceImpl;

@RestController
@RequestMapping("/app")
public class StoreController {
	
	@Autowired
	private StoreServiceImpl impl;
	
	@Autowired
	private ProductServiceImpl productImpl;
	
	@Autowired 
	private ClientServiceImpl clientImpl;
	
	@GetMapping("/stores")
	public List<Store> getAll(){
		return impl.getAllStore();
	}
	
	@GetMapping("/store/{id}")
	public Store getStore(@PathVariable long id) {
		return impl.getStore(id);
	}
	
	@GetMapping("/store/phone/{phone}")
	public Store getStore(@PathVariable String phone) {
		return impl.getStore(phone);
	}
	
	
	@PostMapping("/store/addStore")
	public String addStore(@RequestBody Store store) {
		return impl.addStore(store);
	}
	
	@PostMapping("/store/login")
	public String login(@RequestBody Store store) {
		return impl.login(store.getPhone(), store.getPassword());
	}
	
	@PatchMapping("/store/editStore")
	public String editStore(@RequestBody Store store) {
		return impl.addStore(store);
	}
	
	@DeleteMapping("/store/delete/{id}")
	public String deleteStore(@PathVariable long id) {
		return impl.deleteStore(id);
	}
	
	@PostMapping("/store/{id}/addProduct")
	public String addProduct(@PathVariable long id,@RequestBody Product product) {
		Store store = impl.getStore(id);
		if(store != null) {
			product.setStore(store);
			return productImpl.addProduct(product);
		}else {
			return "Product not added!";
		}
		
	}
	
	@PatchMapping("/store/{id}/editProduct")
	public String editProduct(@PathVariable long id,@RequestBody Product product) {
		Store store = impl.getStore(id);
		if(store != null) {
			product.setStore(store);
			return productImpl.editProduct(product);
		}else {
			return "Product not updated!";
		}
		
	}
	
	@DeleteMapping("/store/{id}/deleteProduct/{pid}")
	public String deleteProduct(@PathVariable long id,@PathVariable long pid) {
		Store store = impl.getStore(id);
		if(store != null) {
			return productImpl.deleteProduct(pid);
		}else {
			return "Product not deleted!";
		}
		
	}

	@GetMapping("/store/{id}/products")
	public List<Product> getAllProducts(@PathVariable long id){
		Store store = impl.getStore(id);
		if(store != null) {
			return productImpl.findByStore(store);
		}else {
			try {
				throw new StoreNotFoundException("Store not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
}
