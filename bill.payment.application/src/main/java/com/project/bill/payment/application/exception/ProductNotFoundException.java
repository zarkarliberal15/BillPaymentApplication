package com.project.bill.payment.application.exception;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String error) {
		super(error);
	}
}
