package com.lima.servicee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.entity.Account;
import com.lima.repository.AccountRepository;
import com.lima.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String existsByUserName(String userName) {
		return accountRepository.existsByUserName(userName);
	}

	@Override
	public Account findAccountByUserName(String userName) {
		return accountRepository.findAccountByUserName(userName);
	}

}
