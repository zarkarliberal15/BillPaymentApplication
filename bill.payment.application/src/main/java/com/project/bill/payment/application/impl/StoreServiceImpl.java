package com.project.bill.payment.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;
import com.project.bill.payment.application.exception.StoreNotFoundException;
import com.project.bill.payment.application.repository.ProductRepository;
import com.project.bill.payment.application.repository.StoreRepository;
import com.project.bill.payment.application.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository repo;
	
	@Override
	public List<Store> getAllStore() {
		return (List<Store>) repo.findAll();
	}

	@Override
	public String addStore(Store store) {
		Store s = repo.save(store);
		if(s != null) {
			return "Store added";
		}else {
			return "Store not added!";
		}
	}

	@Override
	public String deleteStore(long id) {
		repo.deleteById(id);
		return "store deleted!";
	}

	@Override
	public String editStore(Store store) {
		Store s = repo.save(store);
		if(s != null) {
			return "Store updated";
		}else {
			return "Store not updated!";
		}
	}

	@Override
	public Store getStore(long id) {
		Store store = repo.findById(id).get();
		if(store == null) {
			try {
				throw new StoreNotFoundException("Store not found!");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return store;
	}

	@Override
	public Store getStore(String phone) {
		Store store = repo.findByPhone(phone);
		if(store == null) {
			try {
				throw new StoreNotFoundException("Store not found!");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return store;
	}

	@Override
	public String login(String phone, String password) {
		Store store = repo.findByPhone(phone);
		if(store == null) {
			return "Store not found!";
		}else if(password.equals(store.getPassword())) {
			return "Login Successfully";
		}else {
			return "Invalid password!";
		}
	}


}
