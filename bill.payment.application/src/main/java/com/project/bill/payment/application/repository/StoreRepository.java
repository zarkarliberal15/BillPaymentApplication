package com.project.bill.payment.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.entity.Store;

public interface StoreRepository extends CrudRepository<Store, Long>{
	Store findByPhone(String phone);
}
