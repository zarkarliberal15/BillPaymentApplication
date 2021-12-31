package com.project.bill.payment.application.exception;

public class OrderProductNotFoundException extends Exception{
	public OrderProductNotFoundException() {
		super();
	}
	
	public OrderProductNotFoundException(String error) {
		super(error);
	}
}
