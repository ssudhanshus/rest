package com.example.rest.service;

import java.util.List;

import com.example.rest.Dto.AccountDto;

public interface AccountService {
	
AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	AccountDto deposit(Long id, double amount);
	AccountDto withdraw (Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	void deleteAccount(Long id);

}
