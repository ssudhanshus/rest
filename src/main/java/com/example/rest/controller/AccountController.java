package com.example.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.Dto.AccountDto;
import com.example.rest.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	// Get account details by Id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {

		AccountDto accountDto = accountService.getAccountById(id);

		return ResponseEntity.ok(accountDto);
	}

	// Deposit
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);

	}
	
	// withdraw rest api
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);

	}
	
	// Get all accounts
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}

	// Delete account 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok(id + " : Deleted this account successfuly!");
		
	}

}
