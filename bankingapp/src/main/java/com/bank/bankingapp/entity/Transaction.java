package com.bank.bankingapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int accountId;
	private String accountType; // deposit or withdrawal
	private float amount;
	private LocalDate transcationDate;
	private String description;
	private String status; // pending or completed

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, int accountId, String accountType, float amount, LocalDate transcationDate,
			String description, String status) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountType = accountType;
		this.amount = amount;
		this.transcationDate = transcationDate;
		this.description = description;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDate getTranscationDate() {
		return transcationDate;
	}

	public void setTranscationDate(LocalDate transcationDate) {
		this.transcationDate = transcationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
