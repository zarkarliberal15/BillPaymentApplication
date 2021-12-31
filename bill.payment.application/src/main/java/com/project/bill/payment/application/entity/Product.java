package com.project.bill.payment.application.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 create table product(
id number primary key,
name varchar2(50) not null,
description varchar2(250),
price number not null,
quantity number not null,
discount number,
tax number,
image blob,
sid not null references store(id) on delete cascade
);

 */
@Entity
public class Product {
	@Id
	private long id;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private double discount;
	private double tax;
	private byte[] image;
	@ManyToOne(targetEntity=Store.class)
	@JoinColumn(name="sid")
	private Store store;
	public Product() {
	}
	public Product(long id, String name, String description, double price, int quantity, double discount, double tax,
			byte[] image, Store store) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.tax = tax;
		this.image = image;
		this.store = store;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", discount=" + discount + ", tax=" + tax + ", image="
				+ Arrays.toString(image) + ", store=" + store + "]";
	}
	
}
