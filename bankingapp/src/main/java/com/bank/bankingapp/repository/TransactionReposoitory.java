package com.bank.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bankingapp.entity.Transaction;

public interface TransactionReposoitory extends JpaRepository<Transaction, Integer> {
	//List<Transaction> findByAccount_AccountId(int accountId);
	//List<Transaction> findByAccount_AccountId(int accountId);
	List<Transaction> findByAccount_Id(int accountId);

}
