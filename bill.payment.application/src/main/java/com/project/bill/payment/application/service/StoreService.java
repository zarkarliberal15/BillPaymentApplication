package com.project.bill.payment.application.service;

import java.util.List;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;

public interface StoreService {
	List<Store> getAllStore();
	String addStore(Store store);
	String deleteStore(long id);
	String editStore(Store store);
	Store getStore(long id);
	Store getStore(String phone);
	String login(String phone, String password);
}
