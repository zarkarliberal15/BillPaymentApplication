package com.project.bill.payment.application.exception;

public class StoreNotFoundException extends Exception{
	public StoreNotFoundException() {
		super();
	}
	
	public StoreNotFoundException(String error) {
		super(error);
	}
}
