package com.project.bill.payment.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bill.payment.application.entity.Client;
import com.project.bill.payment.application.exception.ClientNotFoundException;
import com.project.bill.payment.application.repository.ClientRepository;
import com.project.bill.payment.application.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> getAllClient() {
		List<Client> clients = (List<Client>)clientRepository.findAll();
		return clients;
	}

	@Override
	public String addClient(Client client) {
		Client c = clientRepository.save(client);
		if(c != null) {
			return "Client added successfully";
		}else {
			return "Client not added!";
		}
		
	}

	@Override
	public String deleteClient(long id) {
		clientRepository.deleteById(id);
		return "Client deleted!";
	}

	@Override
	public String editClient(Client client) {
		Client c = clientRepository.save(client);
		if(c != null) {
			return "Client updated successfully";
		}else {
			return "Client  not updated!";
		}
		
	}

	@Override
	public Client getClient(long id) {
		Client c = clientRepository.findById(id).get();
		if(c == null) {
			try {
				throw new ClientNotFoundException("Client not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return c;
	}

	@Override
	public String login(String phone, String password) {
		Client client = clientRepository.findByPhone(phone);
		if(client == null) {
			return "Client not found!";
		}else if(password.equals(client.getPassword())) {
			return "Login Successfully";
		}else {
			return "Invalid Password";
		}
		
	}

	@Override
	public Client getClient(String phone) {
		Client client = clientRepository.findByPhone(phone);
		if(client == null) {
			try {
				throw new ClientNotFoundException("Client not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return client;
	}

}
