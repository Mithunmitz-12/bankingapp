package com.bank.bankingapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankingapp.entity.Account;
import com.bank.bankingapp.entity.Transaction;
import com.bank.bankingapp.repository.TransactionReposoitory;

@Service
public class TransactionService {
	@Autowired
	private TransactionReposoitory transactionRepository;

	@Autowired
	private AccountService accountService;

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public Transaction getTransactionById(int transactionId) {
		Optional<Transaction> transaction = transactionRepository.findById(transactionId);
		return transaction.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
	}

	public List<Transaction> getTransactionsByAccountId(int accountId) {
		return transactionRepository.findByAccount_Id(accountId);
	}

	public Transaction createTransaction(Transaction transaction) {
		transaction.setTransactionDate(LocalDateTime.now()); // Set current timestamp
		return transactionRepository.save(transaction);
	}

	public Transaction deposit(int accountId, Double amount, String description) {
		Account account = accountService.deposit(accountId, amount);
		String transactionType = "DEPOSIT";
		Transaction transaction = new Transaction(account, transactionType, amount, LocalDateTime.now(),
				description != null ? description : "Deposit to account");
		return transactionRepository.save(transaction);
	}

	public Transaction withdraw(int accountId, Double amount, String description) {

		Account account = accountService.withdraw(accountId, amount);
		String transactionType = "WITHDRAW";
		Transaction transaction = new Transaction(account, transactionType, amount, LocalDateTime.now(),
				description != null ? description : "withdraw from account");
		return transactionRepository.save(transaction);
	}

	public Transaction transfer(int fromAccountId, int toAccountId, Double amount, String description) {
		accountService.withdraw(fromAccountId, amount);
		Account toAccount = accountService.deposit(toAccountId, amount);
		String transactionType = "TRANSFER";
		Transaction transaction = new Transaction(accountService.getAccountById(fromAccountId), transactionType, amount,
				LocalDateTime.now(),
				description != null ? description : "Transfer to account " + toAccount.getAccountNumber());
		return transactionRepository.save(transaction);
	}

}
