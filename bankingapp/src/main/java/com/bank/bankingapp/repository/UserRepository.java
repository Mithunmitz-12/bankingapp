package com.bank.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankingapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//User findByUsername(String username);
	User findByUserName(String userName); 
}
