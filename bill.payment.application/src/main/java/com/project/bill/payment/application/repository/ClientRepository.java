package com.project.bill.payment.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.bill.payment.application.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
	Client findByPhone(String phone);
}
