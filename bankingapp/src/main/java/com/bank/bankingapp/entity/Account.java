package com.bank.bankingapp.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int accountNumber;
	private String accountType;
	private Double balance;
	private LocalDate createdAt;
	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "customer_id", insertable = false, updatable = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int id, int accountNumber, String accountType, Double balance, LocalDate createdAt,
			Customer customer, List<Transaction> transactions) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.createdAt = createdAt;
		this.customer = customer;
		this.transactions = transactions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
