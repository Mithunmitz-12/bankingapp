package com.bank.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.Customer;
import com.bank.bankingapp.exception.NotFoundException;
import com.bank.bankingapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	public CustomerService cusService;

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable int id) {
		return new ResponseEntity<Customer>(cusService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCus() {
		return (ResponseEntity<List<Customer>>) new ResponseEntity<List<Customer>>(cusService.getAllCus(),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(cusService.createCustomer(customer), HttpStatus.CREATED);
	}
//	@PostMapping
//	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
//	    try {
//	        return new ResponseEntity<>(cusService.createCustomer(customer), HttpStatus.CREATED);
//	    } catch (DataIntegrityViolationException e) {
//	        return new ResponseEntity<>("Data Integrity Violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//	    }
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails)
			throws NotFoundException {
		return new ResponseEntity<Customer>(cusService.updateCustomer(id, customerDetails), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
		cusService.deleteCustomer(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
