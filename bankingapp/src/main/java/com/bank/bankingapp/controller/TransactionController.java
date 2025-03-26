package com.bank.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.Transaction;
import com.bank.bankingapp.service.TransactionService;

@RestController
@RequestMapping("/transation")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		return new ResponseEntity<List<Transaction>>(transactionService.getAllTransactions(), HttpStatus.OK);
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int transactionId) {
		return new ResponseEntity<Transaction>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
	}

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable int accountId) {
		return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByAccountId(accountId),
				HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestParam int accountId, @RequestParam Double amount,
			@RequestParam(required = false) String description) {
		return new ResponseEntity<Transaction>(transactionService.deposit(accountId, amount, description),
				HttpStatus.CREATED);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestParam int accountId, @RequestParam Double amount,
			@RequestParam(required = false) String description) {
		return new ResponseEntity<Transaction>(transactionService.withdraw(accountId, amount, description),
				HttpStatus.CREATED);
	}

	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transfer(@RequestParam int fromAccountId, @RequestParam int toAccountId,
			@RequestParam Double amount, @RequestParam(required = false) String description) {
		return new ResponseEntity<Transaction>(
				transactionService.transfer(fromAccountId, toAccountId, amount, description), HttpStatus.CREATED);
	}

}
