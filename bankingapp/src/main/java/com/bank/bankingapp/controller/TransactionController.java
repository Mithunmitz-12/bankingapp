package com.bank.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.Transaction;
import com.bank.bankingapp.service.TransactionService;

@RestController
@RequestMapping("/transaction")
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

//	@PostMapping("/deposit")
//	public ResponseEntity<Transaction> deposit(@RequestParam int accountId, @RequestParam Double amount,
//			@RequestParam(required = false) String description) {
//		return new ResponseEntity<Transaction>(transactionService.deposit(accountId, amount, description),
//				HttpStatus.CREATED);
//	}
//	@PostMapping("/deposit")
//	public ResponseEntity<Account> deposit(@RequestBody Map<String, Object> payload) {
//	    if (!payload.containsKey("accountId") || !payload.containsKey("amount")) {
//	        throw new IllegalArgumentException("Missing required fields: accountId or amount");
//	    }
//
//	    Integer accountId = (Integer) payload.get("accountId");
//	    Double amount = ((Number) payload.get("amount")).doubleValue();
//	    String description = (String) payload.getOrDefault("description", "Deposit to account");
//
//	    return new ResponseEntity<>(accountService.deposit(accountId, amount), HttpStatus.CREATED);
//	}
	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody Map<String, Object> payload) {
		Integer accountId = (Integer) payload.get("accountId");
		Double amount = ((Number) payload.get("amount")).doubleValue();
		String description = (String) payload.getOrDefault("description", "Deposit to account");

		Transaction transaction = transactionService.deposit(accountId, amount, description);

		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
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
