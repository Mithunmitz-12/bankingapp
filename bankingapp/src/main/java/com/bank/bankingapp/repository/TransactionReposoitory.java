package com.bank.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankingapp.entity.Transaction;
@Repository
public interface TransactionReposoitory extends JpaRepository<Transaction, Integer> {

	//List<Transaction> findByAccount_AccountId(int accountId);

}
