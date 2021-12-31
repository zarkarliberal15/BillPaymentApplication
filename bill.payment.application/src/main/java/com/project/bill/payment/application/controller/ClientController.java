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

import com.project.bill.payment.application.entity.Client;
import com.project.bill.payment.application.impl.ClientServiceImpl;

@RestController
@RequestMapping("/app")
public class ClientController {
	
	@Autowired
	private ClientServiceImpl impl;
	
	
	@GetMapping("/clients")
	public List<Client> getAllClient(){
		return impl.getAllClient();
	}
	
	@GetMapping("/client/{id}")
	public Client getClient(@PathVariable long id) {
		return impl.getClient(id);
	}
	
	@PostMapping("/client/addClient")
	public String addClient(@RequestBody Client client) {
		return impl.addClient(client);
	}
	
	@PatchMapping("/client/editClient")
	public String editClient(@RequestBody Client client) {
		return impl.editClient(client);
	}
	
	@DeleteMapping("client/deleteClient/{id}")
	public String deleteClient(@PathVariable long id) {
		return impl.deleteClient(id);
	}
	
	@PostMapping("client/login")
	public String login(@RequestBody Client client) {
		return impl.login(client.getPhone(), client.getPassword());
	}
	
	@GetMapping("/client/phone/{phone}")
	public Client getClientByPhone(@PathVariable String phone) {
		return impl.getClient(phone);
	}
}
