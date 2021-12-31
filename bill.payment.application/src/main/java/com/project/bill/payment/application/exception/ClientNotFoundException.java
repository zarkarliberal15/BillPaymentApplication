package com.project.bill.payment.application.exception;

public class ClientNotFoundException extends Exception {
	public ClientNotFoundException() {
		super();
	}
	public ClientNotFoundException(String error) {
		super(error);
	}

}
