package com.bank.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.Customer;
import com.bank.bankingapp.service.CustomerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	public CustomerService cusService;
	
	@PostMapping
	public ResponseEntity<Customer>  createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Customer> (cusService.createCustomer(customer), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails) {
		return new ResponseEntity<Customer> (cusService.updateCustomer(id,customerDetails), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
		cusService.deleteCustomer(id);
		return new ResponseEntity<HttpStatus> (HttpStatus.NO_CONTENT);
	}
}
