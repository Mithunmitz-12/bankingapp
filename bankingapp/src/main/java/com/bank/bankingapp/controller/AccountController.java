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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.Account;
import com.bank.bankingapp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accService;

	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAllAcc() {
		return new ResponseEntity<List<Account>>(accService.getAllAcc(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable int id) {
		return new ResponseEntity<Account>(accService.getAccountById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		return new ResponseEntity<Account>(accService.saveAccount(account), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account accountDetails) {
		return new ResponseEntity<Account>(accService.updateAccount(id,accountDetails), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable int id) {
		accService.deleteAccount(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Account> deposit(@RequestParam Integer accountId, Double amount) {
		return new ResponseEntity<Account>(accService.deposit(accountId, amount), HttpStatus.CREATED);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Account> withdraw(@RequestParam int accountId, @RequestParam Double amount) {
		return new ResponseEntity<Account>(accService.withdraw(accountId, amount), HttpStatus.OK);
	}

}
