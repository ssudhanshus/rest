package com.example.rest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.rest.Dto.AccountDto;
import com.example.rest.entity.Account;
import com.example.rest.mapper.AccountMapper;
import com.example.rest.repository.AccountRepository;
import com.example.rest.service.AccountService;

@Service

public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {

		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepository.save(account);
		
		// Setting values in Transaction table

		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't exist"));

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't exist"));

		double updatedamount = account.getBalance() + amount;
		account.setBalance(updatedamount);

		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't exist"));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Ammount");
		}

		double updatedamount = account.getBalance() - amount;
		account.setBalance(updatedamount);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());

	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't exist"));
		
		accountRepository.deleteById(id);
		
	}

}
