package com.bank.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankingapp.entity.Account;
import com.bank.bankingapp.entity.Customer;
import com.bank.bankingapp.repository.AccountRepository;
import com.bank.bankingapp.repository.CustomerRepository;
import com.organization.empmanagement.repository.NotFoundException;

@Service
public class AccountService {
	@Autowired
	public AccountRepository accRepository;
	@Autowired
	public CustomerService cusService;
//	public Account saveAccount;

	@Autowired
	public CustomerRepository cusRepository;

	public List<Account> getAllAcc() {
		return accRepository.findAll();
	}

	public Account getAccountById(Integer accountId) {
		Optional<Account> account = accRepository.findById(accountId);
		return account.orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
	}
	
	public Account updateAccount(int id, Account accountDetails) {
	    Optional<Account> optionalAccount = accRepository.findById(id);
	    if (optionalAccount.isPresent()) {
	        Account existingAccount = optionalAccount.get();

	        existingAccount.setAccountNumber(accountDetails.getAccountNumber());
	        existingAccount.setAccountType(accountDetails.getAccountType());
	        existingAccount.setBalance(accountDetails.getBalance());
	        existingAccount.setCreatedAt(accountDetails.getCreatedAt());

	        // Handle customer update
	        if (accountDetails.getCustomer() != null && accountDetails.getCustomer().getId() != null) {
	            Optional<Customer> customer = cusRepository.findById(accountDetails.getCustomer().getId());
	            if (customer.isPresent()) {
	                existingAccount.setCustomer(customer.get());
	            } else {
	                throw new NotFoundException("Customer not found with ID: " + accountDetails.getCustomer().getId());
	            }
	        }

	        return accRepository.save(existingAccount);
	    }

	    throw new NotFoundException("Account not found with ID: " + id);
	}

	public Account saveAccount(Account account) {
		if (account.getBalance() == null) {
			account.setBalance(0.0);
		}
		Optional<Customer> customer = cusRepository.findById(account.getCustomer().getId());
		account.setCustomer(customer.get());

		return accRepository.save(account);
	}

	public void deleteAccount(int id) {
		accRepository.deleteById(id);
	}

	public Account deposit(Integer accountId, Double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
		Account account = getAccountById(accountId);
		account.setBalance(account.getBalance() + amount);
		return accRepository.save(account);
	}

	public Account withdraw(int accountId, Double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		Account account = getAccountById(accountId);
		if (account.getBalance() < amount) {
			throw new IllegalArgumentException("Insufficient balance");
		}
		account.setBalance(account.getBalance() - amount);
		return accRepository.save(account);
	}

}
