package com.project.bill.payment.application.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	private long id;
	
	@OneToOne
	@JoinColumn(name="cid")
	private Client client;
	private double total;
	private String payment_mode;
	private String payment_status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datetime")
	private Date dateTime;
	private String transaction_id;
	
	
	public Order() {
	}

	public Order(long id, Client client) {
		this.id = id;
		this.client = client;
		this.payment_status = "Pending";
		this.payment_mode = "cash";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getPayment_mode() {
		return payment_mode;
	}


	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}


	public String getPayment_status() {
		return payment_status;
	}


	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}


	public Date getDateTime() {
		return dateTime;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public String getTransaction_id() {
		return transaction_id;
	}


	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}


	@Override
	public String toString() {
		return String.format("%-5s %-5s %-10.2f %-15s %-15s %-25s %-15s",id,client.getId(),total,payment_mode,payment_status,dateTime,transaction_id);	
	} 
		
}
