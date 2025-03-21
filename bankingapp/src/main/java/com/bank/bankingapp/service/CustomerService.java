package com.bank.bankingapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankingapp.entity.Customer;
import com.bank.bankingapp.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	public CustomerRepository cusRepository;
	
	public Customer createCustomer( Customer customer) {
		return cusRepository.save(customer);
	}

	public Customer updateCustomer(int id, Customer customerDetails) {
		Optional<Customer> customer=cusRepository.findById(id);
		if(customer != null) {
			Customer existingcustomer = customer.get();
			existingcustomer.setName(customerDetails.getName());
			existingcustomer.setPhoneNo(customerDetails.getPhoneNo());
			existingcustomer.setAddress(customerDetails.getAddress());
			existingcustomer.setEmail(customerDetails.getEmail());
			existingcustomer.setDob(customerDetails.getDob());
			return cusRepository.save(existingcustomer);
		}
		return null;
	}

	public void deleteCustomer(int id) {
		cusRepository.deleteById(id);
	}

}
