package com.bank.bankingapp.exception;

public class NotFoundException extends Exception{
	public NotFoundException(String id) {
		super("The id'" + id + " Does not exist in our records");
	}
}
