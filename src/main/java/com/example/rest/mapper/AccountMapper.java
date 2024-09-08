package com.example.rest.mapper;


import com.example.rest.Dto.AccountDto;
import com.example.rest.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance(),
				accountDto.getAddress()
				
				);
		
		return account;
	}
	
	// converts Jpa into DTO
	public static AccountDto mapToAccountDto(Account account) {
		
		AccountDto accountDto = new AccountDto(
				
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance(),
				account.getAddress()
				);
		
		return accountDto;
	}

}
