package com.bank.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankingapp.entity.Account;
import com.bank.bankingapp.repository.AccountRepository;
import com.organization.empmanagement.repository.NotFoundException;

@Service
public class AccountService {
	@Autowired
	public AccountRepository accRepository;
	public Account saveAccount;

	public List<Account> getAllAcc() {
		return accRepository.findAll();
	}

	public Account getAccountById(Integer accountId) {
		Optional<Account> account = accRepository.findById(accountId);
		return account.orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
	}

	public Account updateAccount(int id, Account accountDetails) throws NotFoundException {
		Optional<Account> account = accRepository.findById(id);
		if (account.isPresent()) {
			Account existingAccount = account.get();
			existingAccount.setAccountNumber(accountDetails.getAccountNumber());
			existingAccount.setAccountType(accountDetails.getAccountType());
			existingAccount.setBalance(accountDetails.getBalance());
			existingAccount.setCreatedAt(accountDetails.getCreatedAt());
			//existingAccount.setCustomerId(accountDetails.getCustomerId());
			return accRepository.save(existingAccount);
		}
		throw new NotFoundException("Department Not Found for Update" + id);
	}

	public Account saveAccount(Account account) {
		if (account.getBalance() == null) {
			account.setBalance(0.0);
		}
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
