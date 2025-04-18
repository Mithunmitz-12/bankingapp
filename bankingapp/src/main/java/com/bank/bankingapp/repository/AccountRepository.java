package com.bank.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankingapp.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
