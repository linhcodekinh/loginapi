package com.lima.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findAccountByUserName(String username);
	
	@Query(value = "select user_name from account where user_name = ?1", nativeQuery = true)
	String existsByUserName(String userName);
}
