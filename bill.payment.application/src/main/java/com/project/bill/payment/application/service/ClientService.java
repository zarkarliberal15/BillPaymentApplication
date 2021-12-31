package com.project.bill.payment.application.service;

import java.util.List;

import com.project.bill.payment.application.entity.Client;

public interface ClientService {
	List<Client> getAllClient();
	String addClient(Client client);
	String deleteClient(long id);
	String editClient(Client client);
	Client getClient(long id);
	Client getClient(String phone);
	String login(String phone, String password);
}
