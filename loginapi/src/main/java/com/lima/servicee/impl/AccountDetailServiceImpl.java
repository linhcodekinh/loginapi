package com.lima.servicee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lima.entity.Account;
import com.lima.repository.AccountRepository;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {

	/**
	 * Nguyen Van Linh
	 */
	@Autowired
	AccountRepository accountRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findAccountByUserName(username);
		if (account == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		return AccountDetailsImpl.build(account);
	}

}
