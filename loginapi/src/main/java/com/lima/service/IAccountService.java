package com.lima.service;

import com.lima.entity.Account;


public interface IAccountService {
	/**
	 * Nguyen Van Linh
	 **/

	String existsByUserName(String userName);
	
	Account findAccountByUserName(String userName);

}
