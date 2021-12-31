package com.project.bill.payment.application.exception;

public class OrderNotFoundException extends Exception{
	public OrderNotFoundException() {
		super();
	}
	
	public OrderNotFoundException(String error) {
		super(error);
	}
}
