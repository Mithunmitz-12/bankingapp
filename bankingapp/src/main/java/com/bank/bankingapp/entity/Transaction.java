package com.bank.bankingapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	private String transactionType;
	private Double amount;
	private LocalDateTime transactionDate;
	private String description;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(Account account, String transactionType, Double amount, LocalDateTime transactionDate,
			String description) {
		super();
		this.account = account;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.description = description;
	}

//	public Transaction(Account account2, String transactionType2, Double amount2, LocalDateTime now, Object object) {
//		// TODO Auto-generated constructor stub
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}